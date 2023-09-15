package com.kaelesty.flexibleschedule.data.local.group
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.kaelesty.flexibleschedule.data.entities.dtos.DayDto
//import com.kaelesty.flexibleschedule.data.entities.dtos.PairDto
//
//@Database(
//	entities = [PairDto::class], version = 1, exportSchema = false,
//)
//abstract class PairDatabase : RoomDatabase() {
//
//	abstract fun dao(): PairDao
//
//	companion object {
//
//		private var instance: PairDatabase? = null
//		private const val DB_NAME = "day-db"
//
//		private val LOCK = Any()
//
//		fun getInstance(context: Context): PairDatabase {
//			instance?.let {
//				return it
//			}
//			synchronized(LOCK) {
//				instance?.let {
//					return it
//				}
//				val db = Room.databaseBuilder(
//					context,
//					PairDatabase::class.java,
//					DB_NAME
//				).build()
//				instance = db
//				return db
//			}
//		}
//	}
//}