package com.kaelesty.flexibleschedule.domain.use_cases

import com.kaelesty.flexibleschedule.domain.GroupReturnCode
import com.kaelesty.flexibleschedule.domain.entities.Timetable
import com.kaelesty.flexibleschedule.domain.repo.IGroupRepo

class UploadTimetableUseCase(private val repo: IGroupRepo) {

	suspend fun upload(name: String, timetable: Timetable): GroupReturnCode {
		return repo.uploadTimetable(name, timetable)
	}

}