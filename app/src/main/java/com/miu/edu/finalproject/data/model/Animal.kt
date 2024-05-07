package com.miu.edu.finalproject.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "animals",
    indices = [Index(value = ["id"], unique = true)])
data class Animal(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "habitat")
    val habitat: String,

    @ColumnInfo(name = "diet")
    val diet: String
) {
    constructor(name: String, habitat: String, diet: String) : this(0, name, habitat, diet)
}