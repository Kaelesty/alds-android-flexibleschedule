package com.kaelesty.flexibleschedule.data.mappers

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kaelesty.flexibleschedule.data.entities.dbmodels.group.DayDbModel
import com.kaelesty.flexibleschedule.data.entities.dbmodels.group.PairDbModel
import com.kaelesty.flexibleschedule.data.entities.dbmodels.group.TimetableDbModel
import com.kaelesty.flexibleschedule.data.entities.dtos.DayDto
import com.kaelesty.flexibleschedule.data.entities.dtos.FullTimetableDto
import com.kaelesty.flexibleschedule.data.entities.dtos.PairDto
import com.kaelesty.flexibleschedule.domain.entities.Day
import com.kaelesty.flexibleschedule.domain.entities.Pair
import com.kaelesty.flexibleschedule.domain.entities.Timetable

object GroupMapper {

	fun Timetable_DtoToDbModel(dto: FullTimetableDto): TimetableDbModel {
		val daysJson: String = Gson().toJson(dto.days)
		return TimetableDbModel(
			dto.id, daysJson
		)
	}

	fun Timetable_DbModelToDomain(dbModel: TimetableDbModel): Timetable {
		val listType = object : TypeToken<List<Day>>(){}.type
		val days = Gson().fromJson<List<Day>>(dbModel.daysJson, listType)
		return Timetable(dbModel.id, days)
	}

	fun Day_DtoToDbModel(dto: DayDto, id: String) = DayDbModel(
		id,
		dto.timeTableId
	)


	fun Pair_DtoToDbModel(dto: PairDto, dayId: String) = PairDbModel(
		dto.id,
		dayId,
		dto.time, dto.info, dto.place, dto.teacher
	)

	fun Pair_DbModelToEntity(dbModel: PairDbModel) = Pair(
		dbModel.time,
		dbModel.info,
		dbModel.place,
		dbModel.teacher
	)
}