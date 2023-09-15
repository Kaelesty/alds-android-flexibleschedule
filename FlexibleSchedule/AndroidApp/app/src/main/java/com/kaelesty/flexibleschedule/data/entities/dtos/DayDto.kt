package com.kaelesty.flexibleschedule.data.entities.dtos

import com.google.gson.annotations.SerializedName

data class DayDto(
	@SerializedName("timeTableId") val timeTableId: Int,
	@SerializedName("pairs") val pairs: List<PairDto>
)