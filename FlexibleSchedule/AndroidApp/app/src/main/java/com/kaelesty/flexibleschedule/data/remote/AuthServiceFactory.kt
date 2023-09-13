package com.kaelesty.flexibleschedule.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AuthServiceFactory {

	private const val URL = "https://eece-193-32-202-124.ngrok-free.app/api/Auth/"

	val apiService: AuthService = Retrofit.Builder()
		.baseUrl(URL)
		.addConverterFactory(GsonConverterFactory.create())
		.build()
		.create(AuthService::class.java)
}