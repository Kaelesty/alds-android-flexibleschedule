package com.kaelesty.flexibleschedule.data.entities.dbmodels.group

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "days")
data class DayDbModel(
	@PrimaryKey val id: String,
	val timetableId: Int
)