package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.myapplication.ui.theme.MyApplicationTheme

var name : String = ""
var darkBluee = Color(0xFF3D4175)



fun extractor(namePlayer:String)
{
    name = namePlayer
}

class MainActivity11 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

        }
    }
}


@Composable
fun Display(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
                .background(color = darkBluee,shape = RoundedCornerShape(50.dp)),
            shape = RoundedCornerShape(50.dp),
            color = darkBluee // Set the background color here
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier
                    .size(width = 280.dp, height = 60.dp) // Set width and height here
                    .background(Color.White, shape = RoundedCornerShape(30.dp)),
                    contentAlignment = Alignment.Center)
                {
                    Text(text="$name",fontSize = 30.sp,fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(30.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .width(85.dp)
                            .background(color = lightBlue)
                            .height(4.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.first),
                        contentDescription = "Image",
                        modifier = Modifier.size(80.dp)
                    )
                    Box(
                        modifier = Modifier
                            .width(85.dp)
                            .background(color = lightBlue)
                            .height(4.dp)
                    )
                }
                Text(text = "WINS!", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = {
                        // Replace with your actual function
                         com.example.myapplication.processor()
                    },
                    modifier = Modifier
                        .width(270.dp)
                        .height(55.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = lightBlue)
                ) {
                    Text(text = "Play Again", fontSize = 26.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(18.dp))
                Button(
                    onClick = {
                        // Replace with your actual function
                         com.example.myapplication.returnHome()
                    },
                    modifier = Modifier
                        .width(270.dp)
                        .height(55.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = lightRed)
                ) {
                    Text(text = "Home", fontSize = 26.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}


@Composable
fun GamePause(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(color = darkBluee,shape = RoundedCornerShape(50.dp)),
            shape = RoundedCornerShape(30.dp),
            color = darkBluee // Set the background color here
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .size(width = 230.dp, height = 60.dp)
                        .background(Color.White, shape = RoundedCornerShape(30.dp)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "Game Pause",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(color = lightBlue, shape = RoundedCornerShape(size = 30.dp))
                            .clickable {
                                // Replace with your actual function
                                 com.example.myapplication.returnHome()
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Home, contentDescription = "Home Page", modifier = Modifier.size(45.dp), tint = Color.White)
                    }
                    Spacer(modifier = Modifier.width(40.dp))
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(color = lightRed, shape = RoundedCornerShape(size = 30.dp))
                            .clickable {
                                // Replace with your actual function
                                 resume = true
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.PlayArrow, contentDescription = "Resume", modifier = Modifier.size(45.dp), tint = Color.White)
                    }
                    Spacer(modifier = Modifier.width(40.dp))
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(color = lightBlue, shape = RoundedCornerShape(size = 30.dp))
                            .clickable {
                                // Replace with your actual function
                                 com.example.myapplication.processor()
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Refresh, contentDescription = "Restart", modifier = Modifier.size(45.dp), tint = Color.White)
                    }
                }
            }
        }
    }
}


