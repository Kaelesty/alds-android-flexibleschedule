package com.kaelesty.flexibleschedule.presentation.fragments.user

data class LogoutState(
	val isVisible: Boolean,
	val email: String? = null,
	val name: String? = null,
)