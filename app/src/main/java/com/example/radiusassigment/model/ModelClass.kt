package com.example.radiusassigment.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ModelClass(
    @PrimaryKey(autoGenerate = false)
    val exclusions: List<List<Exclusion>>,
    val facilities: List<Facility>
)
@Entity
data class Exclusion(
    @PrimaryKey(autoGenerate = false)
    val facility_id: String,
    val options_id: String
)
@Entity
data class Facility(
    @PrimaryKey(autoGenerate = false)
    val facility_id: String,
    val name: String,
    val options: List<Option>
)

@Entity
data class Option(
    @PrimaryKey(autoGenerate = false)
    val icon: String,
    val id: String,
    val name: String
)