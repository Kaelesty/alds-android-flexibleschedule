package com.kaelesty.flexibleschedule.data

import com.kaelesty.flexibleschedule.data.entities.UserDbModel
import com.kaelesty.flexibleschedule.domain.entities.User

object UserMapper {

	fun UserToDbModel(user: User) = UserDbModel(
		user.email,
		user.name,
		user.isAuthorized,
	)

	fun DbModelToUser(user: UserDbModel) = User(
		user.name,
		user.email,
		user.isAuthorized,
	)
}