package com.kaelesty.flexibleschedule.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserDbModel(
	val email: String,
	val name: String,
	val isAuthorized: Boolean,
	@PrimaryKey val id: Int = 1,
)