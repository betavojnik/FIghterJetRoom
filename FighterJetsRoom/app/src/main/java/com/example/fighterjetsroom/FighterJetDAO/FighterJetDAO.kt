package com.example.fighterjetsroom.FighterJetDAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.fighterjetsroom.model.FighterJet

@Dao
interface FighterJetDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJet(jet: FighterJet)

    @Delete
    suspend fun deleteJet(jet: FighterJet)

    @Query("SELECT * FROM fighterjet")
    suspend fun getFavouriteJets(): List<FighterJet>

    @Query("DELETE FROM fighterjet")
    suspend fun deleteAllFighterJets()

    @Query("SELECT * FROM fighterjet WHERE id = :id ")
    suspend fun findById(id : Int) : FighterJet?

}