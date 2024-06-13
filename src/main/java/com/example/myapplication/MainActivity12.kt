package com.example.myapplication

import android.content.Intent
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

var themeNum : Int by mutableIntStateOf(1)
var clearData : Boolean by mutableStateOf(false)

var themeChange : Boolean by mutableStateOf(false)


class MainActivity12 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            if(clearData)
            {
                val sharedPreferences = applicationContext.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.clear()
                editor.apply()
                clearData = false
            }

            if(themeChange)
            {
                val sharedPreferencesTwo = applicationContext.getSharedPreferences("myPrefsTwo", Context.MODE_PRIVATE)
                val editorTwo = sharedPreferencesTwo.edit()
                editorTwo.putInt("myIntegerKey", themeNum)
                editorTwo.apply()
                themeOnChange = sharedPreferencesTwo.getInt("myIntegerKey", 0)
                themeChange = false
            }


            val gradientColorlist = listOf(
                orange_1,
                orange_2,
                orange_3,
                orange_4,
                orange_5,
                orange_6,
                orange_7,
                orange_8,
                orange_9
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = gradientBrush(
                            isVerticalGradient = true,
                            colors = if(themeOnChange == 1) gradientColorlist else gradientColorDark
                        )
                    ),
                contentAlignment = Alignment.Center
                
            )
            {
                Setting()
                Toggle()
                ClearHistory()
                Button(
                    onClick = {
                        Intent(applicationContext, MainActivity::class.java).also {
                            startActivity(it)
                            finish()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),
                    modifier = Modifier.offset((-170).dp, (-340).dp)
                )
                {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow icon",
                        modifier = Modifier.size(48.dp),
                        tint = if(themeOnChange ==1)darkBlue else Color.White
                    )

                }

            }

        }
    }
}

@Composable
fun Setting() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .drawBehind {
                val xo = Path().apply {
                    moveTo(200f, 300f)
                    lineTo(900f, 300f)
                    lineTo(900f, 500f)
                    lineTo(200f, 500f)
                    moveTo(150f, 400f)
                    val re = Rect(120f, 370f, 180f, 430f)
                    arcTo(
                        rect = re,
                        startAngleDegrees = 120f,
                        sweepAngleDegrees = 120f,
                        forceMoveTo = false
                    )
                    moveTo(135f, 374f)
                    lineTo(200f, 300f)
                    lineTo(200f, 500f)
                    lineTo(135f, 425.9f)


                    moveTo(950f, 400f)
                    val ree = Rect(920f, 370f, 980f, 430f)
                    arcTo(
                        rect = ree,
                        startAngleDegrees = 60f,
                        sweepAngleDegrees = -120f,
                        forceMoveTo = false
                    )
                    moveTo(965f, 374f)
                    lineTo(900f, 300f)
                    lineTo(900f, 500f)
                    lineTo(965f, 425.9f)

                }

                val kun = Path().apply {
                    moveTo(200f, 310f)
                    lineTo(900f, 310f)
                    lineTo(900f, 490f)
                    lineTo(200f, 490f)
                    moveTo(150f, 400f)
                    val re = Rect(130f, 380f, 170f, 420f)
                    arcTo(
                        rect = re,
                        startAngleDegrees = 120f,
                        sweepAngleDegrees = 120f,
                        forceMoveTo = false
                    )
                    moveTo(140f, 382.6f)
                    lineTo(200f, 310f)
                    lineTo(200f, 490f)
                    lineTo(135f, 417.3f)
                    moveTo(950f, 400f)
                    val ree = Rect(930f, 380f, 970f, 420f)
                    arcTo(
                        rect = ree,
                        startAngleDegrees = 60f,
                        sweepAngleDegrees = -120f,
                        forceMoveTo = false
                    )
                    moveTo(960f, 382.6f)
                    lineTo(900f, 310f)
                    lineTo(900f, 490f)
                    lineTo(960f, 417.3f)
                }

                drawPath(
                    path = xo,
                    color = lightBrownTwo
                )
                drawPath(
                    path = kun,
                    color = lightBrown
                )
            }
    )
    {
        Text(
            text = "GAME SETTING",
            modifier = Modifier.offset(110.dp, 125.dp),
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun Toggle()
{

    Box(
        modifier = Modifier
            .offset(x = 0.dp, y = -100.dp)
            .background(color = darkBlue,shape = RoundedCornerShape(30.dp))
            .size(width = 350.dp, height = 100.dp)
    )

     Box(
         modifier = Modifier
             .offset(x = 100.dp, y = -100.dp)
             .background(color = lightenedBlue, shape = RoundedCornerShape(30.dp))
             .size(width = 110.dp, height = 60.dp)
     )
     {

     }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .drawBehind{
                val hu = Path().apply {
                    moveTo(870f,835f)
                   val rev = Rect(820f,785f,920f,885f)
                    arcTo(
                        rect = rev,
                        startAngleDegrees = 0f,
                        sweepAngleDegrees = 359.999f,
                        forceMoveTo = false
                    )

                }

                val huy= Path().apply {
                    moveTo(770f,835f)
                    val revy = Rect(720f,785f,820f,885f)
                    arcTo(
                        rect = revy,
                        startAngleDegrees = 0f,
                        sweepAngleDegrees = 359.999f,
                        forceMoveTo = false
                    )

                }

                if (themeOnChange == 1)
                {
                    drawPath(
                        path = huy,
                        color = Color.Gray

                    )
                }

                else
                {
                    drawPath(
                        path = hu,
                        color = Color.White

                    )
                }



            }


    ){
        Text(text="THEME" ,color = Color.White,fontWeight = FontWeight.Bold,fontSize = 40.sp,modifier = Modifier.offset(60.dp,275.dp))
        Text(text="DARK" ,color = Color.Transparent,fontWeight = FontWeight.Bold,fontSize = 25.sp,modifier = Modifier.offset(270.dp,285.dp).clickable {
            if(themeOnChange == 1)
            {
                themeNum = 0
                themeChange = true
            }
            else{
                themeNum = 1
                themeChange = true
            }



        })
    }

}
@Composable
fun ClearHistory()
{
    Box(
        modifier = Modifier
            .offset(x = 0.dp, y = 20.dp)
            .background(color = darkBlue,shape = RoundedCornerShape(30.dp))
            .size(width = 350.dp, height = 100.dp)
    )
    {
        Text(text="CLEAR HISTORY" ,color = Color.White,fontWeight = FontWeight.Bold,fontSize = 40.sp,modifier = Modifier.offset(25.dp,20.dp).clickable{
            clearData = true
        })
    }

}
