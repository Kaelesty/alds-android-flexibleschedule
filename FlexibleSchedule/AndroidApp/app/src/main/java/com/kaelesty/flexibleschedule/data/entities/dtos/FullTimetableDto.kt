package com.kaelesty.flexibleschedule.data.entities.dtos

import com.google.gson.annotations.SerializedName


data class FullTimetableDto(
	@SerializedName("id") val id: Int,
	@SerializedName("days") val days: List<DayDto>
)