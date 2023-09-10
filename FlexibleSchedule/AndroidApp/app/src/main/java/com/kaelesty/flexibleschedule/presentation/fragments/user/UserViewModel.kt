package com.kaelesty.flexibleschedule.presentation.fragments.user

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaelesty.flexibleschedule.data.UserRepo
import com.kaelesty.flexibleschedule.data.entities.UserResponse
import com.kaelesty.flexibleschedule.domain.use_cases.LoginUseCase
import com.kaelesty.flexibleschedule.domain.use_cases.LogoutUseCase
import com.kaelesty.flexibleschedule.domain.use_cases.RegisterUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

	private val repo = UserRepo()
	private val registerUseCase = RegisterUseCase(repo)
	private val loginUseCase = LoginUseCase(repo)
	private val logoutUseCase = LogoutUseCase(repo)


	private val _registerState: MutableLiveData<RegisterState> = MutableLiveData()
	val registerState: LiveData<RegisterState> get() = _registerState

	private val _loginState: MutableLiveData<LoginState> = MutableLiveData()
	val loginState: LiveData<LoginState> get() = _loginState

	private val _logoutState: MutableLiveData<LogoutState> = MutableLiveData()
	val logoutState: LiveData<LogoutState> get() = _logoutState

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
				onSuccessfulLogin(result.body.email, result.body.name)
				// TODO save logged user
			}
		}
	}

	fun logout() {
		viewModelScope.launch(Dispatchers.IO) {
			logoutUseCase.logout()
		}
		_logoutState.value = LogoutState(false)
		_loginState.value = LoginState(true)
		_registerState.value = RegisterState(true)
	}

	private fun validateLogin(email: String, password: String): Boolean {

		var emailError: String = ""
		var passwordError: String = ""

		if (!email.contains("@")) {
			emailError = "Нет символа '@'"
		}
		if (password.length < 6) {
			passwordError = "Слишком короткий пароль"
		}

		_loginState.value = LoginState(
			true, emailError, passwordError, ""
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

		if (password.length < 6) {
			passwordError = "Слишком короткий пароль"
		}

		_registerState.value = RegisterState(
			true,
			emailError,
			nameError,
			passwordError,
			""
		)

		return emailError == "" && passwordError == "" && nameError == ""
	}

	private fun onSuccessfulLogin(email: String, name: String) {

		_logoutState.postValue(LogoutState(
			true, email, name
		))

		_loginState.postValue(LoginState(false))
		_registerState.postValue(RegisterState(false))
	}
}