package com.android.imageshowing.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.android.imageshowing.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureAndShowFragment()
    }

    private fun configureAndShowFragment() {
        val bundle = intent.extras
        val navHost =
            supportFragmentManager.findFragmentById(R.id.navHostMainFragment) as NavHostFragment?
        supportFragmentManager.beginTransaction().setPrimaryNavigationFragment(navHost).commit()

        val navController = findNavController(R.id.navHostMainFragment)
        val navGraph = navController.navInflater.inflate(R.navigation.main_navigation)
        navController.setGraph(navGraph, bundle)
    }
}