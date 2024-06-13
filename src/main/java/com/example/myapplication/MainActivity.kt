package com.example.myapplication

import android.content.Intent
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.ui.platform.LocalContext
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.net.Uri
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.Font
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


val transparent_black = Color.Black.copy(alpha = 0.6f)
val orange_1 = Color(0xFFFFBE71)
val orange_2 = Color(0xFFFFB670)
val orange_3 = Color(0xFFFFAD70)
val orange_4 = Color(0xFFFFA170)
val orange_5 = Color(0xFFFF956F)
val orange_6 = Color(0xFFFF926F)
val orange_7 = Color(0xFFFF786E)
val orange_8 = Color(0xFFFF6B6D)
val orange_9 = Color(0xFFFF646D)
val darkPurple = Color(0xFF3B4276)
val lightBlue = Color(0xFF2FB6F0)
val lightRed = Color(0xFFFF5F57)
val brown = Color(0xFF66382F)
var lightBrown = Color(0xFFFFD4B7)
var lightBrownTwo = Color(0xFFD4BAB6)
val darkBlue = Color(0xFF3D4175)
val yellow = Color(0xFFFFA756)
val lightGreen = Color(0xFF41DC8E)
val lightenedColor = lightGreen.copy(alpha = 0.5f)
val lightenedBrown = brown.copy(alpha = 0.5f)
val lightenedBlue = Color.Black.copy(alpha = 0.5f)
val cellColor = Color(0xFFF5E5CE)
val lightBlack = Color(0xFF2F323B)

val dark_1 = Color(0xFF373E43)
val dark_2 = Color(0xFF3A454C)
val dark_3 = Color(0xFF425460)
val dark_4 = Color(0xFF486071)
val dark_5 = Color(0xFF4E6C82)
val dark_6 = Color(0xFF547A95)
val dark_7 = Color(0xFF5883A1)
val dark_8 = Color(0xFF5D8CAD)
val dark_9 = Color(0xFF6399BF)

val gradientColorDark = listOf(
    dark_1,
    dark_2,
    dark_3,
    dark_4,
    dark_5,
    dark_6,
    dark_7,
    dark_8,
    dark_9
)

var themeOnChange : Int by mutableIntStateOf(1)

val robu = FontFamily(
    Font(R.font.robu, FontWeight.Black)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val context = LocalContext.current

            val sharedPreferences: SharedPreferences = applicationContext.getSharedPreferences("myPrefsTwo", Context.MODE_PRIVATE)

            themeOnChange = sharedPreferences.getInt("myIntegerKey", 0)

            val music: MediaPlayer = MediaPlayer.create(this, R.raw.game)

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

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = gradientBrush(
                            isVerticalGradient = true,
                            colors = if(themeOnChange == 1) gradientColorlist else gradientColorDark
                        )
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "COLOR",fontFamily = robu, color = Color.Black, fontSize = 135.sp,fontWeight = FontWeight.Bold,modifier = Modifier.offset(x = 0.dp, y = 10.dp))
                Text(text = "CONQUEST", fontFamily = robu,color = transparent_black,fontWeight =FontWeight.Bold,fontSize = 83.sp,modifier = Modifier.offset(x = 0.dp, y = 0.dp))

                PlayerIcon()


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.offset(x = 0.dp, y = 0.dp)
                )
                {
                    Button(onClick={
                        Intent(applicationContext,MainActivity10::class.java).also{
                            startActivity(it)
                        }

                    },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = lightBlue),
                        modifier = Modifier
                            .width(90.dp)
                            .height(65.dp)
                            .graphicsLayer {
                                shadowElevation = 10.dp.toPx()
                                shape = RoundedCornerShape(40.dp)
                                clip = true
                            },
                        shape = RoundedCornerShape(30.dp),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
                    )
                    {
                        Icon(imageVector = Icons.Default.Star , contentDescription = "Star icon",modifier = Modifier.size(48.dp),tint = darkBlue)
                    }

                    Spacer(modifier = Modifier.width(20.dp))

                    Button(onClick={
                        music.start()

                        playerNameOne = ""
                        playerNameTwo = ""
                        playerNameThree = ""
                        globalMode = ""
                        globalSubMode = ""
                        globalPlayer = ""
                        enablerTwo = false
                        globalRow = ""
                        globalColumn = ""
                        globalPlayerOne = 0
                        globalPlayerTwo = 0
                        globalPlayerThree = 0
                        globalPlayerFour = 0
                        globalCounter = 105F

                        Intent(applicationContext,MainActivity2::class.java).also{
                            startActivity(it)
                        }

                    },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = lightBlue),
                        modifier = Modifier
                            .width(90.dp)
                            .height(65.dp)
                            .graphicsLayer {
                                shadowElevation = 10.dp.toPx()
                                shape = RoundedCornerShape(60.dp)
                                clip = true
                            },
                        shape = RoundedCornerShape(30.dp),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
                    )
                    {
                        Icon(imageVector = Icons.Default.PlayArrow , contentDescription = "Arrow icon",modifier = Modifier.size(100.dp),tint = darkBlue)
                    }

                    Spacer(modifier = Modifier.width(20.dp))


                    Button(onClick={
                        Intent(applicationContext,MainActivity12::class.java).also{
                            startActivity(it)
                        }

                    },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = lightBlue),
                        modifier = Modifier
                            .width(90.dp)
                            .height(65.dp)
                            .graphicsLayer {
                                shadowElevation = 10.dp.toPx()
                                shape = RoundedCornerShape(40.dp)
                                clip = true
                            },
                        shape = RoundedCornerShape(30.dp),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
                    )
                    {
                        Icon(imageVector = Icons.Default.Settings , contentDescription = "Setting icon",modifier = Modifier.size(48.dp),tint = darkBlue)
                    }

                }

                Spacer(modifier = Modifier.height(30.dp))

                Button(onClick={
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/shorts/msKtWcRy2So"))
                    context.startActivity(intent)
                },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = darkPurple),
                    modifier = Modifier.height(50.dp)
                        .width(60.dp)
                        .graphicsLayer {
                            shadowElevation = 30.dp.toPx()
                            shape = RoundedCornerShape(10.dp)
                            clip = true
                        }
                )
                {
                    Text(text = "?", color = Color.White, fontSize = 25.sp,fontFamily = robu,fontWeight = FontWeight.ExtraBold)
                }

            }


        }
    }



    @Composable
    fun PlayerIcon()
    {
        Box(
            modifier = Modifier
                .width(500.dp)
                .height(250.dp)
                .drawBehind {
                    val pathOne = Path().apply {
                        moveTo(300f, 200f)
                        val picOne = Rect(200f, 100f, 400f, 300f)
                        arcTo(
                            rect = picOne,
                            startAngleDegrees = 0f,
                            sweepAngleDegrees = -359f,
                            forceMoveTo = true
                        )
                        moveTo(300f,400f)
                        val picTwo = Rect(150f,250f,450f,600f)
                        arcTo(
                            rect = picTwo,
                            startAngleDegrees = 0f,
                            sweepAngleDegrees = -180f,
                            forceMoveTo = true
                        )
                        moveTo(175f,425f)
                        val picThree = Rect(150f,400f,200f,450f)
                        arcTo(
                            rect = picThree,
                            startAngleDegrees = 0f,
                            sweepAngleDegrees = 180f,
                            forceMoveTo = false
                        )
                        moveTo(425f,425f)
                        val picFour = Rect(400f,400f,450f,450f)
                        arcTo(
                            rect = picFour,
                            startAngleDegrees = 0f,
                            sweepAngleDegrees = 180f,
                            forceMoveTo = false
                        )

                        close()

                    }
                    val pathTwo = Path().apply{
                        moveTo(300f,400f)

                        val picFive = Rect(100f,300f,500f,500f)
                        arcTo(
                            rect = picFive,
                            startAngleDegrees = -120f,
                            sweepAngleDegrees = -300f,
                            forceMoveTo = false
                        )

                        moveTo(800f,400f)

                        val picSix = Rect(600f,300f,1000f,500f)
                        arcTo(
                            rect = picSix,
                            startAngleDegrees = -120f,
                            sweepAngleDegrees = -300f,
                            forceMoveTo = false
                        )


                    }

                    val pathThree = Path().apply{

                        moveTo(800f, 200f)

                        val picSeven = Rect(700f, 100f, 900f, 300f)
                        arcTo(
                            rect = picSeven,
                            startAngleDegrees = 0f,
                            sweepAngleDegrees = -359f,
                            forceMoveTo = true
                        )

                        moveTo(800f,400f)

                        val picEight = Rect(650f,250f,950f,600f)
                        arcTo(
                            rect = picEight,
                            startAngleDegrees = 0f,
                            sweepAngleDegrees = -180f,
                            forceMoveTo = true
                        )

                        moveTo(675f,425f)

                        val picNine = Rect(650f,400f,700f,450f)

                        arcTo(
                            rect = picNine,
                            startAngleDegrees = 0f,
                            sweepAngleDegrees = 180f,
                            forceMoveTo = false
                        )

                        moveTo(925f,425f)

                        val picTen = Rect(900f,400f,950f,450f)
                        arcTo(
                            rect = picTen,
                            startAngleDegrees = 0f,
                            sweepAngleDegrees = 180f,
                            forceMoveTo = false
                        )



                    }


                    drawPath(
                        path = pathTwo,
                        color = brown
                    )
                    drawPath(
                        path = pathOne,
                        color = lightBlue
                    )
                    drawPath(
                        path = pathThree,
                        color = lightRed
                    )
                }
        )
    }


}

@Composable
fun gradientBrush(
    isVerticalGradient: Boolean,
    colors:List<Color>
): Brush {
    val endOffset = if(isVerticalGradient){
        Offset(0f, Float.POSITIVE_INFINITY)
    }
    else{
        Offset(Float.POSITIVE_INFINITY,0f)
    }

    return Brush.linearGradient(
        colors = colors,
        start = Offset.Zero,
        end = endOffset
    )

}