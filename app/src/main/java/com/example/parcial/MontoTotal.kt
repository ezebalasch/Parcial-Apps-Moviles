package com.example.parcial

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.parcial.ui.theme.ParcialTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MontoTotal(navController: NavController, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("DolarApp", color = Color.White)

                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Icono",
                            modifier = Modifier.size(100.dp),
                            tint = Color.Yellow
                        )
                    }

                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceTint
                )
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            val texto = remember { mutableStateOf("") }
            Spacer(modifier = Modifier.height(64.dp))

            Text(
                text = "Su saldo es ",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "$2500 USD",
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(64.dp))
            Text("Monto a retirar")
            TextField(
                value = texto.value,
                onValueChange = {
                    texto.value = it
                }
            )
            Spacer(modifier = Modifier.height(64.dp))

            val saldoDisponible = 2500

            Button(onClick = {
                val monto = texto.value.toIntOrNull()

                if (monto != null && monto in 1..saldoDisponible) {
                    navController.navigate("comprobante/$monto")
                } else {
                    // Acá podrías mostrar un mensaje de error si querés
                    println("Monto inválido o mayor al saldo")
                }
            }) {
                Text("Retirar")
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun MontoTotalPreview() {
    ParcialTheme {
        MontoTotal(
            rememberNavController()
        )
    }
}