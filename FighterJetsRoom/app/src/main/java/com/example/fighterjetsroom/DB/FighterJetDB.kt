package com.example.fighterjetsroom.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fighterjetsroom.FighterJetDAO.FighterJetDAO
import com.example.fighterjetsroom.model.FighterJet

@Database(
    entities = [
        FighterJet::class
    ],
    version = 1
)


abstract class FighterJetDB : RoomDatabase() {

    abstract fun fighterDAO(): FighterJetDAO

    companion object {
        @Volatile
        private var INSTANCE: FighterJetDB? = null

        fun getInstance(context: Context): FighterJetDB {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    FighterJetDB::class.java,
                    "FighterJetDB"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}