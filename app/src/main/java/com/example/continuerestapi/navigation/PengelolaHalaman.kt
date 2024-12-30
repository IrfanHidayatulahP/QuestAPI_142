package com.example.continuerestapi.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.continuerestapi.ui.view.DestinasiEntry
import com.example.continuerestapi.ui.view.DestinasiHome
import com.example.continuerestapi.ui.view.DetailScreen
import com.example.continuerestapi.ui.view.EntryMhsScreen
import com.example.continuerestapi.ui.view.HomeScreen
import com.example.continuerestapi.ui.view.UpdateScreen

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier,
    ) {
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                onDetailClick = { nim ->
                    navController.navigate("detail/$nim")
                }
            )
        }

        composable(DestinasiEntry.route) {
            EntryMhsScreen(navigateBack = {
                navController.navigate(DestinasiHome.route) {
                    popUpTo(DestinasiHome.route) {
                        inclusive = true
                    }
                }
            })
        }

        // Halaman Detail Mahasiswa
        composable("detail/{nim}") { backStackEntry ->
            val nim = backStackEntry.arguments?.getString("nim") ?: ""
            DetailScreen(
                nim = nim,
                onNavigateBack = { navController.popBackStack() },
                onEditClick = { navController.navigate("update/$nim") }
            )
        }

        // Halaman Update Mahasiswa
        composable("update/{nim}") { backStackEntry ->
            val nim = backStackEntry.arguments?.getString("nim") ?: ""
            UpdateScreen(
                nim = nim,
                onNavigateBack = { navController.popBackStack() },
                onNavigateToHome = {
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }
    }
}