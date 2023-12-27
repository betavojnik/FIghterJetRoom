package com.example.fighterjetsroom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fighterjetsroom.databinding.FragmentHomeBinding
import com.example.fighterjetsroom.databinding.FragmentLandingBinding

class LandingFragment : Fragment(R.layout.fragment_landing) {

    private lateinit var binding: FragmentLandingBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLandingBinding.bind(view)



    }
}