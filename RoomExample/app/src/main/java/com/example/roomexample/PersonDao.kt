package com.example.roomexample

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface PersonDao {
    @Query("SELECT * FROM person")
    fun getAll(): List<Person>

    @Insert
    fun insertAll(person: List<Person>)

    @Delete
    fun delete(person: Person)
}