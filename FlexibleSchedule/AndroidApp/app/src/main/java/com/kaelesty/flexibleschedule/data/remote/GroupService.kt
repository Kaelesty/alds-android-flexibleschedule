package com.kaelesty.flexibleschedule.data.remote

import com.kaelesty.flexibleschedule.data.entities.dtos.ConnectGroupDto
import com.kaelesty.flexibleschedule.data.entities.dtos.FullTimetableDto
import com.kaelesty.flexibleschedule.data.entities.dtos.GroupDto
import com.kaelesty.flexibleschedule.data.entities.dtos.UserGroupDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface GroupService {

	@POST("CreateGroup")
	suspend fun createGroup(@Header("Cookie") cookie: String, @Body groupDto: GroupDto): Response<Unit>

	@GET("DeleteGroup")
	suspend fun deleteGroup(userGroupDto: UserGroupDto)

	@POST("ConnectToGroup")
	suspend fun connectToGroup(@Header("Cookie") cookie: String, @Body connectGroupDto: ConnectGroupDto): Response<Unit>

	@GET("ChangePriority")
	suspend fun changePriority(userGroupDto: UserGroupDto)

	@GET("GetFullTimetable")
	suspend fun getFullTimetable(@Header("Cookie") cookie: String): Response<FullTimetableDto>

	@GET("GetAllGroupCodes")
	suspend fun getAllGroupCodes()

}