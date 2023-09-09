package com.kaelesty.flexibleschedule.domain.entities

data class ScheduleItem(
	val time: String,
	val info: String,
	val location: String,
	val teacher: String
)