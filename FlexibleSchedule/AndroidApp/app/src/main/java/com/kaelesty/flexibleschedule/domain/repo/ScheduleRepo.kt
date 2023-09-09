package com.kaelesty.flexibleschedule.domain.repo

import com.kaelesty.flexibleschedule.domain.entities.Schedule

interface ScheduleRepo {

	fun getSchedule(group: String): Schedule
}