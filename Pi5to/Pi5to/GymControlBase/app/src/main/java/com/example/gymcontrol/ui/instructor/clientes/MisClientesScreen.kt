package com.example.gymcontrol.ui.instructor.clientes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymcontrol.GymApp

// Colores del diseño de AXOLOTL
private val Negro = Color(0xFF000000)
private val GrisOscuro = Color(0xFF202121)
private val GrisBorde = Color(0xFF494A4A)
private val Morado = Color(0xFFA655E5)
private val MoradoOscuro = Color(0xFF381F52)
private val Dorado = Color(0xFFA68A00)
private val Blanco = Color(0xFFFFFFFF)
private val GrisTexto = Color(0xFFBDBDBD)

@Composable
fun MisClientesScreen() {

    var selectedTab by remember { mutableStateOf(0) }

    val clients = GymApp.repository.instructorClients().filter {
        it.active
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Negro)
    ) {

        // Encabezado
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 14.dp)
        ) {

            Text(
                text = "AXOLOTL",
                color = Dorado,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(8.dp))

            HorizontalDivider(
                color = Dorado,
                thickness = 1.dp
            )
        }

        // Contenido
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 20.dp)
        ) {

            Text(
                text = "Usuarios asignados",
                color = Blanco,
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    top = 8.dp,
                    bottom = 14.dp
                )
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(clients) { client ->

                    ClientCard(
                        name = client.clientName,
                        days = client.days,
                        goal = client.goal,
                        onEdit = {
                            // Después agregaremos aquí la ventana de Editar
                        }
                    )
                }
            }
        }

        // Barra inferior
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(Negro)
                .border(
                    width = 1.dp,
                    color = GrisBorde
                ),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Mis clientes
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = "♧",
                    color = if (selectedTab == 0) Morado else GrisTexto,
                    fontSize = 27.sp
                )

                Text(
                    text = "Mis clientes",
                    color = if (selectedTab == 0) Morado else GrisTexto,
                    fontSize = 14.sp
                )
            }

            // Perfil
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {

                Text(
                    text = "●",
                    color = if (selectedTab == 1) Morado else GrisTexto,
                    fontSize = 24.sp
                )

                Text(
                    text = "Perfil",
                    color = if (selectedTab == 1) Morado else GrisTexto,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
private fun ClientCard(
    name: String,
    days: String,
    goal: String,
    onEdit: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = GrisOscuro,
                shape = RoundedCornerShape(14.dp)
            )
            .border(
                width = 2.dp,
                color = GrisBorde,
                shape = RoundedCornerShape(14.dp)
            )
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // Avatar
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(
                    color = Color.Black,
                    shape = CircleShape
                )
                .border(
                    width = 1.dp,
                    color = GrisBorde,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "●",
                color = Color.DarkGray,
                fontSize = 25.sp
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        // Información
        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = name,
                color = Blanco,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = days,
                color = GrisTexto,
                fontSize = 11.sp
            )

            Text(
                text = "Objetivo: $goal",
                color = GrisTexto,
                fontSize = 11.sp
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Botón editar
        Button(
            onClick = onEdit,
            colors = ButtonDefaults.buttonColors(
                containerColor = Morado
            ),
            shape = RoundedCornerShape(6.dp),
            contentPadding = PaddingValues(
                horizontal = 17.dp,
                vertical = 7.dp
            )
        ) {

            Text(
                text = "Editar",
                color = Color.Black,
                fontSize = 12.sp
            )
        }
    }
}