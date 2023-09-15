package com.kaelesty.flexibleschedule.data.repos

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.kaelesty.flexibleschedule.data.mappers.UserMapper
import com.kaelesty.flexibleschedule.data.entities.dtos.auth.LoginDto
import com.kaelesty.flexibleschedule.data.entities.dtos.auth.RegisterDto
import com.kaelesty.flexibleschedule.data.entities.dbmodels.auth.UserDbModel
import com.kaelesty.flexibleschedule.data.local.auth.UserDao
import com.kaelesty.flexibleschedule.data.local.auth.UserDatabase
import com.kaelesty.flexibleschedule.data.remote.AuthServiceFactory
import com.kaelesty.flexibleschedule.domain.AuthReturnCode
import com.kaelesty.flexibleschedule.domain.AuthUseCaseResult
import com.kaelesty.flexibleschedule.domain.entities.User
import com.kaelesty.flexibleschedule.domain.repo.IUserRepo

class UserRepo(val context: Context) : IUserRepo {

	val authService = AuthServiceFactory.apiService
	val userDao: UserDao = UserDatabase.getInstance(context).dao()

	override suspend fun register(
		name: String,
		email: String,
		password: String
	): AuthUseCaseResult {
		val response = authService.register(RegisterDto(name, email, password))
		Log.d("UserViewModel", "register " + response.code().toString())
		if (response.code() == 201) {
			return AuthUseCaseResult(AuthReturnCode.RC_REGISTER_OK)
		}
		// TODO relocate strings to string_resources
		return AuthUseCaseResult(AuthReturnCode.RC_REGISTER_UNKWOWN)
	}

	override suspend fun login(email: String, password: String): AuthUseCaseResult {
		val response = authService.login(LoginDto(email, password))
		Log.d("UserViewModel", "login " + response.code().toString())
		Log.d("UserViewModel", response.errorBody().toString())
		return when (response.code()) {
			200 -> {
				val jwt = response.headers().values("Set-Cookie")[0]
				AuthUseCaseResult(AuthReturnCode.RC_LOGIN_OK, response.body(), jwt)
			}

			500 -> {
				AuthUseCaseResult(AuthReturnCode.RC_LOGIN_EMAIL)
			}

			400 -> {
				AuthUseCaseResult(AuthReturnCode.RC_LOGIN_PASSWORD)
			}

			else -> {
				AuthUseCaseResult(AuthReturnCode.RC_LOGIN_UNKWOWN)
			}
		}
	}

	override suspend fun logout() {
		authService.logout()
		userDao.saveUser(
			UserDbModel("", "", false, "")
		)
	}

	override suspend fun saveUser(user: User) {
		userDao.saveUser(
			UserMapper.UserToDbModel(user)
		)
	}

	override fun getUser(): LiveData<User> {
		return userDao.getUser().map {
			if (it != null) UserMapper.DbModelToUser(it) else User("", "", false, "")
		}
	}
}