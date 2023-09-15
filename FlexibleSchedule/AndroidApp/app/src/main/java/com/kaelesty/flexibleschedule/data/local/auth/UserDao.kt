package com.kaelesty.flexibleschedule.data.local.auth

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kaelesty.flexibleschedule.data.entities.dbmodels.auth.UserDbModel

@Dao
interface UserDao {

	@Query("SELECT * FROM user")
	fun getUser(): LiveData<UserDbModel?>

	@Query("SELECT * FROM user")
	fun getStaticUser(): UserDbModel?

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun saveUser(user: UserDbModel)
}