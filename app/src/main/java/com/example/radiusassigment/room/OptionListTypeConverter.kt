package com.example.radiusassigment.room

import androidx.room.TypeConverter
import com.example.radiusassigment.model.Exclusion
import com.example.radiusassigment.model.Option
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class OptionListTypeConverter {
    @TypeConverter
    fun fromOptionList(value: List<Option>): String {
        val gson = Gson()
        return gson.toJson(value)
    }

    @TypeConverter
    fun toOptionList(value: String): List<Option> {
        val gson = Gson()
        val type = object : TypeToken<List<Option>>() {}.type
        return gson.fromJson(value, type)
    }
}