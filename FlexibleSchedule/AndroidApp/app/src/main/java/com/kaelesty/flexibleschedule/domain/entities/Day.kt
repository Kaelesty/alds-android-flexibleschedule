package com.kaelesty.flexibleschedule.domain.entities

data class Day(
	val timeTableId: Int,
	val pairs: List<Pair>
)