package com.kaelesty.flexibleschedule.data.remote

import com.kaelesty.flexibleschedule.data.entities.LoginDto
import com.kaelesty.flexibleschedule.data.entities.RegisterDto
import com.kaelesty.flexibleschedule.data.entities.UserResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {


	@POST("Login")
	suspend fun login(@Body loginDto: LoginDto): Response<UserResponse>

	@POST("Logout")
	suspend fun logout()

	@POST("Register")
	suspend fun register(@Body registerDto: RegisterDto): Response<RegisterDto>
}