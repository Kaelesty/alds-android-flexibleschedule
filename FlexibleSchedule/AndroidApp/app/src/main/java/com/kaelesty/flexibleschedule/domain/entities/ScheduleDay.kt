package com.kaelesty.flexibleschedule.domain.entities

data class ScheduleDay(
	val day: Week,
	val items: List<ScheduleItem>
)