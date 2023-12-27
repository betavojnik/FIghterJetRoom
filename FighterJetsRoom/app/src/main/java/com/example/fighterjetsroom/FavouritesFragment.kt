package com.example.fighterjetsroom

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fighterjetsroom.DB.fighterJets
import com.example.fighterjetsroom.adapter.FighterJetAdapter
import com.example.fighterjetsroom.databinding.FragmentFavouritesBinding
import com.example.fighterjetsroom.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar

class FavouritesFragment : Fragment(R.layout.fragment_favourites) {
    private lateinit var binding: FragmentFavouritesBinding

    lateinit var fighterAdapter: FighterJetAdapter
    lateinit var viewModel: FighterJetViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFavouritesBinding.bind(view)


        viewModel = ViewModelProvider(this).get(FighterJetViewModel::class.java)
        setupRecyclerView()
        viewModel.getFavourites()

        viewModel.test.observe(viewLifecycleOwner) { fighterJets ->
            Toast.makeText(requireActivity(), fighterJets.toString(), Toast.LENGTH_LONG).show()
                fighterAdapter.differ.submitList(fighterJets)
        }

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val fighter = fighterAdapter.differ.currentList[position]
        //        viewModel.deleteFIghter(fighter)
                Snackbar.make(view, "Deleted successfully", Snackbar.LENGTH_LONG). apply {
                    setAction("Undo") {
                       viewModel.addFavouriteJet(fighter)

                        viewModel.getFavourites()
                    }
                    show()
                }
            }
        }
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.adapter)
        }
    }


    private fun setupRecyclerView() {
        fighterAdapter = FighterJetAdapter()
        binding.adapter.apply {
            adapter = fighterAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}
