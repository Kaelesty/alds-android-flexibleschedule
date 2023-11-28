package com.kaelesty.flexibleschedule.domain.use_cases

import com.kaelesty.flexibleschedule.domain.entities.User
import com.kaelesty.flexibleschedule.domain.repo.IUserRepo

class SaveUserUseCase(val repo: IUserRepo) {

	suspend fun saveUser(user: User) {
		repo.saveUser(user)
	}
}