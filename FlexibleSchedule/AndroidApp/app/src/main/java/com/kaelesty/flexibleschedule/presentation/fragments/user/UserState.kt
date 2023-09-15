package com.kaelesty.flexibleschedule.presentation.fragments.user

sealed class UserState

class StateUnauthorized : UserState()

class StateRegisterError(
	val toastMessage: String? = null,
	val emailMessage: String? = null,
	val nameMessage: String? = null,
	val passwordMessage: String? = null
) : UserState()

class StateLoginError(
	val toastMessage: String? = null,
	val emailMessage: String? = null,
	val passwordMessage: String? = null
) : UserState()

class StateAuthorized(val name: String, val email: String) : UserState()

class StateLoading: UserState()