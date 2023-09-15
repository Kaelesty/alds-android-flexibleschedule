package com.kaelesty.flexibleschedule.data.repos

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kaelesty.flexibleschedule.data.entities.dtos.FullTimetableDto
import com.kaelesty.flexibleschedule.data.local.auth.UserDatabase
//import com.kaelesty.flexibleschedule.data.local.group.DayDao
//import com.kaelesty.flexibleschedule.data.local.group.DayDatabase
//import com.kaelesty.flexibleschedule.data.local.group.PairDatabase
import com.kaelesty.flexibleschedule.data.local.group.TimetableDatabase
import com.kaelesty.flexibleschedule.data.mappers.GroupMapper
import com.kaelesty.flexibleschedule.data.remote.GroupServiceFactory
import com.kaelesty.flexibleschedule.domain.entities.Day
import com.kaelesty.flexibleschedule.domain.entities.Timetable
import com.kaelesty.flexibleschedule.domain.repo.IGroupRepo

class GroupRepo(context: Context): IGroupRepo {

	private val groupService = GroupServiceFactory.apiService
	private val userDao = UserDatabase.getInstance(context).dao()
	private val timetableDao = TimetableDatabase.getInstance(context).dao()
//	private val dayDao: DayDao = DayDatabase.getInstance(context).dao()
//	private val pairDao = PairDatabase.getInstance(context).dao()

	override fun getFullTimetable(): LiveData<Timetable> {
		// Backend always return 0 as timetable.id so we can store only one timetable
		// TODO: or we can link timetable id with current user?

		return timetableDao.getTimetable(0).map {
			it?.let {
				GroupMapper.Timetable_DbModelToDomain(it)
			} ?: Timetable(0, mutableListOf())
		}

	}

	override suspend fun updateTimetable() {
		val user = userDao.getStaticUser() ?: return
		val cookies = user.jwt
		val response = groupService.getFullTimetable(cookies)
		val timetableDto = response.body() ?: return

		timetableDao.addTimetable(
			GroupMapper.Timetable_DtoToDbModel(timetableDto)
		)
//		var counter = -1
//		for (day in timetableDto.days) {
//			counter++
//			val dayId = timetableDto.id.toString() + "_" + counter.toString()
//			dayDao.addDay(
//				GroupMapper.Day_DtoToDbModel(day, dayId)
//			)
//			for (pair in day.pairs) {
//
//				pairDao.addPair(
//					GroupMapper.Pair_DtoToDbModel(pair, dayId)
//				)
//			}
//		}
	}
}