package com.kaelesty.flexibleschedule.domain

import com.kaelesty.flexibleschedule.data.entities.ResponseBody

class AuthUseCaseResult(val rc: AuthReturnCode,
						val body: ResponseBody? = null,
						val jwt: String? = null)