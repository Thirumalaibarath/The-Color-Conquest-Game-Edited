package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableOpenTarget
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

val gson = Gson()
var primeReciever: MutableList<MutableList<String>> by mutableStateOf(mutableListOf())


var trophyOne : String by mutableStateOf("")
var trophyTwo : String by mutableStateOf("")
var trophyThree : String by mutableStateOf("")






class MainActivity10 : ComponentActivity() {
    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            if(loadData)
            {
                saveData()
                loadData = false
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



            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = if(themeOnChange == 1) gradientColorlist else gradientColorDark
                        )
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(30.dp))
                History()
                provider()

            }
        }
    }

    @Composable
    fun NoGame()
    {
        Box(
            modifier = Modifier.fillMaxSize()
            , contentAlignment = Alignment.Center
        )
        {
            Text("PLAY A GAME FELLA..!",fontSize = 30.sp,color = Color.White,fontWeight = FontWeight.Bold,modifier = Modifier.offset(0.dp,250.dp))
        }
    }

    private fun saveData() {
        val sharedPreferences = applicationContext.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Load existing data
        val existingData = loadDataFromPreferences()

        // Merge only unique new data with existing data
        primeReciever.forEach { newEntry ->
            if (!existingData.contains(newEntry)) {
                existingData.add(newEntry)
            }
        }

        val json = gson.toJson(existingData)
        editor.putString("primeReceiverKey", json)
        editor.apply()
    }


    private fun loadDataFromPreferences():MutableList<MutableList<String>> {
        val sharedPreferences = applicationContext.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val savedJson = sharedPreferences.getString("primeReceiverKey", null)

        val type = object : TypeToken<MutableList<MutableList<String>>>() {}.type
        return if (savedJson != null) {
            gson.fromJson(savedJson, type)
        } else {
            mutableListOf()
        }
    }


    @Composable
    fun History()
    {
        Box( modifier = Modifier
            .background(
                color = lightBrown, shape = RoundedCornerShape(20.dp)
            )
            .padding(16.dp),
            contentAlignment = Alignment.Center
        )
        {
            Text(text = "PLAYER HISTORY", fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)
        }
    }
    @Composable
    fun provider() {

        val sharedPreferences = applicationContext.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        val savedJson = sharedPreferences.getString("primeReceiverKey", null)

        val type = object : TypeToken<MutableList<MutableList<String>>>() {}.type
        val primeListTwo = gson.fromJson<MutableList<MutableList<String>>>(savedJson, type)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(25.dp))

            if(primeListTwo != null && primeListTwo.isNotEmpty())
            {
                primeListTwo.forEachIndexed { index,mutStringState ->
                    InfoBox(mutStringState,index)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            else
            {
                NoGame()
                Log.d("Hii I'm empty","hello")
            }
        }
    }

    @Composable
    fun InfoBox(mutStringStates: MutableList<String>,Index:Int) {

        if(mutStringStates.size == 5 || mutStringStates.size == 6 )
        {
            if(mutStringStates[2].toInt() > mutStringStates[4].toInt())
            {
                trophyOne = "gold"
                trophyTwo = "silver"
            }
            else if(mutStringStates[2].toInt() == mutStringStates[4].toInt())
            {
                trophyOne = "-"
                trophyTwo = "-"
            }
            else
            {
                trophyTwo = "gold"
                trophyOne = "silver"
            }
        }
        else if(mutStringStates.size == 10)
        {
            if(mutStringStates[7] == "0")
            {
                trophyOne = "gold"
            }
            else if(mutStringStates[7]== "2")
            {
                trophyOne = "silver"
            }
            else if(mutStringStates[7] == "1")
            {
                trophyOne = "bronze"
            }


            if(mutStringStates[8] == "0")
            {
                trophyTwo = "gold"
            }
            else if(mutStringStates[8] == "2")
            {
                trophyTwo = "silver"
            }
            else if(mutStringStates[8] == "1")
            {
                trophyTwo = "bronze"
            }

            if(mutStringStates[9]== "0")
            {
                trophyThree = "gold"
            }
            else if(mutStringStates[9]== "2")
            {
                trophyThree = "silver"
            }
            else if(mutStringStates[9] == "1")
            {
                trophyThree = "bronze"
            }


        }



        if(mutStringStates[2].length == 1)
        {
            mutStringStates[2] =  "0" + mutStringStates[2]
        }

        if(mutStringStates[4].length == 1)
        {
            mutStringStates[4] = "0" + mutStringStates[4]
        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = darkBlue, shape = RoundedCornerShape(30.dp)
                )
                .padding(16.dp),
            contentAlignment = Alignment.Center)

        {
            Column (
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Box(
                    modifier = Modifier
                        .background(shape = RoundedCornerShape(20.dp), color = brown)
                        .width(290.dp)
                        .height(60.dp)
                    , contentAlignment = Alignment.Center
                )
                {
                    Text(text = "#"+(Index+1).toString() +" "+ mutStringStates[0],fontSize = 30.sp,color = Color.White,fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(25.dp))
                if(mutStringStates[0] == "NORMAL MODE" || mutStringStates[0] == "GOD MODE")
                {
                    if(mutStringStates.size == 5)
                    {

                        Box(
                            modifier = Modifier
                                .background(shape = RoundedCornerShape(20.dp), color = lightRed)
                                .height(60.dp)
                                .width(270.dp)
                            , contentAlignment = Alignment.Center
                        )
                        {
                            Row(verticalAlignment  = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center) {
                                Text(text = "1.",fontWeight = FontWeight.Bold,fontSize = 25.sp,color = Color.Black)
                                Spacer(modifier = Modifier.width(25.dp))
                                Box(modifier = Modifier.width(110.dp),
                                    contentAlignment = Alignment.Center )
                                {
                                    Text(text = mutStringStates[1].toUpperCase() , fontWeight = FontWeight.Bold,fontSize = 25.sp,color = Color.Black)
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(text =  mutStringStates[2] ,color = Color.Black,fontWeight = FontWeight.Bold,fontSize = 25.sp)
                                Spacer(modifier = Modifier.width(10.dp))
                                Image(
                                    painter = if(trophyOne == "gold")
                                    {
                                        painterResource(id = R.drawable.gold)
                                    }
                                    else
                                    {
                                        painterResource(id = R.drawable.silver)
                                    },
                                    contentDescription = "My Image",
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Box(
                            modifier = Modifier
                                .background(shape = RoundedCornerShape(20.dp), color = lightBlue)
                                .height(60.dp)
                                .width(270.dp)
                            , contentAlignment = Alignment.Center
                        )
                        {
                            Row(verticalAlignment  = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center) {
                                Text(text = "2.",fontWeight = FontWeight.Bold,fontSize = 25.sp,color = Color.Black)
                                Spacer(modifier = Modifier.width(25.dp))
                                Box(modifier = Modifier.width(110.dp),
                                    contentAlignment = Alignment.Center )
                                {
                                    Text(text = mutStringStates[3].toUpperCase() , fontWeight = FontWeight.Bold,fontSize = 25.sp,color = Color.Black)
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(text =  mutStringStates[4] ,color = Color.Black,fontWeight = FontWeight.Bold,fontSize = 25.sp)
                                Spacer(modifier = Modifier.width(10.dp))
                                Image(
                                    painter = if(trophyTwo == "gold")
                                    {
                                        painterResource(id = R.drawable.gold)
                                    }
                                    else
                                    {
                                        painterResource(id = R.drawable.silver)
                                    },
                                    contentDescription = "My Image",
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                    else if(mutStringStates.size == 10)
                    {

                        Box(
                            modifier = Modifier
                                .background(shape = RoundedCornerShape(20.dp), color = lightRed)
                                .height(60.dp)
                                .width(270.dp)
                            , contentAlignment = Alignment.Center
                        )
                        {
                            Row(verticalAlignment  = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center) {
                                Text(text = "1.",fontWeight = FontWeight.Bold,fontSize = 25.sp,color = Color.Black)
                                Spacer(modifier = Modifier.width(25.dp))
                                Box(modifier = Modifier.width(110.dp),
                                    contentAlignment = Alignment.Center )
                                {
                                    Text(text = mutStringStates[1].toUpperCase() , fontWeight = FontWeight.Bold,fontSize = 25.sp,color = Color.Black)
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(text =  if(mutStringStates[2] == "-1") "0" else mutStringStates[2] ,color = Color.Black,fontWeight = FontWeight.Bold,fontSize = 25.sp)
                                Spacer(modifier = Modifier.width(10.dp))
                                Image(
                                    painter = if(trophyOne == "gold")
                                    {
                                        painterResource(id = R.drawable.gold)
                                    }
                                    else if(trophyOne == "silver")
                                    {
                                        painterResource(id = R.drawable.silver)
                                    }
                                    else
                                    {
                                        painterResource(id = R.drawable.bronze)
                                    },
                                    contentDescription = "My Image",
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Box(
                            modifier = Modifier
                                .background(shape = RoundedCornerShape(20.dp), color = lightBlue)
                                .height(60.dp)
                                .width(270.dp)
                            , contentAlignment = Alignment.Center
                        )
                        {
                            Row(verticalAlignment  = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center) {
                                Text(text = "2.",fontWeight = FontWeight.Bold,fontSize = 25.sp,color = Color.Black)
                                Spacer(modifier = Modifier.width(25.dp))
                                Box(modifier = Modifier.width(110.dp),
                                    contentAlignment = Alignment.Center )
                                {
                                    Text(text = mutStringStates[3].toUpperCase() , fontWeight = FontWeight.Bold,fontSize = 25.sp,color = Color.Black)
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(text =  if(mutStringStates[4] == "-1") "0" else mutStringStates[4] ,color = Color.Black,fontWeight = FontWeight.Bold,fontSize = 25.sp)
                                Spacer(modifier = Modifier.width(10.dp))
                                Image(
                                    painter = if(trophyTwo == "gold")
                                    {
                                        painterResource(id = R.drawable.gold)
                                    }
                                    else if(trophyTwo == "silver")
                                    {
                                        painterResource(id = R.drawable.silver)
                                    }
                                    else
                                    {
                                        painterResource(id = R.drawable.bronze)
                                    },
                                    contentDescription = "My Image",
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Box(
                            modifier = Modifier
                                .background(shape = RoundedCornerShape(20.dp), color = yellow)
                                .height(60.dp)
                                .width(270.dp)
                            , contentAlignment = Alignment.Center
                        )
                        {
                            Row(verticalAlignment  = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center) {
                                Text(text = "3.",fontWeight = FontWeight.Bold,fontSize = 25.sp,color = Color.Black)
                                Spacer(modifier = Modifier.width(25.dp))
                                Box(modifier = Modifier.width(110.dp),
                                    contentAlignment = Alignment.Center )
                                {
                                    Text(text = mutStringStates[5].toUpperCase() , fontWeight = FontWeight.Bold,fontSize = 25.sp,color = Color.Black)
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(text =  if(mutStringStates[6] == "-1") "0" else mutStringStates[6] ,color = Color.Black,fontWeight = FontWeight.Bold,fontSize = 25.sp)
                                Spacer(modifier = Modifier.width(10.dp))
                                Image(
                                    painter = if(trophyThree == "gold")
                                    {
                                        painterResource(id = R.drawable.gold)
                                    }
                                    else if(trophyThree == "silver")
                                    {
                                        painterResource(id = R.drawable.silver)
                                    }
                                    else
                                    {
                                        painterResource(id = R.drawable.bronze)
                                    },
                                    contentDescription = "My Image",
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                    }


                }
                else if(mutStringStates[0] == "TIMER MODE")
                {

                    Box(
                        modifier = Modifier.background(shape = RoundedCornerShape(20.dp),color = brown)
                            .width(200.dp)
                            .height(60.dp)
                        , contentAlignment = Alignment.Center
                    )
                    {
                        Text(text = "TIME: "+mutStringStates[5],fontSize = 30.sp,fontWeight = FontWeight.Bold,color = Color.White)
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Box(
                        modifier = Modifier
                            .background(shape = RoundedCornerShape(20.dp), color = lightBlue)
                            .height(60.dp)
                            .width(270.dp)
                        , contentAlignment = Alignment.Center
                    )
                    {
                        Row(verticalAlignment  = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center) {
                            Text(text = "1.",fontWeight = FontWeight.Bold,fontSize = 25.sp,color = Color.Black)
                            Spacer(modifier = Modifier.width(25.dp))
                            Box(modifier = Modifier.width(110.dp),
                                contentAlignment = Alignment.Center )
                            {
                                Text(text = mutStringStates[1].toUpperCase() , fontWeight = FontWeight.Bold,fontSize = 25.sp,color = Color.Black)
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text =  mutStringStates[2],color = Color.Black,fontWeight = FontWeight.Bold,fontSize = 25.sp)
                            Spacer(modifier = Modifier.width(10.dp))
                            Image(
                                painter = if(trophyOne == "gold")
                                {
                                    painterResource(id = R.drawable.gold)
                                }
                                else if(trophyOne == "-")
                                {
                                    painterResource(id = R.drawable.dash)
                                }
                                else
                                {
                                    painterResource(id = R.drawable.silver)
                                }
                                ,
                                contentDescription = "My Image",
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Box(
                        modifier = Modifier
                            .background(shape = RoundedCornerShape(20.dp), color = lightRed)
                            .height(60.dp)
                            .width(270.dp)
                        , contentAlignment = Alignment.Center
                    )
                    {
                        Row(verticalAlignment  = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center) {
                            Text(text = "2.",fontWeight = FontWeight.Bold,fontSize = 25.sp,color = Color.Black)
                            Spacer(modifier = Modifier.width(25.dp))
                            Box(modifier = Modifier.width(110.dp),
                                contentAlignment = Alignment.Center )
                            {
                                Text(text = mutStringStates[3].toUpperCase() , fontWeight = FontWeight.Bold,fontSize = 25.sp,color = Color.Black)
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text =  mutStringStates[4] ,color = Color.Black,fontWeight = FontWeight.Bold,fontSize = 25.sp)
                            Spacer(modifier = Modifier.width(10.dp))
                            Image(
                                painter = if(trophyTwo == "gold")
                                {
                                    painterResource(id = R.drawable.gold)
                                }
                                else if(trophyTwo == "-")
                                {
                                    painterResource(id = R.drawable.dash)
                                }
                                else
                                {
                                    painterResource(id = R.drawable.silver)
                                },
                                contentDescription = "My Image",
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                }

                else if(mutStringStates[0] == "HERO MODE")
                    {
                    Box( modifier = Modifier.background(shape = RoundedCornerShape(20.dp)
                        ,color = brown)
                        .width(200.dp)
                        .height(60.dp)
                        , contentAlignment = Alignment.Center)
                    {
                        Text(mutStringStates[5],fontSize = 30.sp,fontWeight = FontWeight.Bold,color = Color.White)
                    }

                        Spacer(modifier = Modifier.height(20.dp))


                        Box(
                            modifier = Modifier
                                .background(shape = RoundedCornerShape(20.dp), color = lightBlue)
                                .height(60.dp)
                                .width(270.dp)
                            , contentAlignment = Alignment.Center
                        )
                        {
                            Row(verticalAlignment  = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center) {
                                Text(text = "1.",fontWeight = FontWeight.Bold,fontSize = 25.sp,color = Color.Black)
                                Spacer(modifier = Modifier.width(25.dp))
                                Box(modifier = Modifier.width(110.dp),
                                    contentAlignment = Alignment.Center )
                                {
                                    Text(text = mutStringStates[1].toUpperCase() , fontWeight = FontWeight.Bold,fontSize = 25.sp,color = Color.Black)
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(text =  mutStringStates[2] ,color = Color.Black,fontWeight = FontWeight.Bold,fontSize = 25.sp)
                                Spacer(modifier = Modifier.width(10.dp))
                                Image(
                                    painter = if(trophyOne == "gold")
                                    {
                                        painterResource(id = R.drawable.gold)
                                    }
                                    else
                                    {
                                        painterResource(id = R.drawable.silver)
                                    },
                                    contentDescription = "My Image",
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Box(
                            modifier = Modifier
                                .background(shape = RoundedCornerShape(20.dp), color = lightRed)
                                .height(60.dp)
                                .width(270.dp)
                            , contentAlignment = Alignment.Center
                        )
                        {
                            Row(verticalAlignment  = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center) {
                                Text(text = "2.",fontWeight = FontWeight.Bold,fontSize = 25.sp,color = Color.Black)
                                Spacer(modifier = Modifier.width(25.dp))
                                Box(modifier = Modifier.width(110.dp),
                                    contentAlignment = Alignment.Center )
                                {
                                    Text(text = mutStringStates[3].toUpperCase() , fontWeight = FontWeight.Bold,fontSize = 25.sp,color = Color.Black)
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(text =  mutStringStates[4],color = Color.Black,fontWeight = FontWeight.Bold,fontSize = 25.sp)
                                Spacer(modifier = Modifier.width(10.dp))
                                Image(
                                    painter = if(trophyTwo == "gold")
                                    {
                                        painterResource(id = R.drawable.gold)
                                    }
                                    else
                                    {
                                        painterResource(id = R.drawable.silver)
                                    },
                                    contentDescription = "My Image",
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))


                    }

            }
        }

    }


    @Composable
    fun InfoCard(texts: List<String>, textColor: Color, backgroundColor: Color) {
        Box( modifier = Modifier
            .width(160.dp)
            .height(250.dp) // Increased height to accommodate the image and text
            .padding(10.dp)
            .background(color = backgroundColor, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center)
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp), // Added padding to the bottom for the text
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(15.dp))

                Image(
                    painter = painterResource(id = R.drawable.gold),
                    contentDescription = "My Image",
                    modifier = Modifier.size(80.dp)
                )

                Spacer(modifier = Modifier.height(15.dp))

                texts.forEach { text ->
                    Text(
                        text = text.uppercase(),
                        fontSize = 25.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }

@Composable
fun HistoryBoard() {
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
            text = "PLAYER HISTORY",
            modifier = Modifier.offset(64.dp, 125.dp),
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
}


