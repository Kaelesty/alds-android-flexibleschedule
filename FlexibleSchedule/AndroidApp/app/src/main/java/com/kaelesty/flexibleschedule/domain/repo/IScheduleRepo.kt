package com.kaelesty.flexibleschedule.domain.repo

import com.kaelesty.flexibleschedule.domain.entities.Group
import com.kaelesty.flexibleschedule.domain.entities.Timetable
import com.kaelesty.flexibleschedule.domain.entities.UserGroup

interface IScheduleRepo {

	suspend fun createGroup(group: Group)

	suspend fun deleteGroup(group: UserGroup)

	suspend fun connectToGroup(groupCode: String)

	suspend fun changePriority(group: UserGroup)

	suspend fun getFullTimetable(): Timetable

	suspend fun getAllGroupCodes(): List<UserGroup>
}