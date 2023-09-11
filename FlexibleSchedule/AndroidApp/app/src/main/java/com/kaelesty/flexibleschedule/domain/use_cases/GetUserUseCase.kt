package com.kaelesty.flexibleschedule.domain.use_cases

import androidx.lifecycle.LiveData
import com.kaelesty.flexibleschedule.domain.entities.User
import com.kaelesty.flexibleschedule.domain.repo.IUserRepo

class GetUserUseCase(val repo: IUserRepo) {

	fun getUser(): LiveData<User> {
		return repo.getUser()
	}
}