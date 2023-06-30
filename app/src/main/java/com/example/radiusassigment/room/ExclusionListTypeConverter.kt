package com.example.radiusassigment.room

import androidx.room.TypeConverter
import com.example.radiusassigment.model.Exclusion
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ExclusionListTypeConverter {
    @TypeConverter
    fun fromExclusionList(value: List<List<Exclusion>>): String {
        val gson = Gson()
        return gson.toJson(value)
    }

    @TypeConverter
    fun toExclusionList(value: String): List<List<Exclusion>> {
        val gson = Gson()
        val type = object : TypeToken<List<List<Exclusion>>>() {}.type
        return gson.fromJson(value, type)
    }
}
