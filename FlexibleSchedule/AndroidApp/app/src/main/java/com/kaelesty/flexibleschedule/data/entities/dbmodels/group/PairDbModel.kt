package com.kaelesty.flexibleschedule.data.entities.dbmodels.group

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity("pairs")
data class PairDbModel(
	@PrimaryKey val id: Int,
	val dayId: String,
	val time: String,
	val info: String,
	val place: String,
	val teacher: String,
)
