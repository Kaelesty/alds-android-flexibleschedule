package com.kaelesty.flexibleschedule.presentation.fragments.user

sealed class UserState

class UserStateUnauthorized : UserState()

class UserStateRegisterError(
	val toastMessage: String? = null,
	val emailMessage: String? = null,
	val nameMessage: String? = null,
	val passwordMessage: String? = null
) : UserState()

class UserStateLoginError(
	val toastMessage: String? = null,
	val emailMessage: String? = null,
	val passwordMessage: String? = null
) : UserState()

class UserStateAuthorized(val name: String, val email: String) : UserState()

class UserStateLoading: UserState()