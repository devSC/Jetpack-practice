package com.example.roomexample

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "cars",
        foreignKeys = arrayOf(
                ForeignKey(entity = Person::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("hid"),
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE)
        )
)
data class Car(
       @PrimaryKey var id: String,
       var mark: String,
       var price: Double,
       var hid: String
)