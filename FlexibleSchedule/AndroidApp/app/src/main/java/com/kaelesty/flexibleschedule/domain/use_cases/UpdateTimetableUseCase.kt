package com.kaelesty.flexibleschedule.domain.use_cases

import com.kaelesty.flexibleschedule.domain.repo.IGroupRepo

class UpdateTimetableUseCase(private val repo: IGroupRepo) {

	suspend fun updateTimetable() {
		repo.updateTimetable()
	}
}