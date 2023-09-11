package com.kaelesty.flexibleschedule.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kaelesty.flexibleschedule.data.entities.UserDbModel

@Database(entities = [UserDbModel::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {

	abstract fun dao(): UserDao

	companion object {

		private var instance: UserDatabase? = null
		private const val DB_NAME = "user_db"

		private val LOCK = Any()

		fun getInstance(context: Context): UserDatabase {
			instance?.let {
				return it
			}
			synchronized(LOCK) {
				instance?.let {
					return it
				}
				val db = Room.databaseBuilder(
					context,
					UserDatabase::class.java,
					DB_NAME
				).build()
				instance = db
				return db
			}
		}
	}
}