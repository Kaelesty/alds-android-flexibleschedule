package com.kaelesty.flexibleschedule.domain.use_cases

import com.kaelesty.flexibleschedule.domain.AuthUseCaseResult
import com.kaelesty.flexibleschedule.domain.repo.IUserRepo

class LoginUseCase(private val repo: IUserRepo) {

	suspend fun login(email: String, password: String): AuthUseCaseResult {
		return repo.login(email, password)
	}
}