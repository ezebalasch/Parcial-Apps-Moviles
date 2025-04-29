package com.example.parcial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.parcial.ui.theme.ParcialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController();
            ParcialTheme {
                NavHost(
                    navController = navController,
                    startDestination = "monto"){

                    composable("monto"){ MontoTotal(navController) }
                    composable(
                        "comprobante/{monto}",
                        arguments = listOf(
                            navArgument("monto") { type = NavType.IntType }
                        )
                    ) { backStackEntry ->
                        val monto = backStackEntry.arguments?.getInt("monto") ?: 0
                        Comprobante(navController, monto)
                    }
                }

            }
        }
    }
}

