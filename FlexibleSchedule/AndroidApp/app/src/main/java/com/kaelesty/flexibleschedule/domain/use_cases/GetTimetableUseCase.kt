package com.kaelesty.flexibleschedule.domain.use_cases

import com.kaelesty.flexibleschedule.domain.repo.IGroupRepo

class GetTimetableUseCase(val repo: IGroupRepo) {

	fun getTimetable() = repo.getFullTimetable()
}