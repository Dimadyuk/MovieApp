package com.example.movieapp.data.mapper

import androidx.room.TypeConverter

class ListConverter {

    @TypeConverter
    fun fromString(value: String?): List<Int>? {
        return value?.let {
            it.split(",").map { it.toInt() }
        }
    }

    @TypeConverter
    fun toString(list: List<Int>?): String? {
        return list?.joinToString(",")
    }
}