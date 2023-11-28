package com.kaelesty.flexibleschedule.data.entities

import com.google.gson.annotations.SerializedName

data class UserResponse(
	@SerializedName("name") val name: String,
	@SerializedName("email") val email: String,
): ResponseBody()