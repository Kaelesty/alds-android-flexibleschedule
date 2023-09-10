package com.kaelesty.flexibleschedule.data

import android.util.Log
import com.kaelesty.flexibleschedule.data.entities.LoginDto
import com.kaelesty.flexibleschedule.data.entities.RegisterDto
import com.kaelesty.flexibleschedule.data.remote.AuthServiceFactory
import com.kaelesty.flexibleschedule.domain.AuthUseCaseResult
import com.kaelesty.flexibleschedule.domain.repo.IUserRepo
import retrofit2.Response

class UserRepo : IUserRepo {

	val authService = AuthServiceFactory.apiService

	override suspend fun register(
		name: String,
		email: String,
		password: String
	): AuthUseCaseResult {
		val response = authService.register(RegisterDto(name, email, password))
		if (response.code() == 200) {
			return AuthUseCaseResult("OK")
		}
		// TODO relocate strings to string_resources
		return AuthUseCaseResult("Ошибка на сервере")
	}

	override suspend fun login(email: String, password: String): AuthUseCaseResult {
		val response = authService.login(LoginDto(email, password))
		if (response.code() == 200) {
			return AuthUseCaseResult("OK", response.body())
		}
		// TODO relocate strings to string_resources
		return AuthUseCaseResult("Ошибка на сервере")
	}

	override suspend fun logout() {
		authService.logout()
	}
}