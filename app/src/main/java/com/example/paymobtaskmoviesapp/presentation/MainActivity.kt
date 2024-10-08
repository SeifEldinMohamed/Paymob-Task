package com.example.paymobtaskmoviesapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.paymobtaskmoviesapp.presentation.navigation.AppNavHost
import com.example.paymobtaskmoviesapp.presentation.theme.PaymobTaskMoviesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaymobTaskMoviesAppTheme {
                AppNavHost()
            }
        }
    }
}