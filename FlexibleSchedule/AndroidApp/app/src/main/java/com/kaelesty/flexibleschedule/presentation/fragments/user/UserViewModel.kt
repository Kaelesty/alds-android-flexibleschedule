package com.kaelesty.flexibleschedule.presentation.fragments.user

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaelesty.flexibleschedule.data.UserRepo
import com.kaelesty.flexibleschedule.data.entities.UserResponse
import com.kaelesty.flexibleschedule.domain.entities.User
import com.kaelesty.flexibleschedule.domain.use_cases.GetUserUseCase
import com.kaelesty.flexibleschedule.domain.use_cases.LoginUseCase
import com.kaelesty.flexibleschedule.domain.use_cases.LogoutUseCase
import com.kaelesty.flexibleschedule.domain.use_cases.RegisterUseCase
import com.kaelesty.flexibleschedule.domain.use_cases.SaveUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserViewModel(activity: Context, application: Application) : AndroidViewModel(application) {

	private val repo = UserRepo(activity)
	private val registerUseCase = RegisterUseCase(repo)
	private val loginUseCase = LoginUseCase(repo)
	private val logoutUseCase = LogoutUseCase(repo)
	private val saveUserUseCase = SaveUserUseCase(repo)
	private val getUserUseCase = GetUserUseCase(repo)


	private val _registerState: MutableLiveData<RegisterState> = MutableLiveData()
	val registerState: LiveData<RegisterState> get() = _registerState

	private val _loginState: MutableLiveData<LoginState> = MutableLiveData()
	val loginState: LiveData<LoginState> get() = _loginState

	private val _logoutState: MutableLiveData<LogoutState> = MutableLiveData()
	val logoutState: LiveData<LogoutState> get() = _logoutState

	init {

		getUserUseCase.getUser().observe(activity as LifecycleOwner) { user ->
			if (user.isAuthorized) {
				_logoutState.postValue(LogoutState(
					true, user.email, user.name
				))

				_loginState.postValue(LoginState(false))
				_registerState.postValue(RegisterState(false))
			}
			else {
				_logoutState.postValue(LogoutState(
					false
				))
				_loginState.postValue(LoginState(true))
				_registerState.postValue(RegisterState(true))
			}
		}
	}

	fun register(email: String, name: String, password: String) {

		if (! validateRegister(email, name, password)) {
			return
		}

		viewModelScope.launch(Dispatchers.IO) {
			val result = registerUseCase.registerUser(email, name, password)
			if (result.message != "OK") {
				_registerState.postValue(
					RegisterState(true, "", "", "", result.message)
				)
				//TODO("Backend isn't handling duplicate emails?")
			}
			else {
				Log.d("UserViewModel", email + " " + password)
				login(email, password)
			}
		}
	}

	fun login(email: String, password: String) {
		if (!validateLogin(email, password)) {
			return
		}

		viewModelScope.launch(Dispatchers.IO) {
			val result = loginUseCase.login(email, password)
			if (result.message != "OK") {
				_loginState.postValue(LoginState(true, "", "", result.message))
			}
			else {
				result.body as UserResponse
				onSuccessfulLogin(result.body.email, result.body.name, result.jwt?:"")
			}
		}
	}

	fun logout() {
		viewModelScope.launch(Dispatchers.IO) {
			logoutUseCase.logout()
		}
	}

	private fun validateLogin(email: String, password: String): Boolean {

		var emailError: String = ""
		var passwordError: String = ""

		if (!email.contains("@")) {
			emailError = "Нет символа '@'"
		}
		if (password.length < 4) {
			passwordError = "Слишком короткий пароль"
		}

		_loginState.postValue(LoginState(
			true, emailError, passwordError, ""
		))

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

		_registerState.postValue(RegisterState(
			true,
			emailError,
			nameError,
			passwordError,
			""
		))

		return emailError == "" && passwordError == "" && nameError == ""
	}

	private fun onSuccessfulLogin(email: String, name: String, jwt: String) {

		viewModelScope.launch(Dispatchers.IO) {
			saveUserUseCase.saveUser(
				User(
					name, email, true, jwt
				)
			)
		}
	}
}