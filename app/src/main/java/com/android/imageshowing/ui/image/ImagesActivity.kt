package com.android.imageshowing.ui.image

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.android.imageshowing.R

class ImagesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_images)
        configureAndShowFragment()
    }

    private fun configureAndShowFragment() {
        val bundle = intent.extras
        val navHost =
            supportFragmentManager.findFragmentById(R.id.navHostImagesFragment) as NavHostFragment?
        supportFragmentManager.beginTransaction().setPrimaryNavigationFragment(navHost).commit()

        val navController = findNavController(R.id.navHostImagesFragment)
        val navGraph = navController.navInflater.inflate(R.navigation.images_navigation)
        navController.setGraph(navGraph, bundle)
    }
}