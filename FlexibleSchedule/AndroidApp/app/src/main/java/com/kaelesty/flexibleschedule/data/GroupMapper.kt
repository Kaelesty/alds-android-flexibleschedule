package com.kaelesty.flexibleschedule.data

import com.kaelesty.flexibleschedule.data.entities.DayDto
import com.kaelesty.flexibleschedule.data.entities.FullTimetableDto
import com.kaelesty.flexibleschedule.data.entities.PairDto
import com.kaelesty.flexibleschedule.domain.entities.Day
import com.kaelesty.flexibleschedule.domain.entities.Pair
import com.kaelesty.flexibleschedule.domain.entities.Timetable

object GroupMapper {

	fun dtoToTimetable(dto: FullTimetableDto): Timetable {
		return Timetable(
			dto.id, dto.days.map { dtoToDay(it) }
		)
	}

	fun dtoToDay(dto: DayDto): Day {
		return Day(
			dto.timeTableId,
			dto.pairs.map { dtoToPair(it) }
		)
	}

	fun dtoToPair(dto: PairDto): Pair {
		return Pair(
			dto.time,
			dto.info,
			dto.place,
			dto.teacher,
		)
	}
}