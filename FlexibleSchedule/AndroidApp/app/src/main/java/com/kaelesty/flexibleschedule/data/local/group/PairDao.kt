package com.kaelesty.flexibleschedule.data.local.group
//
//import androidx.lifecycle.LiveData
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import com.kaelesty.flexibleschedule.data.entities.dbmodels.group.PairDbModel
//
//interface PairDao {
//
//	@Insert(onConflict = OnConflictStrategy.REPLACE)
//	fun addPair(pair: PairDbModel)
//
//	@Query("SELECT * FROM pairs WHERE dayId = :dayId")
//	fun getPairs(dayId: String): LiveData<List<PairDbModel>>
//
//	@Query("DELETE FROM pairs WHERE dayId = :dayId")
//	fun delPairs(dayId: String)
//}