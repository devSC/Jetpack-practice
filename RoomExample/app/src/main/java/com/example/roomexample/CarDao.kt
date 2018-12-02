package com.example.roomexample

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface CarDao {
    @Query("SELECT * FROM cars")
    fun getAll(): List<Car>

    @Insert
    fun insertAll(cars: List<Car>)

    @Query("SELECT * FROM cars where hid = :hid ORDER BY id ASC")
    fun findCarsBy(hid: String): List<Car>
}