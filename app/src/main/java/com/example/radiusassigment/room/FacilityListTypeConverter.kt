package com.example.radiusassigment.room

import androidx.room.TypeConverter
import com.example.radiusassigment.model.Facility
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FacilityListTypeConverter {
    @TypeConverter
    fun fromFacilityList(value: List<Facility>): String {
        val gson = Gson()
        return gson.toJson(value)
    }

    @TypeConverter
    fun toFacilityList(value: String): List<Facility> {
        val gson = Gson()
        val type = object : TypeToken<List<Facility>>() {}.type
        return gson.fromJson(value, type)
    }
}
