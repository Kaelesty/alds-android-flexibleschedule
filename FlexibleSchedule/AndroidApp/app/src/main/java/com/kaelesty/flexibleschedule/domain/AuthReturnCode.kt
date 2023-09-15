package com.kaelesty.flexibleschedule.domain

enum class AuthReturnCode {
	RC_REGISTER_OK, // http 201
	RC_REGISTER_EMAIL_EXIST, // TODO Backend throws exception if we try to register existing email. fix?
	RC_REGISTER_UNKWOWN, // all other http RCs

	RC_LOGIN_OK, // http 200
	RC_LOGIN_PASSWORD, // http 400
	RC_LOGIN_EMAIL, // http 500
	RC_LOGIN_UNKWOWN, // all other http RCs
}