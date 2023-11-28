package com.kaelesty.flexibleschedule.data.repos

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kaelesty.flexibleschedule.data.entities.dtos.ConnectGroupDto
import com.kaelesty.flexibleschedule.data.entities.dtos.FullTimetableDto
import com.kaelesty.flexibleschedule.data.entities.dtos.GroupDto
import com.kaelesty.flexibleschedule.data.local.auth.UserDatabase
//import com.kaelesty.flexibleschedule.data.local.group.DayDao
//import com.kaelesty.flexibleschedule.data.local.group.DayDatabase
//import com.kaelesty.flexibleschedule.data.local.group.PairDatabase
import com.kaelesty.flexibleschedule.data.local.group.TimetableDatabase
import com.kaelesty.flexibleschedule.data.mappers.GroupMapper
import com.kaelesty.flexibleschedule.data.remote.GroupServiceFactory
import com.kaelesty.flexibleschedule.domain.ConnectGroupReturnCode
import com.kaelesty.flexibleschedule.domain.GroupCodes
import com.kaelesty.flexibleschedule.domain.GroupCodesError
import com.kaelesty.flexibleschedule.domain.GroupCodesUseCaseResult
import com.kaelesty.flexibleschedule.domain.GroupReturnCode
import com.kaelesty.flexibleschedule.domain.SimpleReturnCode
import com.kaelesty.flexibleschedule.domain.entities.Day
import com.kaelesty.flexibleschedule.domain.entities.Timetable
import com.kaelesty.flexibleschedule.domain.entities.UserGroup
import com.kaelesty.flexibleschedule.domain.repo.IGroupRepo
import java.io.IOException

class GroupRepo(context: Context): IGroupRepo {

	private val groupService = GroupServiceFactory.apiService
	private val userDao = UserDatabase.getInstance(context).dao()
	private val timetableDao = TimetableDatabase.getInstance(context).dao()

	override fun getFullTimetable(): LiveData<Timetable> {
		// Backend always return 0 as timetable.id so we can store only one timetable
		// TODO: or we can link timetable id with current user?

		return timetableDao.getTimetable(0).map {
			it?.let {
				GroupMapper.Timetable_DbModelToDomain(it)
			} ?: Timetable(0, mutableListOf())
		}

	}

	override suspend fun uploadTimetable(name: String, timetable: Timetable): GroupReturnCode {
		val user = userDao.getStaticUser() ?: return GroupReturnCode.RC_UPLOAD_UNAUTH
		val cookies = user.jwt

		val response = groupService.createGroup(cookies, GroupDto(
			name, timetable
		))

		return when (response.code()) {
			200 -> GroupReturnCode.RC_UPLOAD_OK
			401 -> GroupReturnCode.RC_UPLOAD_UNAUTH
			400 -> GroupReturnCode.RC_UPLOAD_TO_MANY
			else -> GroupReturnCode.RC_UPLOAD_UNKWOWN
		}
	}

	override suspend fun connectGroup(code: String): ConnectGroupReturnCode {
		val user = userDao.getStaticUser() ?: return ConnectGroupReturnCode.GC_UNAUTH
		val cookies = user.jwt

		val response = groupService.connectToGroup(cookies, ConnectGroupDto(code))

		return when(response.code()) {
			200 -> ConnectGroupReturnCode.GC_OK
			401 -> ConnectGroupReturnCode.GC_NOTFOUND
			else -> ConnectGroupReturnCode.GC_UNKNOWN
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
	}

	override suspend fun deleteGroup(userGroup: UserGroup): SimpleReturnCode {

		try {
			val user = userDao.getStaticUser() ?: throw IOException()
			val cookies = user.jwt
			val response = groupService.deleteGroup(cookies, GroupMapper.UserGroup_DomainToDto(userGroup))
			if (response.code() == 200) {
				return SimpleReturnCode.OK
			}

			throw IOException()
		}
		catch (e: Exception) {
			Log.d("TAGTAG", e.message.toString())
			return SimpleReturnCode.UNKNOWN
		}
	}

	override suspend fun changePriority(userGroup: UserGroup): SimpleReturnCode {
		try {
			val user = userDao.getStaticUser() ?: throw IOException()
			val cookies = user.jwt
			val response = groupService.changePriority(cookies, GroupMapper.UserGroup_DomainToDto(userGroup))
			if (response.code() == 200) {
				return SimpleReturnCode.OK
			}

			throw IOException()
		}
		catch (e: Exception) {
			return SimpleReturnCode.UNKNOWN
		}
	}

	override suspend fun getAllGroupCodes(): GroupCodesUseCaseResult {
		try {
			val user = userDao.getStaticUser() ?: throw IOException()
			val cookies = user.jwt
			val response = groupService.getAllGroupCodes(cookies)
			if (response.code() == 200) {
				return response.body()?.let { it1 ->
					GroupCodes(
						it1.map { GroupMapper.UserGroup_DtoToDomain(it) }
					)
				} ?: throw IOException()
			}

			throw IOException()
		}
		catch (e: Exception) {
			return GroupCodesError()
		}
	}
}