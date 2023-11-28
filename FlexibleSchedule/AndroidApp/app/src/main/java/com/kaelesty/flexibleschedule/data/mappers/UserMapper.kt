package com.kaelesty.flexibleschedule.data.mappers

import com.kaelesty.flexibleschedule.data.entities.dbmodels.auth.UserDbModel
import com.kaelesty.flexibleschedule.domain.entities.User

object UserMapper {

	fun UserToDbModel(user: User) = UserDbModel(
		user.email,
		user.name,
		user.isAuthorized,
		user.jwt
	)

	fun DbModelToUser(user: UserDbModel) = User(
		user.name,
		user.email,
		user.isAuthorized,
		user.jwt
	)
}