package com.kaelesty.flexibleschedule.data.entities.dtos.auth

data class RegisterDto(
	val name: String,
	val email: String,
	val password: String,
)