package com.kaelesty.flexibleschedule.presentation.fragments.user

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kaelesty.flexibleschedule.data.repos.UserRepo
import com.kaelesty.flexibleschedule.data.entities.UserResponse
import com.kaelesty.flexibleschedule.data.repos.GroupRepo
import com.kaelesty.flexibleschedule.domain.AuthReturnCode
import com.kaelesty.flexibleschedule.domain.entities.User
import com.kaelesty.flexibleschedule.domain.use_cases.GetUserUseCase
import com.kaelesty.flexibleschedule.domain.use_cases.LoginUseCase
import com.kaelesty.flexibleschedule.domain.use_cases.LogoutUseCase
import com.kaelesty.flexibleschedule.domain.use_cases.RegisterUseCase
import com.kaelesty.flexibleschedule.domain.use_cases.SaveUserUseCase
import com.kaelesty.flexibleschedule.domain.use_cases.UpdateTimetableUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(activity: Context, application: Application) : AndroidViewModel(application) {

	private val userRepo = UserRepo(activity)
	private val registerUseCase = RegisterUseCase(userRepo)
	private val loginUseCase = LoginUseCase(userRepo)
	private val logoutUseCase = LogoutUseCase(userRepo)
	private val saveUserUseCase = SaveUserUseCase(userRepo)
	private val getUserUseCase = GetUserUseCase(userRepo)

	private val groupRepo = GroupRepo(activity)
	private val updateTimetableUseCase = UpdateTimetableUseCase(groupRepo)


	private val _userState: MutableLiveData<UserState> = MutableLiveData()
	val userState: LiveData<UserState> get() = _userState

	init {

		getUserUseCase.getUser().observe(activity as LifecycleOwner) { user ->
			if (user.isAuthorized) {
				_userState.postValue(UserStateAuthorized(user.name, user.email) as UserState)
			} else {
				_userState.postValue(UserStateUnauthorized())
			}
		}
	}

	fun register(email: String, name: String, password: String) {
		_userState.postValue(UserStateLoading())
		if (! validateRegister(email, name, password)) {
			return
		}

		viewModelScope.launch(Dispatchers.IO) {
			val result = registerUseCase.registerUser(email, name, password)
			when (result.rc) {
				AuthReturnCode.RC_REGISTER_OK -> {
					login(email, password)
				}

				AuthReturnCode.RC_REGISTER_EMAIL_EXIST -> {
					_userState.postValue(UserStateRegisterError("Email не найден"))
				}

				else -> {
					_userState.postValue(
						UserStateRegisterError(
							"Ошибка на сервере"
						)
					)
				}
			}
		}
	}

	fun login(email: String, password: String) {
		_userState.postValue(UserStateLoading())
		if (! validateLogin(email, password)) {
			return
		}

		viewModelScope.launch(Dispatchers.IO) {
			val result = loginUseCase.login(email, password)
			when (result.rc) {
				AuthReturnCode.RC_LOGIN_OK -> {
					result.body as UserResponse
					onSuccessfulLogin(result.body.email, result.body.name, result.jwt ?: "")
					_userState.postValue(UserStateAuthorized(result.body.name, result.body.email))
				}

				AuthReturnCode.RC_LOGIN_PASSWORD -> {
					_userState.postValue(UserStateLoginError(
						passwordMessage = "Неправильный пароль"
					))
				}

				AuthReturnCode.RC_LOGIN_EMAIL -> {
					_userState.postValue(UserStateLoginError(
						emailMessage = "Email не найден"
					))
				}

				else -> {
					_userState.postValue(
						UserStateRegisterError(
							"Ошибка на сервере"
						)
					)
				}
			}
		}
	}

	fun logout() {
		viewModelScope.launch(Dispatchers.IO) {
			logoutUseCase.logout()
			_userState.postValue(UserStateUnauthorized())
		}
	}

	private fun validateLogin(email: String, password: String): Boolean {

		var emailError: String = ""
		var passwordError: String = ""

		if (! email.contains("@")) {
			emailError = "Нет символа '@'"
		}
		if (password.length < 4) {
			passwordError = "Слишком короткий пароль"
		}

		_userState.postValue(
			UserStateLoginError(
				"", emailError, passwordError
			)
		)

		return emailError == "" && passwordError == ""
	}

	private fun validateRegister(email: String, name: String, password: String): Boolean {

		var emailError: String = ""
		var nameError: String = ""
		var passwordError: String = ""

		if (! email.contains("@")) {
			emailError = "Нет символа '@'"
		} else if (email.length < 5) {
			emailError = "Слишком короткий email"
		}

		if (name.length < 3) {
			nameError = "Слишком короткое имя"
		}

		if (password.length < 4) {
			passwordError = "Слишком короткий пароль"
		}

		_userState.postValue(
			UserStateRegisterError(
				"", emailError, nameError, passwordError
			)
		)

		return emailError == "" && passwordError == "" && nameError == ""
	}

	private fun onSuccessfulLogin(email: String, name: String, jwt: String) {

		viewModelScope.launch(Dispatchers.IO) {
			saveUserUseCase.saveUser(
				User(
					name, email, true, jwt
				)
			)
			updateTimetableUseCase.updateTimetable()
		}
	}
}