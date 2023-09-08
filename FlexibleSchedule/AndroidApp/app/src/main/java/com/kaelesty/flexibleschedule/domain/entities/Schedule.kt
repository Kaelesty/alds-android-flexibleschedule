package com.kaelesty.flexibleschedule.domain.entities

data class Schedule(
	val group: String,
	val schedule: List<ScheduleDay>
)