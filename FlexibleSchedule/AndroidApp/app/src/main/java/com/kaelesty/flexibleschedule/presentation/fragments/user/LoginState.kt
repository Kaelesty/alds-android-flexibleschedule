package com.kaelesty.flexibleschedule.presentation.fragments.user

data class LoginState(
	val isVisible: Boolean,
	val email: String? = null,
	val password: String? = null,
	val toast: String? = null,
)