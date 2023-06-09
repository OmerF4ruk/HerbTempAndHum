package com.example.herbtempandhum

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.herbtempandhum.data.Device
import com.example.herbtempandhum.viewmodel.DeviceListViewModel
import com.example.herbtempandhum.viewmodel.DeviceListViewModelFactory

@Composable

fun DeviceListPage(
    user_id: String?,
    navController: NavController,
    viewModel: DeviceListViewModel = viewModel(factory = DeviceListViewModelFactory(user_id))
) {


    Column(
        Modifier
            .padding(15.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Hoş Geldiniz!", fontSize = 25.sp)
        }

        val state by viewModel.state.collectAsState()
        println(state.devices)



        Button(
            onClick = { navController.navigate(route = Screen.AddDevicePage.route+"/${1}")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 100.dp)
                .clip(RoundedCornerShape(100))
        ) {
            Text(text = "Cihaz Ekle", textAlign = TextAlign.Center)

        }

        LazyColumn(
            Modifier
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(state.devices) { device ->
                DeviceBox(device, navController)
            }
        }


        }




}

@Composable
private fun DeviceBox(
    device: Device,
    navController: NavController,
) {
    Box(
        modifier = Modifier
            .clickable(onClick = { navController.navigate(route = Screen.DevicePage.route + "/${device.id}") })
            .size(450.dp, 130.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF7D5260)),

        ) {
        Column(
            Modifier
                .padding(0.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(
                    space = 10.dp, alignment = Alignment.CenterHorizontally
                ),


                ) {
                Text(text = "Bitki İsmi:", fontSize = 30.sp, color = Color.White)

                Text(text = device.name, fontSize = 30.sp, color = Color.White)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(
                    space = 10.dp,
                ),
            ) {
                Text(text = "Cihaz İD:", fontSize = 20.sp, color = Color.White)

                Text(text = device.id, fontSize = 20.sp, color = Color.White)
            }

        }
    }
}

