package com.example.fighterjetsroom

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.setViewTreeOnBackPressedDispatcherOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.fighterjetsroom.DB.FighterJetDB
import com.example.fighterjetsroom.DB.fighterJets
import com.example.fighterjetsroom.databinding.FragmentDetailedDescriptionBinding
import com.example.fighterjetsroom.model.FighterJet
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match

class DetailedDescriptionFragment : Fragment(R.layout.fragment_detailed_description) {

    lateinit var binding: FragmentDetailedDescriptionBinding
    val args: DetailedDescriptionFragmentArgs by navArgs()
    lateinit var viewModel: FighterJetViewModel
    var exists: Boolean = true


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {

        }

        binding = FragmentDetailedDescriptionBinding.bind(view)
        val fighterjet = args.fighterJet
        viewModel = ViewModelProvider(this).get(FighterJetViewModel::class.java)

        Glide.with(this).load(fighterjet.urlImage).into(binding.imageView)
        binding.textView.text = fighterjet.description
        binding.tvMaxSpeed.text = "Fighter jat max speed: ${fighterjet.maxSpeed} km/h"

        viewModel.checkIfExists(args.fighterJet.id) { rez ->
            exists = rez
            Log.i("random", "vrijednost exista je: $exists")
            if (exists) {
                binding.floatingActionButton.setImageResource(R.drawable.ic_favourite)
            } else binding.floatingActionButton.setImageResource(R.drawable.favourite)


        }

        binding.floatingActionButton.setOnClickListener {
            if (exists)  {
                viewModel.deleteFIghter(fighterjet)
                binding.floatingActionButton.setImageResource(R.drawable.favourite)
            } else {
                viewModel.addFavouriteJet(fighterjet)
                binding.floatingActionButton.setImageResource(R.drawable.ic_favourite)
            }
        }



    }

    private fun test() {

        viewModel.checkIfExists(args.fighterJet.id) { rez ->
            exists = rez
            Log.i("random", "vrijednost exista je: $exists")


        }
    }
}
