package com.kaelesty.flexibleschedule.data.entities

import com.google.gson.annotations.SerializedName
import com.kaelesty.flexibleschedule.domain.entities.Day

data class DayDto(
	@SerializedName("timeTableId") val timeTableId: Int,
	@SerializedName("pairs") val pairs: List<PairDto>
)