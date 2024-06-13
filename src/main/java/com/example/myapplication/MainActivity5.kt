package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import kotlinx.coroutines.delay
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

var stateLoad : Boolean by mutableStateOf(true)
var globalCounter: Float by mutableFloatStateOf(105F)

var heroMode : Boolean by mutableStateOf(false)

var heroCount : Int by mutableIntStateOf(0)




class MainActivity5 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
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
                        brush = Brush.verticalGradient(
                            colors =if(themeOnChange == 1) gradientColorlist else gradientColorDark
                        )
                    )
            )
            {
                GameStartBoard()
                GameTip()
                Grid5x5(
                    modifier = Modifier
                        .offset(x = 50.dp, y = 350.dp))
                if(globalCounter <900)
                {
                    Button(
                        onClick = {

                            if(globalPlayer != "2" && globalPlayer != "NONE" )
                            {
                                stateLoad = false
                                val intentGameOne = Intent(this@MainActivity5,MainActivity4::class.java)
                                startActivity(intentGameOne)
                                finish()


                            }
                            else
                            {
                                stateLoad = false
                                Intent(applicationContext, MainActivity3::class.java).also {
                                    startActivity(it)
                                    finish()
                                }
                            }

                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        modifier = Modifier.offset((-15).dp, (30).dp)
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

                LaunchedEffect(stateLoad) {
                    if(stateLoad)
                    {
                        while (globalCounter < 990F) {
                            delay(1000L)
                            if(globalCounter <900F)
                            {
                                globalCounter += 70F
                            }
                            else
                            {
                                globalCounter += 10F
                            }

                        }
                    }
                    else
                    {
                        if(globalCounter >980F)
                        {
                            globalCounter = 980F
                        }
                        else
                        {
                            globalCounter = 105F
                        }

                    }
                }
                if(globalCounter == 995F)
                {
                    stateLoad = false
                    if(globalMode == "NORMAL MODE")
                    {
                        if(globalPlayer == "2")
                        {
                            Intent(applicationContext, MainActivity6::class.java).also {
                                startActivity(it)
                                finish()
                            }
                        }
                        else if(globalPlayer == "3")
                        {
                            Intent(applicationContext, MainActivity7::class.java).also {
                                startActivity(it)
                                finish()
                            }
                        }

                    }
                    else if(globalMode == "TIMER MODE")
                    {
                        Intent(applicationContext, MainActivity8::class.java).also {
                            startActivity(it)
                            finish()
                        }
                    }
                    else if(globalMode == "GOD MODE")
                    {
                        globalPlayerTwo = -1
                        Intent(applicationContext, MainActivity9::class.java).also {
                            startActivity(it)
                            finish()
                        }

                    }
                    else if(globalMode == "HERO MODE")
                    {
                        if(globalSubMode == "BEST OF 3")
                        {
                            heroCount = 3
                        }
                        else if(globalSubMode == "BEST OF 5")
                        {
                            heroCount = 5
                        }
                        heroMode = true
                        Intent(applicationContext, MainActivity6::class.java).also {
                            startActivity(it)
                            finish()
                        }
                    }

                    globalCounter = 994F
                    Log.d("Im","Load")
                }
                GameLoad(z = globalCounter)
            }
        }
    }
}
@Composable
fun GameLoad(z:Float)
{
    Box(
        modifier = Modifier
            .fillMaxSize()
            .drawBehind()
            {
                val pathThree = Path().apply {
                    moveTo(100f, 1900f)
                    lineTo(1000f, 1900f)
                    lineTo(1000f, 2000f)
                    lineTo(100f, 2000f)
                }
                val pathFour = Path().apply {
                    moveTo(110f, 1910f)
                    lineTo(z, 1910f)
                    lineTo(z, 1990f)
                    lineTo(110f, 1990f)

                }

                drawPath(
                    path = pathThree,
                    color = androidx.compose.ui.graphics.Color.Black
                )
                drawPath(
                    path = pathFour,
                    color =  androidx.compose.ui.graphics.Color.Red
                )


            }
    )
}

@Composable
fun GameTip()
{
    Box(modifier = Modifier.fillMaxSize()
        .drawBehind {
            val pathSeven = Path().apply {
                moveTo(150f,600f)
                lineTo(940f,600f)
                lineTo(940f,1800f)
                lineTo(150f,1800f)

                moveTo(150f,650f)
                val cirOne = Rect(100f,600f,200f,700f)
                arcTo(
                    rect = cirOne,
                    startAngleDegrees = -90f,
                    sweepAngleDegrees = -90f,
                    forceMoveTo = false
                )

                moveTo(940f,650f)
                val cirTwo = Rect(890f,600f,990f,700f)
                arcTo(
                    rect = cirTwo,
                    startAngleDegrees = -90f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )

                moveTo(150f,1750f)
                val cirThree = Rect(100f,1700f,200f,1800f)
                arcTo(
                    rect = cirThree,
                    startAngleDegrees = 90f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )

                moveTo(940f,1750f)
                val cirFour = Rect(890f,1700f,990f,1800f)
                arcTo(
                    rect = cirFour,
                    startAngleDegrees = 90f,
                    sweepAngleDegrees = -90f,
                    forceMoveTo = false
                )

                moveTo(150f,650f)
                lineTo(100f,650f)
                lineTo(100f,1750f)
                lineTo(150f,1750f)

                moveTo(940f,650f)
                lineTo(990f,650f)
                lineTo(990f,1750f)
                lineTo(940f,1750f)

            }
            val line = Path().apply{
                moveTo(120f,750f)
                lineTo(210f,750f)
                lineTo(210f,760f)
                lineTo(120f,760f)

                moveTo(525f,750f)
                lineTo(960f,750f)
                lineTo(960f,760f)
                lineTo(525f,760f)
            }

            drawPath(
                path = pathSeven,
                color = darkPurple
            )
            drawPath(
                path = line,
                color = Color.White
            )
        })
    {
        Text(text = "Focus ",fontWeight = FontWeight.Bold,fontSize = 40.sp,color = Color.White,modifier=Modifier.offset(80.dp,242.dp))
        Text(text = "MORE ON STARTING AT EDGES",fontWeight = FontWeight.Bold,fontSize = 20.sp,color = Color.White,modifier=Modifier.offset(50.dp,320.dp))

    }

}

@Composable
fun Grid5x5(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        for (i in 0 until 5) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                for (j in 0 until 5) {
                    Box(
                        modifier = Modifier
                            .height(50.dp)
                            .width(50.dp)
                            .padding(4.dp)
                            .background(color = if((i == 0 && j == 0)||(i == 4 && j == 0)||(i == 0 && j == 4)||(i == 4 && j == 4))lightBlue else cellColor,shape = RoundedCornerShape(10.dp)) // You can change the color as per your requirement
                    ) {
                        // You can place any content here, such as text or images
                    }
                }
            }
        }
    }
}


@Composable
fun GameStartBoard() {
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
            text = "GAME TIP",
            modifier = Modifier.offset(140.dp, 125.dp),
            fontSize = 26.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
        )
    }
}