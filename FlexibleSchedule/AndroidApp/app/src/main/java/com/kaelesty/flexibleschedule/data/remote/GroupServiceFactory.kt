package com.kaelesty.flexibleschedule.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GroupServiceFactory {

	private const val URL = " https://14d0-193-32-202-60.ngrok-free.app" + "/api/Group/"

	val apiService: GroupService = Retrofit.Builder()
		.baseUrl(URL)
		.addConverterFactory(GsonConverterFactory.create())
		.build()
		.create(GroupService::class.java)
}