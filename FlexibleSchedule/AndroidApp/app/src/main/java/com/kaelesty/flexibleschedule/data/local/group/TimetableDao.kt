package com.kaelesty.flexibleschedule.data.local.group

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kaelesty.flexibleschedule.data.entities.dbmodels.group.TimetableDbModel

@Dao
interface TimetableDao {

	@Query("SELECT * FROM timetable WHERE id = :id")
	fun getTimetable(id: Int): LiveData<TimetableDbModel?>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun addTimetable(timetable: TimetableDbModel)

	@Query("DELETE FROM timetable WHERE id = :id")
	fun delTimetable(id: Int)
}