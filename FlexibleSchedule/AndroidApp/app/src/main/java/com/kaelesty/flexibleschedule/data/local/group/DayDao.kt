package com.kaelesty.flexibleschedule.data.local.group
//
//import androidx.lifecycle.LiveData
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import com.kaelesty.flexibleschedule.data.entities.dbmodels.group.DayDbModel
//import com.kaelesty.flexibleschedule.data.entities.dtos.DayDto
//
//interface DayDao {
//
//	@Insert(onConflict = OnConflictStrategy.REPLACE)
//	fun addDay(day: DayDbModel)
//
//	@Query("SELECT * FROM days WHERE timetableId = :timetableId")
//	fun getDays(timetableId: Int): List<DayDbModel>
//
//	@Query("DELETE FROM days WHERE timetableId = :timetableId")
//	fun delDays(timetableId: Int)
//}