package com.kaelesty.flexibleschedule.domain.entities

data class Timetable(
	val id: Int,
	val days: List<Day>
)