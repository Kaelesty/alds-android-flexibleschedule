package com.kaelesty.flexibleschedule.data.remote

import androidx.lifecycle.LiveData
import com.kaelesty.flexibleschedule.data.entities.ConnectGroupDto
import com.kaelesty.flexibleschedule.data.entities.FullTimetableDto
import com.kaelesty.flexibleschedule.data.entities.GroupDto
import com.kaelesty.flexibleschedule.data.entities.UserGroupDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface GroupService {

	@GET("CreateGroup")
	suspend fun createGroup(groupDto: GroupDto)

	@GET("DeleteGroup")
	suspend fun deleteGroup(userGroupDto: UserGroupDto)

	@GET("ConnectToGroup")
	suspend fun connectToGroup(connectGroupDto: ConnectGroupDto)

	@GET("ChangePriority")
	suspend fun changePriority(userGroupDto: UserGroupDto)

	@GET("GetFullTimetable")
	suspend fun getFullTimetable(@Header("Cookie") cookie: String): Response<FullTimetableDto>
	// I get jwt token> but where i should pass it?

	@GET("GetAllGroupCodes")
	suspend fun getAllGroupCodes()

}