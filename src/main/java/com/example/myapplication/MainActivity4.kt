package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


var light  : Boolean by mutableStateOf(false)
var playerNameThree : String by mutableStateOf("")
var playerNameFour : String by mutableStateOf("")


class MainActivity4 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            light = if (globalPlayer == "3") {
                true
            } else {
                false
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

                PlayerIcons()
                PlayerInfoIcon()
                InformationBar()

                if(playerNameThree != "" )
                {
                    Button(
                        onClick = {
                            stateLoad = true
                            val intentThree = Intent(this@MainActivity4, MainActivity5::class.java)
                            startActivity(intentThree)
                            finish()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        modifier = Modifier.offset(170.dp, (-340).dp)
                    )
                    {
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Arrow icon",
                            modifier = Modifier.size(48.dp),
                            tint = if(themeOnChange ==1)darkBlue else Color.White
                        )


                    }
                }

                Button(
                    onClick = {
                        Intent(applicationContext, MainActivity3::class.java).also {
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
    fun PlayerInfoIcon()
    {
        Box(modifier= Modifier
            .fillMaxSize()
            .drawBehind {
//                THE DARK BLUE PART
                val wuy = Path().apply {
                    moveTo(100f, 700f)
                    lineTo(450f, 700f)
                    lineTo(450f, 1000f)
                    lineTo(100f, 1000f)

                    moveTo(100f, 750f)
                    val wer = Rect(50f, 700f, 150f, 800f)
                    arcTo(
                        rect = wer,
                        startAngleDegrees = -90f,
                        sweepAngleDegrees = -90f,
                        forceMoveTo = false
                    )
                    moveTo(450f, 750f)
                    val war = Rect(400f, 700f, 500f, 800f)
                    arcTo(
                        rect = war,
                        startAngleDegrees = -90f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false
                    )
                    moveTo(450f, 950f)
                    val wqr = Rect(400f, 900f, 500f, 1000f)
                    arcTo(
                        rect = wqr,
                        startAngleDegrees = 90f,
                        sweepAngleDegrees = -90f,
                        forceMoveTo = false
                    )
                    moveTo(100f, 950f)
                    val wtr = Rect(50f, 900f, 150f, 1000f)
                    arcTo(
                        rect = wtr,
                        startAngleDegrees = 90f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false
                    )
                    moveTo(50f, 750f)
                    lineTo(50f, 950f)
                    lineTo(100f, 950f)
                    lineTo(100f, 750f)
//the upper part
                    moveTo(280f, 700f)
                    val weru = Rect(180f, 600f, 380f, 800f)
                    arcTo(
                        rect = weru,
                        startAngleDegrees = 0f,
                        sweepAngleDegrees = -180f,
                        forceMoveTo = false
                    )

//
                    moveTo(500f, 950f)
                    lineTo(500f, 750f)
                    lineTo(450f, 750f)
                    lineTo(450f, 950f)

                    moveTo(100f, 1200f)
                    lineTo(450f, 1200f)
                    lineTo(450f, 1500f)
                    lineTo(100f, 1500f)

                    moveTo(630f, 1200f)
                    lineTo(980f, 1200f)
                    lineTo(980f, 1500f)
                    lineTo(630f, 1500f)
                    lineTo(630f, 1200f)
//
                    moveTo(100f, 1250f)
                    val ret = Rect(50f, 1200f, 150f, 1300f)
                    arcTo(
                        rect = ret,
                        startAngleDegrees = -90f,
                        sweepAngleDegrees = -90f,
                        forceMoveTo = false
                    )
                    moveTo(450f, 1250f)
                    val rete = Rect(400f, 1200f, 500f, 1300f)
                    arcTo(
                        rect = rete,
                        startAngleDegrees = -90f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false
                    )
                    moveTo(100f, 1450f)
                    val retu = Rect(50f, 1400f, 150f, 1500f)
                    arcTo(
                        rect = retu,
                        startAngleDegrees = 90f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false
                    )
                    moveTo(450f, 1450f)
                    val retee = Rect(400f, 1400f, 500f, 1500f)
                    arcTo(
                        rect = retee,
                        startAngleDegrees = 90f,
                        sweepAngleDegrees = -90f,
                        forceMoveTo = false
                    )
                    moveTo(50f, 1250f)
                    lineTo(50f, 1450f)
                    lineTo(100f, 1450f)
                    lineTo(100f, 1250f)

                    moveTo(450f, 1250f)
                    lineTo(450f, 1450f)
                    lineTo(500f, 1450f)
                    lineTo(500f, 1250f)

                    moveTo(630f, 1250f)
                    val balls = Rect(580f, 1200f, 680f, 1300f)
                    arcTo(
                        rect = balls,
                        startAngleDegrees = -90f,
                        sweepAngleDegrees = -90f,
                        forceMoveTo = false
                    )
                    moveTo(980f, 1250f)
                    val balle = Rect(930f, 1200f, 1030f, 1300f)
                    arcTo(
                        rect = balle,
                        startAngleDegrees = -90f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false
                    )
                    moveTo(630f, 1450f)
                    val ballu = Rect(580f, 1400f, 680f, 1500f)
                    arcTo(
                        rect = ballu,
                        startAngleDegrees = 90f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false
                    )
                    moveTo(980f, 1450f)
                    val ballq = Rect(930f, 1400f, 1030f, 1500f)
                    arcTo(
                        rect = ballq,
                        startAngleDegrees = 0f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false
                    )
                    moveTo(630f, 1450f)
                    lineTo(580f, 1450f)
                    lineTo(580f, 1250f)
                    lineTo(630f, 1250f)

                    moveTo(980f, 1450f)
                    lineTo(1030f, 1450f)
                    lineTo(1030f, 1250f)
                    lineTo(980f, 1250f)

                    moveTo(230f, 1200f)
                    lineTo(290f, 1120f)
                    lineTo(360f, 1120f)
                    lineTo(360f, 1200f)

                }

                val yoyo = Path().apply {

                    moveTo(120f, 870f)
                    lineTo(430f, 870f)
                    lineTo(430f, 980f)
                    lineTo(120f, 980f)

                    moveTo(120f, 925f)
                    val fuy = Rect(80f, 870f, 160f, 980f)

                    arcTo(
                        rect = fuy,
                        startAngleDegrees = -90f,
                        sweepAngleDegrees = -180f,
                        forceMoveTo = false
                    )
                    moveTo(430f, 940f)
                    val fuyu = Rect(390f, 870f, 470f, 980f)

                    arcTo(
                        rect = fuyu,
                        startAngleDegrees = -90f,
                        sweepAngleDegrees = 180f,
                        forceMoveTo = false
                    )

                }
                val yuyu = Path().apply {
                    moveTo(110f, 740f)
                    lineTo(220f, 740f)
                    lineTo(220f, 750f)
                    lineTo(110f, 750f)

                    moveTo(360f, 740f)
                    lineTo(440f, 740f)
                    lineTo(440f, 750f)
                    lineTo(360f, 750f)

                    moveTo(805f, 800f)
                    val sma = Rect(775f, 770f, 835f, 830f)
                    arcTo(
                        rect = sma,
                        startAngleDegrees = -0f,
                        sweepAngleDegrees = 359f,
                        forceMoveTo = false
                    )
                }

                val wuyu = Path().apply {
                    moveTo(630f, 700f)
                    lineTo(980f, 700f)
                    lineTo(980f, 1000f)
                    lineTo(630f, 1000f)

                    moveTo(630f, 750f)
                    val qww = Rect(580f, 700f, 680f, 800f)
                    arcTo(
                        rect = qww,
                        startAngleDegrees = -90f,
                        sweepAngleDegrees = -90f,
                        forceMoveTo = false
                    )
                    moveTo(630f, 950f)
                    val qee = Rect(580f, 900f, 680f, 1000f)
                    arcTo(
                        rect = qee,
                        startAngleDegrees = 90f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false
                    )
                    moveTo(980f, 950f)
                    val qeew = Rect(930f, 900f, 1030f, 1000f)
                    arcTo(
                        rect = qeew,
                        startAngleDegrees = 0f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false
                    )
                    moveTo(980f, 750f)
                    val qwwq = Rect(930f, 700f, 1030f, 800f)
                    arcTo(
                        rect = qwwq,
                        startAngleDegrees = 0f,
                        sweepAngleDegrees = -90f,
                        forceMoveTo = false
                    )
                    moveTo(1030f, 750f)
                    lineTo(1030f, 950f)
                    lineTo(980f, 950f)
                    lineTo(980f, 750f)

                    moveTo(580f, 750f)
                    lineTo(580f, 950f)
                    lineTo(630f, 950f)
                    lineTo(630f, 750f)

                }

                val than = Path().apply {
                    moveTo(110f, 1250f)
                    lineTo(190f, 1250f)
                    lineTo(190f, 1260f)
                    lineTo(110f, 1260f)

                    moveTo(360f, 1250f)
                    lineTo(440f, 1250f)
                    lineTo(440f, 1260f)
                    lineTo(360f, 1260f)

                    moveTo(120f, 1370f)
                    lineTo(430f, 1370f)
                    lineTo(430f, 1480f)
                    lineTo(120f, 1480f)

                    moveTo(120f, 1425f)
                    val recw = Rect(80f, 1370f, 160f, 1480f)
                    arcTo(
                        rect = recw,
                        startAngleDegrees = -90f,
                        sweepAngleDegrees = -180f,
                        forceMoveTo = false
                    )

                    moveTo(430f, 1425f)
                    val recwe = Rect(390f, 1370f, 470f, 1480f)

                    arcTo(
                        rect = recwe,
                        startAngleDegrees = -90f,
                        sweepAngleDegrees = 180f,
                        forceMoveTo = false
                    )

                    moveTo(805f, 1300f)
                    val vuc = Rect(775f, 1270f, 835f, 1330f)
                    arcTo(
                        rect = vuc,
                        startAngleDegrees = -0f,
                        sweepAngleDegrees = 359f,
                        forceMoveTo = false
                    )

                }

                val drewq = Path().apply {
                    moveTo(805f, 860f)
                    val smam = Rect(760f, 805f, 850f, 915f)
                    arcTo(
                        rect = smam,
                        startAngleDegrees = 0f,
                        sweepAngleDegrees = -180f,
                        forceMoveTo = false
                    )

                    moveTo(770f, 860f)
                    val guy = Rect(760f, 850f, 780f, 870f)
                    arcTo(
                        rect = guy,
                        startAngleDegrees = 0f,
                        sweepAngleDegrees = 180f,
                        forceMoveTo = false
                    )

                    moveTo(840f, 860f)
                    val gaya = Rect(830f, 850f, 850f, 870f)
                    arcTo(
                        rect = gaya,
                        startAngleDegrees = 0f,
                        sweepAngleDegrees = 180f,
                        forceMoveTo = false
                    )

                    moveTo(630f, 950f)
                    lineTo(670f, 950f)
                    lineTo(670f, 960f)
                    lineTo(630f, 960f)

                    moveTo(690f, 950f)
                    lineTo(730f, 950f)
                    lineTo(730f, 960f)
                    lineTo(690f, 960f)

                    moveTo(750f, 950f)
                    lineTo(790f, 950f)
                    lineTo(790f, 960f)
                    lineTo(750f, 960f)

                    moveTo(810f, 950f)
                    lineTo(850f, 950f)
                    lineTo(850f, 960f)
                    lineTo(810f, 960f)

                    moveTo(870f, 950f)
                    lineTo(910f, 950f)
                    lineTo(910f, 960f)
                    lineTo(870f, 960f)

                    moveTo(930f, 950f)
                    lineTo(970f, 950f)
                    lineTo(970f, 960f)
                    lineTo(930f, 960f)

                }

                val drewqu = Path().apply {
                    moveTo(805f, 1360f)
                    val smama = Rect(760f, 1305f, 850f, 1415f)
                    arcTo(
                        rect = smama,
                        startAngleDegrees = 0f,
                        sweepAngleDegrees = -180f,
                        forceMoveTo = false
                    )

                    moveTo(770f, 1360f)
                    val guyy = Rect(760f, 1350f, 780f, 1370f)
                    arcTo(
                        rect = guyy,
                        startAngleDegrees = 0f,
                        sweepAngleDegrees = 180f,
                        forceMoveTo = false
                    )

                    moveTo(840f, 1360f)
                    val gayay = Rect(830f, 1350f, 850f, 1370f)
                    arcTo(
                        rect = gayay,
                        startAngleDegrees = 0f,
                        sweepAngleDegrees = 180f,
                        forceMoveTo = false
                    )

                    moveTo(630f, 1450f)
                    lineTo(670f, 1450f)
                    lineTo(670f, 1460f)
                    lineTo(630f, 1460f)

                    moveTo(690f, 1450f)
                    lineTo(730f, 1450f)
                    lineTo(730f, 1460f)
                    lineTo(690f, 1460f)

                    moveTo(750f, 1450f)
                    lineTo(790f, 1450f)
                    lineTo(790f, 1460f)
                    lineTo(750f, 1460f)

                    moveTo(810f, 1450f)
                    lineTo(850f, 1450f)
                    lineTo(850f, 1460f)
                    lineTo(810f, 1460f)

                    moveTo(870f, 1450f)
                    lineTo(910f, 1450f)
                    lineTo(910f, 1460f)
                    lineTo(870f, 1460f)

                    moveTo(930f, 1450f)
                    lineTo(970f, 1450f)
                    lineTo(970f, 1460f)
                    lineTo(930f, 1460f)


                }

                drawPath(
                    path = wuy,
                    color = darkBlue
                )
                drawPath(
                    path = wuyu,
                    color = darkBlue
                )
                drawPath(
                    path = yuyu,
                    color = yellow
                )
                drawPath(
                    path = yoyo,
                    color = yellow
                )
                drawPath(
                    path = than,
                    color = if (light) lightenedColor else lightGreen
                )
                drawPath(
                    path = drewq,
                    color = yellow
                )
                drawPath(
                    path = drewqu,
                    color = if (light) lightenedColor else lightGreen
                )
            }
        )
        {
            Text(text = "PLAYER",color= Color.White,textDecoration = TextDecoration.Underline,fontWeight = FontWeight.Bold,modifier = Modifier.offset(45.dp,315.dp),fontSize = 30.sp)
            Text(text = "4" ,color = if(light) lightenedColor else lightGreen,fontWeight = FontWeight.Bold,modifier = Modifier.offset(73.dp,380.dp),fontSize = 105.sp)
            Text(text = "3" ,color = yellow,fontWeight = FontWeight.Bold,modifier = Modifier.offset(73.dp,195.dp),fontSize = 105.sp)
            Text(text = "PLAYER",color= Color.White,textDecoration = TextDecoration.Underline,fontWeight = FontWeight.Bold,modifier = Modifier.offset(45.dp,495.dp),fontSize = 30.sp)


            CustomStyledTextField(
                value = playerNameThree,
                enable = true,
                onValueChange = { playerNameThree = it },
                placeholder = "Enter Player-3 Name",
                textColor = yellow,
                placeholderColor = Color.Gray,
                backgroundColor = Color.Transparent,
                cursorColor = yellow,
                textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                offsetX = if(playerNameThree == "")
                {
                    210.dp
                }
                else
                {
                    260.dp
                },
                offsetY = 315.dp
            )


            CustomStyledTextField(
                value = playerNameFour,
                enable = !light,
                onValueChange = { playerNameFour = it },
                placeholder = "Enter Player-4 Name",
                textColor = lightGreen,
                placeholderColor = Color.Gray,
                backgroundColor = Color.Transparent,
                cursorColor = lightGreen,
                textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                offsetX = if(playerNameFour == "")
                {
                    210.dp
                }
                else
                {
                    260.dp
                },
                offsetY = 495.dp
            )
        }
    }

    @Composable
    fun PlayerIcons()
    {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawBehind {
                    val u = Path().apply {
                        moveTo(300f, 1700f)
                        val rutu = Rect(200f, 1600f, 400f, 1800f)
                        arcTo(
                            rect = rutu,
                            startAngleDegrees = 0f,
                            sweepAngleDegrees = -359f,
                            forceMoveTo = true
                        )
                        moveTo(300f, 1900f)
                        val gug = Rect(150f, 1750f, 450f, 2100f)
                        arcTo(
                            rect = gug,
                            startAngleDegrees = 0f,
                            sweepAngleDegrees = -180f,
                            forceMoveTo = true
                        )
                        moveTo(175f, 1925f)
                        val dup = Rect(150f, 1900f, 200f, 1950f)
                        arcTo(
                            rect = dup,
                            startAngleDegrees = 0f,
                            sweepAngleDegrees = 180f,
                            forceMoveTo = false
                        )
                        moveTo(425f, 1925f)
                        val dupu = Rect(400f, 1900f, 450f, 1950f)
                        arcTo(
                            rect = dupu,
                            startAngleDegrees = 0f,
                            sweepAngleDegrees = 180f,
                            forceMoveTo = false
                        )


                        close()

                    }


                    val j = Path().apply {
                        moveTo(300f, 1900f)
                        val kaka = Rect(100f, 1800f, 500f, 2000f)
                        arcTo(
                            rect = kaka,
                            startAngleDegrees = -120f,
                            sweepAngleDegrees = -300f,
                            forceMoveTo = false
                        )


                    }

                    val ji = Path().apply {
                        moveTo(800f, 1900f)
                        val kakaa = Rect(600f, 1800f, 1000f, 2000f)
                        arcTo(
                            rect = kakaa,
                            startAngleDegrees = -120f,
                            sweepAngleDegrees = -300f,
                            forceMoveTo = false
                        )
                    }
                    val i = Path().apply {
                        moveTo(800f, 1700f)
                        val rutuu = Rect(700f, 1600f, 900f, 1800f)
                        arcTo(
                            rect = rutuu,
                            startAngleDegrees = 0f,
                            sweepAngleDegrees = -359f,
                            forceMoveTo = true
                        )
                        moveTo(800f, 1900f)
                        val gugu = Rect(650f, 1750f, 950f, 2100f)
                        arcTo(
                            rect = gugu,
                            startAngleDegrees = 0f,
                            sweepAngleDegrees = -180f,
                            forceMoveTo = true
                        )
                        moveTo(675f, 1925f)
                        val dupe = Rect(650f, 1900f, 700f, 1950f)
                        arcTo(
                            rect = dupe,
                            startAngleDegrees = 0f,
                            sweepAngleDegrees = 180f,
                            forceMoveTo = false
                        )
                        moveTo(925f, 1925f)
                        val dupui = Rect(900f, 1900f, 950f, 1950f)
                        arcTo(
                            rect = dupui,
                            startAngleDegrees = 0f,
                            sweepAngleDegrees = 180f,
                            forceMoveTo = false
                        )


                    }
                    drawPath(
                        path = ji,
                        color = if (light) lightenedBrown else brown
                    )
                    drawPath(
                        path = j,
                        color = brown
                    )
                    drawPath(
                        path = u,
                        color = yellow
                    )
                    drawPath(
                        path = i,
                        color = if (light) lightenedColor else lightGreen
                    )
                }
        )
    }


    @Composable
    fun InformationBar()
    {
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
            Text(text = "PLAYER INFORMATION", modifier = Modifier.offset(64.dp,125.dp), fontSize = 26.sp, fontWeight = FontWeight.Bold)
        }
    }



