package com.kaelesty.flexibleschedule.data.entities

import com.google.gson.annotations.SerializedName

data class PairDto(
	@SerializedName("time") val time: String,
	@SerializedName("info") val info: String,
	@SerializedName("place") val place: String,
	@SerializedName("teacher") val teacher: String,
)