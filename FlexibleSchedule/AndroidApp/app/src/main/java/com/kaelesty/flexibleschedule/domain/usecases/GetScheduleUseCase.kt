package com.kaelesty.flexibleschedule.domain.usecases

import com.kaelesty.flexibleschedule.domain.entities.Schedule
import com.kaelesty.flexibleschedule.domain.repo.ScheduleRepo

class GetScheduleUseCase(val repo: ScheduleRepo) {

	fun getSchedule(group: String): Schedule {
		return repo.getSchedule(group)
	}
}