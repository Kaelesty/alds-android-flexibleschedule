package com.kaelesty.flexibleschedule.domain.repo

import com.kaelesty.flexibleschedule.domain.AuthUseCaseResult

interface IUserRepo {

	suspend fun register(name: String, email: String, password: String): AuthUseCaseResult

	suspend fun login(email: String, password: String): AuthUseCaseResult

	suspend fun logout()
}