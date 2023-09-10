package com.kaelesty.flexibleschedule.presentation.fragments.user

data class RegisterState(
	val isVisible: Boolean,
	val email: String? = null,
	val name: String? = null,
	val password: String? = null,
	val toast: String? = null,
)