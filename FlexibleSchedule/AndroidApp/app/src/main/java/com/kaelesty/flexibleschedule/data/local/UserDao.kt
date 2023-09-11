package com.kaelesty.flexibleschedule.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kaelesty.flexibleschedule.data.entities.UserDbModel

@Dao
interface UserDao {

	@Query("SELECT * FROM user")
	fun getUser(): LiveData<UserDbModel?>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun saveUser(user: UserDbModel)
}