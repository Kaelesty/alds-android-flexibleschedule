package com.kaelesty.flexibleschedule.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.kaelesty.flexibleschedule.data.entities.LoginDto
import com.kaelesty.flexibleschedule.data.entities.RegisterDto
import com.kaelesty.flexibleschedule.data.entities.UserDbModel
import com.kaelesty.flexibleschedule.data.local.UserDao
import com.kaelesty.flexibleschedule.data.local.UserDatabase
import com.kaelesty.flexibleschedule.data.remote.AuthServiceFactory
import com.kaelesty.flexibleschedule.domain.AuthUseCaseResult
import com.kaelesty.flexibleschedule.domain.entities.User
import com.kaelesty.flexibleschedule.domain.repo.IUserRepo
import retrofit2.Response

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
			return AuthUseCaseResult("OK")
		}
		// TODO relocate strings to string_resources
		return AuthUseCaseResult("Ошибка на сервере")
	}

	override suspend fun login(email: String, password: String): AuthUseCaseResult {
		val response = authService.login(LoginDto(email, password))
		Log.d("UserViewModel", "login " + response.code().toString())
		if (response.code() == 200) {
			var jwt = response.headers().values("Set-Cookie")[0]
			return AuthUseCaseResult("OK", response.body(), jwt)
		}
		// TODO relocate strings to string_resources
		return AuthUseCaseResult("Ошибка на сервере")
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