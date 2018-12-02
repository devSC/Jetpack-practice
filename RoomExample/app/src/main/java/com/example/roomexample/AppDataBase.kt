package com.example.roomexample

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(Person::class, Car::class), version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun personDao(): PersonDao
    abstract fun carDao(): CarDao
}