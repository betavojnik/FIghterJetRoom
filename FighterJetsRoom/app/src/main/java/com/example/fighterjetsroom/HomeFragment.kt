package com.example.fighterjetsroom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fighterjetsroom.DB.fighterJets
import com.example.fighterjetsroom.adapter.FighterJetAdapter
import com.example.fighterjetsroom.databinding.FragmentHomeBinding
import com.example.fighterjetsroom.model.FighterJet


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    lateinit var fighterAdapter: FighterJetAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)



        setupRecyclerView()
        fighterAdapter.differ.submitList(fighterJets.getAllJets())

        fighterAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("fighterJet", it)
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_detailedDescriptionFragment,
                bundle
            )

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
