package com.kaelesty.flexibleschedule.data.local.group
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.kaelesty.flexibleschedule.data.entities.dbmodels.group.TimetableDbModel
//import com.kaelesty.flexibleschedule.data.entities.dtos.DayDto
//
//@Database(
//	entities = [DayDto::class], version = 1, exportSchema = false,
//)
//abstract class DayDatabase : RoomDatabase() {
//
//	abstract fun dao(): DayDao
//
//	companion object {
//
//		private var instance: DayDatabase? = null
//		private const val DB_NAME = "day-db"
//
//		private val LOCK = Any()
//
//		fun getInstance(context: Context): DayDatabase {
//			instance?.let {
//				return it
//			}
//			synchronized(LOCK) {
//				instance?.let {
//					return it
//				}
//				val db = Room.databaseBuilder(
//					context,
//					DayDatabase::class.java,
//					DB_NAME
//				).build()
//				instance = db
//				return db
//			}
//		}
//	}
//}