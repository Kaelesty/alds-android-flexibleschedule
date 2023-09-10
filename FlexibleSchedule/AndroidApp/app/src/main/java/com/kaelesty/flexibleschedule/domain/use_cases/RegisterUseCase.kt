package com.kaelesty.flexibleschedule.domain.use_cases

import com.kaelesty.flexibleschedule.domain.AuthUseCaseResult
import com.kaelesty.flexibleschedule.domain.repo.IUserRepo

class RegisterUseCase(private val repo: IUserRepo) {

	suspend fun registerUser(email: String, name: String, password: String): AuthUseCaseResult {
		return repo.register(name, email, password)
	}
}