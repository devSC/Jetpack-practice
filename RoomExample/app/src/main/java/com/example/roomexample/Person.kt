package com.example.roomexample
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Person (
        @PrimaryKey var id: String,
        var name: String,
        var age: Int,
        var sex: String
)