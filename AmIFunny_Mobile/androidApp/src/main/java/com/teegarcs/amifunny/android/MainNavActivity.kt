package com.teegarcs.amifunny.android

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainNavActivity : AppCompatActivity() {

    private val navHostFragment: NavHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_nav)
        findViewById<BottomNavigationView>(R.id.bottomNavigation).setupWithNavController(
            navHostFragment.navController
        )
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navHostFragment.navController.handleDeepLink(intent)
    }
}

fun Fragment.getMainNavController(): NavController? {
    return activity?.findNavController(R.id.nav_host_fragment)
}
