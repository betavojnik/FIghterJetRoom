package com.example.fighterjetsroom.DB

import com.example.fighterjetsroom.FighterJetDAO.FighterJetDAO
import com.example.fighterjetsroom.model.FighterJet

class FighterJetRepository(private val dao: FighterJetDAO) {

        suspend fun getFavouriteJets() = dao.getFavouriteJets()

        suspend fun addFavouriteJet(fighterjet : FighterJet) = dao.insertJet(fighterjet)

        suspend fun deleteAllFighterJets() = dao.deleteAllFighterJets()

        suspend fun findById(id : Int) = dao.findById(id)

        suspend fun deleteFighter(fighter: FighterJet) = dao.deleteJet(fighter)
}