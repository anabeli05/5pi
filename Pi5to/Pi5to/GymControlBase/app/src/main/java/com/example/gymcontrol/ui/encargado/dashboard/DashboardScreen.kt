package com.example.gymcontrol.ui.encargado.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gymcontrol.GymApp
import com.example.gymcontrol.data.model.Status
import com.example.gymcontrol.data.model.UserRole
import com.example.gymcontrol.ui.components.*

@Composable
fun DashboardScreen() {
    val users = GymApp.repository.users()
    val active = users.count { it.role == UserRole.CLIENTE && it.status == Status.ACTIVO }
    val inactive = users.count { it.role == UserRole.CLIENTE && it.status == Status.INACTIVO }

    ScreenContainer("Dashboard") {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            StatCard("Clientes activos", "$active", Modifier.weight(1f))
            StatCard("Inactivos", "$inactive", Modifier.weight(1f))
        }
        StatCard("Ingresos del mes", "$58,400", Modifier.fillMaxWidth())

        OccupancyChart(GymApp.repository.occupancy())

        SectionTitle("Activos / inactivos por mes")
        Card(Modifier.fillMaxWidth()) {
            Text("Espacio para gráfica de 12 meses", Modifier.padding(24.dp))
        }

        SectionTitle("Entradas por día de la semana")
        Card(Modifier.fillMaxWidth()) {
            Text("Lun 120 · Mar 135 · Mié 141 · Jue 128 · Vie 160 · Sáb 98 · Dom 45", Modifier.padding(24.dp))
        }
    }
}
