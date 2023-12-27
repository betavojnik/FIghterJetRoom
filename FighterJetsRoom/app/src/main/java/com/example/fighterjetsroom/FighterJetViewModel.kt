package com.example.fighterjetsroom

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fighterjetsroom.DB.FighterJetDB
import com.example.fighterjetsroom.DB.FighterJetRepository
import com.example.fighterjetsroom.model.FighterJet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FighterJetViewModel(application: Application) : AndroidViewModel(application) {

    private val repo: FighterJetRepository
    val test: MutableLiveData<List<FighterJet>> = MutableLiveData()


    init {
        val dao = FighterJetDB.getInstance(application).fighterDAO()
        repo = FighterJetRepository(dao)
        getFavourites()
    }

    fun addFavouriteJet(fighterJet: FighterJet) {
        viewModelScope.launch {
            repo.addFavouriteJet(fighterJet)
        }
    }

    fun getFavourites() {
        viewModelScope.launch {
            test.postValue(repo.getFavouriteJets())
        }
    }

    fun deleteALl() = viewModelScope.launch {
        repo.deleteAllFighterJets()
    }

    fun checkIfExists(id: Int, callback: (Boolean) -> Unit) {
        var jet: FighterJet?
        var job = viewModelScope.async {
            repo.findById(id)
        }
        job.invokeOnCompletion {

            jet = job.getCompleted()
            callback( jet != null)

        }

    }

    fun deleteFIghter(fighter: FighterJet) {
        viewModelScope.launch {
            repo.deleteFighter(fighter)
           // test.postValue(repo.getFavouriteJets())
        }
    }
}
