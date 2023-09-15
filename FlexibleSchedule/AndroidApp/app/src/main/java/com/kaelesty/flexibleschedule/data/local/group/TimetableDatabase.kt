package com.kaelesty.flexibleschedule.data.local.group

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kaelesty.flexibleschedule.data.entities.dbmodels.auth.UserDbModel
import com.kaelesty.flexibleschedule.data.entities.dbmodels.group.TimetableDbModel
import com.kaelesty.flexibleschedule.data.local.auth.UserDao

@Database(
	entities = [TimetableDbModel::class], version = 1, exportSchema = false,
)
abstract class TimetableDatabase : RoomDatabase() {

	abstract fun dao(): TimetableDao

	companion object {

		private var instance: TimetableDatabase? = null
		private const val DB_NAME = "timetable-db"

		private val LOCK = Any()

		fun getInstance(context: Context): TimetableDatabase {
			instance?.let {
				return it
			}
			synchronized(LOCK) {
				instance?.let {
					return it
				}
				val db = Room.databaseBuilder(
					context,
					TimetableDatabase::class.java,
					DB_NAME
				).build()
				instance = db
				return db
			}
		}
	}
}