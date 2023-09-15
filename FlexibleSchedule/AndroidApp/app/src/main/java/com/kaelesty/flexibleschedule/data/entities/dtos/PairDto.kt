package com.kaelesty.flexibleschedule.data.entities.dtos

import com.google.gson.annotations.SerializedName

data class PairDto(
	@SerializedName("id") val id: Int,
	@SerializedName("time") val time: String,
	@SerializedName("info") val info: String,
	@SerializedName("place") val place: String,
	@SerializedName("teacher") val teacher: String,
)