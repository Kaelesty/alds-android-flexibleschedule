package com.kaelesty.flexibleschedule.domain.use_cases

import com.kaelesty.flexibleschedule.domain.repo.IUserRepo

class LogoutUseCase(private val repo: IUserRepo) {

	suspend fun logout() {
		repo.logout()
	}
}