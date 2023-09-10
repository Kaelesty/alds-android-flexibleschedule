package com.kaelesty.flexibleschedule.domain.entities

data class Day(
	val id: Int,
	val timeTableId: Int,
	val pairs: List<Pair>
)