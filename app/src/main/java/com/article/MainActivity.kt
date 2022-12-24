package com.article

import android.content.Context
import android.graphics.Rect
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.article.core.common.AppCheckConnection
import com.article.databinding.ActivityMainBinding

import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var currentTime = 0L
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        changeLocale()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onStart() {
        super.onStart()
        navController = findNavController(R.id.fragmentContainerView)
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.label == "HomeFragment"
                || destination.label == "SearchFragment"
                || destination.label == "ProfileFragment"
            ) {
                binding.bottomNavigation.visibility = View.VISIBLE
                binding.root.setBackgroundColor(
                    resources.getColor(
                        R.color.background,
                        resources.newTheme()
                    )
                )
            } else {
                binding.bottomNavigation.visibility = View.GONE
            }
        }

        AppCheckConnection(application).observe(this) { isOnline ->
            if (!isOnline) {
                Toast
                    .makeText(
                        this,
                        getString(R.string.internet_not_connected),
                        Toast.LENGTH_SHORT
                    ).show()
            }
        }
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v: View? = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val destinationLabel = navController.currentDestination?.label
                    if (destinationLabel != "com.article.feature.authentication.ui.view.SignUpFragment" && destinationLabel != "com.article.feature.authentication.ui.view.SignInFragment") {
                        val imm: InputMethodManager =
                            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
                    }
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    @Suppress("DEPRECATION")
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val destination = navController.currentDestination?.label
        if (destination == "SearchFragment" || destination == "ProfileFragment"
        ) {
            navController.navigate(R.id.homeFragment)
        } else if (destination == "HomeFragment") {
            if (currentTime + 1000 > System.currentTimeMillis()) {
                finishAffinity()
            } else {
                Toast.makeText(this, resources.getText(R.string.back_again), Toast.LENGTH_SHORT)
                    .show()
                currentTime = System.currentTimeMillis()
            }
        } else {
            super.onBackPressed()
        }
    }

    @Suppress("DEPRECATION")
    private fun changeLocale() {
        val config = resources.configuration
        val locale = Locale("fa")
        Locale.setDefault(locale)
        config.setLocale(locale)
        createConfigurationContext(config)
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}