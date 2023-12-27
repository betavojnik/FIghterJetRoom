package com.example.fighterjetsroom.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class FighterJet(
    val name: String,
    val urlImage: String,
    val description: String,
    val numberOfRockets: Int,
    val fuelCapacity: Double,
    val maxSpeed: Double,
    @PrimaryKey(autoGenerate = false)
    val id: Int) : Serializable {
    override fun toString(): String {
        return id.toString() + " " + name
    }
    }

