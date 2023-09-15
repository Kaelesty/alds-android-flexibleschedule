package com.kaelesty.flexibleschedule.data.entities.dbmodels.group

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "timetable")
data class TimetableDbModel(
	@PrimaryKey val id: Int,
	val daysJson: String
)