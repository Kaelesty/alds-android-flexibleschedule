package com.kaelesty.flexibleschedule.domain.repo

import androidx.lifecycle.LiveData
import com.kaelesty.flexibleschedule.domain.ConnectGroupReturnCode
import com.kaelesty.flexibleschedule.domain.GroupCodesUseCaseResult
import com.kaelesty.flexibleschedule.domain.GroupReturnCode
import com.kaelesty.flexibleschedule.domain.SimpleReturnCode
import com.kaelesty.flexibleschedule.domain.entities.Timetable
import com.kaelesty.flexibleschedule.domain.entities.UserGroup

interface IGroupRepo {

	fun getFullTimetable(): LiveData<Timetable>

	suspend fun updateTimetable()

	suspend fun uploadTimetable(name: String, timetable: Timetable): GroupReturnCode

	suspend fun connectGroup(code: String): ConnectGroupReturnCode

	suspend fun deleteGroup(userGroup: UserGroup): SimpleReturnCode

	suspend fun changePriority(userGroup: UserGroup): SimpleReturnCode

	suspend fun getAllGroupCodes(): GroupCodesUseCaseResult
}