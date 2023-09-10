package com.kaelesty.flexibleschedule.data.remote

import com.kaelesty.flexibleschedule.data.entities.ConnectGroupDto
import com.kaelesty.flexibleschedule.data.entities.GroupDto
import com.kaelesty.flexibleschedule.data.entities.UserGroupDto
import retrofit2.http.GET

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
	suspend fun getFullTimetable()

	@GET("GetAllGroupCodes")
	suspend fun getAllGroupCodes()

}