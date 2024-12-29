package com.example.continuerestapi

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.continuerestapi.navigation.PengelolaHalaman
import com.example.continuerestapi.ui.theme.ContinueRestAPITheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContinueRestAPITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    PengelolaHalaman()
                }
            }
        }
    }
}