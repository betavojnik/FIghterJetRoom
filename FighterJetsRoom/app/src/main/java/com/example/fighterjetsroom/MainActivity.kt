package com.example.fighterjetsroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.fighterjetsroom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var binding: ActivityMainBinding
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*      val f: Fragment? = supportFragmentManager.findFragmentById(R.id.NavHostFragment)
              binding.bottomMenu.setupWithNavController(f!!.findNavController())*/

        binding.bottomMenu.setupWithNavController(findNavController(R.id.Host))

        findNavController(R.id.Host).addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.splashFragment) {

                binding.bottomMenu.visibility = View.GONE
            } else {
                binding.bottomMenu.visibility = View.VISIBLE
            }


        }
    }
}


