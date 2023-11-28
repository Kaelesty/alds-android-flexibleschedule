package com.kaelesty.flexibleschedule.domain.repo

import androidx.lifecycle.LiveData
import com.kaelesty.flexibleschedule.domain.AuthUseCaseResult
import com.kaelesty.flexibleschedule.domain.entities.User

interface IUserRepo {

	suspend fun register(name: String, email: String, password: String): AuthUseCaseResult

	suspend fun login(email: String, password: String): AuthUseCaseResult

	suspend fun saveUser(user: User)

	fun getUser(): LiveData<User>

	suspend fun logout()
}