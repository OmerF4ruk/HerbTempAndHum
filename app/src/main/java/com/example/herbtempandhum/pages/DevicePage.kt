package com.example.herbtempandhum

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.herbtempandhum.data.Data
import com.example.herbtempandhum.viewmodel.DataViewModel
import com.example.herbtempandhum.viewmodel.DataViewModelFactory
import java.util.Objects.toString


@RequiresApi(Build.VERSION_CODES.O)
@Composable

fun DevicePage(
    id: String?,
    viewModel: DataViewModel = viewModel(factory = DataViewModelFactory(id.toString()))
) {
    println("deneme")
    Column() {

    }
    val state by viewModel.state.collectAsState()

    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        LazyColumn(
            Modifier
                .padding(10.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            println(state.datas.isEmpty())
            println(state.datas)

            if (!state.datas.isEmpty()) {
                items(state.datas) { data ->
                    dataBox(data = data)
                }
            } else {
                println("veri yok")
            }
        }


    }


}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showBackground = true)
fun DevicePagePreview() {
    DevicePage(id = "1")
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun dataBox(data: Data) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(vertical = 0.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(color = Color(0xFF00668A)),


        ) {
        Column(
            modifier = Modifier
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.SpaceEvenly,


                ) {
                dataElememnt(name = "Hava Sıcaklığı", value = data.air_temperature.take(5))
                dataElememnt(name = "Hava Nemi", value = data.air_humidity)
                dataElememnt(name = "Toprak Sıcaklığı", value = data.soil_temperature.take(5))
                dataElememnt(name = "Toprak Nemi", value = data.soil_humidity)

            }
            Divider(Modifier.padding(5.dp),thickness = 2.dp)
            Column(
                Modifier.padding(vertical = 0.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Son İletişim Zamanı", color = Color.White, fontSize = 12.sp)
                Text(
                    text = toString(timeFormatter(data.date, data.time)),
                    color = Color.White, fontSize = 12.sp
                )
            }


        }
    }


    /*Text(text = data.air_humidity)
    Text(text = data.air_temperature)
    Text(text = data.soil_humidity)
    Text(text = data.soil_temperature)
    Text(text = toString(data.date))
    Text(text = toString(data.time))*/


}

fun timeFormatter(date: String, time: String): String {

    val dates = date.split("T")
    val times = time.split(".")
    val datetime: String = dates[0] + " " + times[0]
    return datetime;
}

@Composable
fun dataElememnt(name: String, value: String) {

    Box(
        modifier = Modifier
            .size(75.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(Color.Gray)
            .padding(horizontal = 1.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = name, color = Color.White, fontSize = 15.sp)
            Divider()
            Text(text = value, color = Color.White, fontSize = 20.sp)
        }


    }
}

