package com.kaelesty.flexibleschedule.data

import com.kaelesty.flexibleschedule.domain.entities.Schedule
import com.kaelesty.flexibleschedule.domain.entities.ScheduleDay
import com.kaelesty.flexibleschedule.domain.entities.ScheduleItem
import com.kaelesty.flexibleschedule.domain.entities.Week
import com.kaelesty.flexibleschedule.domain.repo.ScheduleRepo
import kotlin.random.Random

class PseudoRepo: ScheduleRepo {

	override fun getSchedule(group: String): Schedule {
		return Schedule(
			"4215",
			arrayListOf(
				getRandomScheduleDay(Week.Monday),
				getRandomScheduleDay(Week.Tuesday),
				getRandomScheduleDay(Week.Wednesday),
				getRandomScheduleDay(Week.Thursday),
				getRandomScheduleDay(Week.Friday),
				getRandomScheduleDay(Week.Saturday),
				getRandomScheduleDay(Week.Sunday),
			)
		)
	}

	private fun getRandomScheduleDay(day: Week): ScheduleDay {
		return ScheduleDay(
			day,
			arrayListOf(
				getRandomScheduleItem(),
				getRandomScheduleItem(),
				getRandomScheduleItem(),
			)
		)
	}

	private fun getRandomScheduleItem(): ScheduleItem {
		return ScheduleItem(
			"time ${Random.nextInt()}",
			"info ${Random.nextInt()}",
			"loca ${Random.nextInt()}",
			"teacher ${Random.nextInt()}"
		)
	}
}