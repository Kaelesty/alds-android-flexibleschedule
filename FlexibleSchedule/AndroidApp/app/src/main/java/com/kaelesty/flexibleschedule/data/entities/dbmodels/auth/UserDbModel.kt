package com.kaelesty.flexibleschedule.data.entities.dbmodels.auth

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserDbModel(
	val email: String,
	val name: String,
	val isAuthorized: Boolean,
	val jwt: String,
	@PrimaryKey val id: Int = 1,
)