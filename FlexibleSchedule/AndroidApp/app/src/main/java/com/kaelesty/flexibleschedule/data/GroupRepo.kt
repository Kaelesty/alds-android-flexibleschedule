package com.kaelesty.flexibleschedule.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kaelesty.flexibleschedule.data.entities.FullTimetableDto
import com.kaelesty.flexibleschedule.data.local.UserDatabase
import com.kaelesty.flexibleschedule.data.remote.AuthServiceFactory
import com.kaelesty.flexibleschedule.data.remote.GroupServiceFactory
import com.kaelesty.flexibleschedule.domain.entities.Timetable
import com.kaelesty.flexibleschedule.domain.repo.IGroupRepo

class GroupRepo(context: Context): IGroupRepo {

	private val groupService = GroupServiceFactory.apiService
	private val dao = UserDatabase.getInstance(context).dao()

	override fun getFillTimetable(): LiveData<Timetable> {
		TODO("return from database")
	}

	suspend fun getTimetable(): Timetable {
		val cookies = dao.getStaticUser().jwt
		val response = groupService.getFullTimetable(cookies)
		val timetableDto = response.body() ?: FullTimetableDto(0, mutableListOf())
		return GroupMapper.dtoToTimetable(timetableDto)
	}
}