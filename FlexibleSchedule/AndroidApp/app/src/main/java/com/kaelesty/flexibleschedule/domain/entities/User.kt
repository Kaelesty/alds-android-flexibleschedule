package com.kaelesty.flexibleschedule.domain.entities

data class User(
	val name: String,
	val email: String,
	val isAuthorized: Boolean,
)