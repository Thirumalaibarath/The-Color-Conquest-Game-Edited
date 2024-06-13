package com.example.myapplication

import android.content.Intent
import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp



var mode : Int by mutableIntStateOf(-1)
var globalMode : String by mutableStateOf("")
var globalSubMode : String by mutableStateOf("")
var globalRow : String by mutableStateOf("0")
var globalColumn : String by mutableStateOf("0")
var globalPlayer :String by mutableStateOf("")

var heroOnePoint : Int by mutableIntStateOf(0)
var heroTwoPoint : Int by mutableIntStateOf(0)


class MainActivity2 : ComponentActivity() {
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
                        brush = gradientBrush(
                            isVerticalGradient = true,
                            colors = if(themeOnChange == 1) gradientColorlist else gradientColorDark
                        )
                    ),
                contentAlignment = Alignment.Center
            )
            {
                if(mode != 1 && mode != 2)
                {
                    globalSubMode = "NONE"

                }
                if(mode != 0)
                {
                    globalPlayer = "NONE"
                }


                if(globalMode != "" && globalSubMode !="" && globalRow != "0" && globalColumn !="0" &&  globalPlayer != "")
                {
                    Button(
                        onClick ={
                            if(globalMode != "GOD MODE")
                            {
                                playerNameTwo = ""
                            }
                            val intent = Intent(this@MainActivity2,MainActivity3::class.java)
                            startActivity(intent)
                            finish()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent),
                        modifier = Modifier.offset(170.dp, (-340).dp))
                    {
                        Icon(imageVector = Icons.Default.ArrowForward , contentDescription = "Arrow icon",modifier = Modifier.size(48.dp),tint = if(themeOnChange ==1)darkBlue else Color.White)
                    }

                }

                GameModePic()

                Button(
                    onClick ={
                        Intent(applicationContext,MainActivity::class.java).also{
                            startActivity(it)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent),
                    modifier = Modifier.offset((-170).dp, (-340).dp))
                {
                    Icon(imageVector = Icons.Default.ArrowBack , contentDescription = "Arrow icon",modifier = Modifier.size(48.dp),tint = if(themeOnChange ==1)darkBlue else Color.White)

                }

                GameSubModePic()
                if(mode == 1 || mode == 2 )
                {
                    TimeVsDropDown()
                }
                if(mode == 0 )
                {
                    PlayerDropDown()
                }
                RowDropDown()
                ColumnDropDown()
                PlayerPic()

            }

        }
    }
}



@Composable
fun GameModePic()
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
        Text(text = "GAME MODE", modifier = Modifier.offset(120.dp,125.dp), fontSize = 26.sp, fontWeight = FontWeight.Bold)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameSubModePic()
{
    val list = listOf("NORMAL MODE","HERO MODE","TIMER MODE","GOD MODE")

    val selectedText = remember {
        mutableStateOf(globalMode)
    }

    val isExpanded = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .offset(5.dp, (-150).dp),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        ExposedDropdownMenuBox(expanded = isExpanded.value, onExpandedChange ={
            isExpanded.value = !isExpanded.value
        } ) {
            TextField(value = selectedText.value, onValueChange ={},
                modifier = Modifier
                    .menuAnchor()
                    .width(300.dp)
                    .height(70.dp)
                , readOnly = true, label = {Text(text="SELECT GAME MODE",fontSize = 15.sp,color = Color.White,fontWeight = FontWeight.Bold)}, textStyle = TextStyle.Default.copy(fontSize = 25.sp,color = Color.White,fontWeight = FontWeight.Bold)  ,
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedLabelColor =Color.Transparent,
                    focusedLabelColor = Color.Transparent,
                    cursorColor = darkBlue,
                    focusedLeadingIconColor = darkBlue,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedLeadingIconColor = darkBlue,
                    containerColor = darkBlue,
                    focusedTrailingIconColor = Color.White,
                    unfocusedTrailingIconColor = Color.White,
                ),shape = RoundedCornerShape(25.dp),
                enabled = false,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded.value ) })

            ExposedDropdownMenu(expanded = isExpanded.value, onDismissRequest ={isExpanded.value = false}  ) {
                list.forEachIndexed{
                        index , text ->
                    DropdownMenuItem(text = { Text(text = text,style = TextStyle.Default.copy(fontSize = 16.sp,fontWeight = FontWeight.Bold))
                    }, onClick = {
                        selectedText.value = list[index]
                        isExpanded.value = false
                        get(index)
                        globalMode = list[index]
                    },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding)
                }

            }

        }


    }
}

@Composable
fun PlayerPic()
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
                    path = j,
                    color = brown
                )
                drawPath(
                    path = u,
                    color = lightBlue
                )
                drawPath(
                    path = i,
                    color = lightRed
                )
            }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowDropDown()
{
    val orient = when (mode) {
        1, 2 -> {
            20
        }
        0 -> {
            20
        }
        else -> {
            -60
        }
    }

    val listThree = listOf("3","4","5","6","7")

    val selectedTextThree = remember {
        mutableStateOf(globalRow)
    }

    val isExpandedThree = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .offset((-80).dp, orient.dp),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        ExposedDropdownMenuBox(expanded = isExpandedThree.value, onExpandedChange ={
            isExpandedThree.value = !isExpandedThree.value
        } ) {
            TextField(value = selectedTextThree.value, onValueChange ={},
                modifier = Modifier
                    .menuAnchor()
                    .width(120.dp)
                    .height(90.dp)
                , readOnly = true, label = {Text(text="SELECT ROW",fontSize = 10.sp,color = Color.White,fontWeight = FontWeight.Bold)}, textStyle = TextStyle.Default.copy(fontSize = 25.sp,color = Color.White,fontWeight = FontWeight.Bold)  ,
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedLabelColor =Color.Transparent,
                    focusedLabelColor = Color.Transparent,
                    cursorColor = darkBlue,
                    focusedLeadingIconColor = darkBlue,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedLeadingIconColor = darkBlue,
                    containerColor = darkBlue,
                    focusedTrailingIconColor = Color.White,
                    unfocusedTrailingIconColor = Color.White,
                ),shape = RoundedCornerShape(25.dp),
                enabled = false,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedThree.value ) })

            ExposedDropdownMenu(expanded = isExpandedThree.value, onDismissRequest ={isExpandedThree.value = false}  ) {
                listThree.forEachIndexed{
                        index , text ->
                    DropdownMenuItem(text = { Text(text = text,style = TextStyle.Default.copy(fontSize = 16.sp,fontWeight = FontWeight.Bold))
                    }, onClick = {
                        selectedTextThree.value = listThree[index]
                        isExpandedThree.value = false
                        globalRow = listThree[index]

                    },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding)
                }

            }

        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnDropDown()
{

    val orientTwo = when (mode) {
        1, 2 -> {
            20
        }
        0 -> {
            20
        }
        else -> {
            -60
        }
    }

    val listFour = listOf("3","4","5","6","7")

    val selectedTextFour = remember {
        mutableStateOf(globalColumn)
    }

    val isExpandedFour= remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .offset(80.dp, orientTwo.dp),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        ExposedDropdownMenuBox(expanded = isExpandedFour.value, onExpandedChange ={
            isExpandedFour.value = !isExpandedFour.value
        } ) {
            TextField(value = selectedTextFour.value, onValueChange ={},
                modifier = Modifier
                    .menuAnchor()
                    .width(120.dp)
                    .height(90.dp)
                , readOnly = true, label = {Text(text="SELECT COLUMN",fontSize = 10.sp,color = Color.White,fontWeight = FontWeight.Bold)}, textStyle = TextStyle.Default.copy(fontSize = 25.sp,color = Color.White,fontWeight = FontWeight.Bold)  ,
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedLabelColor =Color.Transparent,
                    focusedLabelColor = Color.Transparent,
                    cursorColor = darkBlue,
                    focusedLeadingIconColor = darkBlue,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedLeadingIconColor = darkBlue,
                    containerColor = darkBlue,
                    focusedTrailingIconColor = Color.White,
                    unfocusedTrailingIconColor = Color.White,
                ),shape = RoundedCornerShape(25.dp),
                enabled = false,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedFour.value ) })

            ExposedDropdownMenu(expanded = isExpandedFour.value, onDismissRequest ={isExpandedFour.value = false}  ) {
                listFour.forEachIndexed{
                        index , text ->
                    DropdownMenuItem(text = { Text(text = text,style = TextStyle.Default.copy(fontSize = 16.sp,fontWeight = FontWeight.Bold))
                    }, onClick = {
                        selectedTextFour.value = listFour[index]
                        isExpandedFour.value = false
                        globalColumn = listFour[index]

                    },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding)
                }

            }

        }


    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeVsDropDown()
{
    val listTwo = analyser(mode)

    val typo = if(mode == 1){
        "VS"
    }
    else
    {
        "TIME DURATION"
    }

    val selectedTextTwo = remember {
        mutableStateOf(globalSubMode)
    }

    val isExpandedTwo = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .offset(5.dp, (-70).dp),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        ExposedDropdownMenuBox(expanded = isExpandedTwo.value, onExpandedChange ={
            isExpandedTwo.value = !isExpandedTwo.value
        } ) {
            TextField(value = selectedTextTwo.value, onValueChange ={},
                modifier = Modifier
                    .menuAnchor()
                    .width(300.dp)
                    .height(70.dp)
                , readOnly = true, label = {Text(text= "SELECT $typo",fontSize = 15.sp,color = Color.White,fontWeight = FontWeight.Bold)}, textStyle = TextStyle.Default.copy(fontSize = 25.sp,color = Color.White,fontWeight = FontWeight.Bold)  ,
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedLabelColor =Color.Transparent,
                    focusedLabelColor = Color.Transparent,
                    cursorColor = darkBlue,
                    focusedLeadingIconColor = darkBlue,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedLeadingIconColor = darkBlue,
                    containerColor = darkBlue,
                    focusedTrailingIconColor = Color.White,
                    unfocusedTrailingIconColor = Color.White,
                ),shape = RoundedCornerShape(25.dp),
                enabled = false,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedTwo.value ) })

            ExposedDropdownMenu(expanded = isExpandedTwo.value, onDismissRequest ={isExpandedTwo.value = false}  ) {
                listTwo.forEachIndexed{
                        index , text ->
                    DropdownMenuItem(text = { Text(text = text)
                    }, onClick = {
                        selectedTextTwo.value = listTwo[index]
                        isExpandedTwo.value = false
                        globalSubMode = listTwo[index]

                    },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding)
                }

            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerDropDown()
{
    val listFive = analyser(mode)


    val selectedTextFive = remember {
        mutableStateOf(globalPlayer)
    }

    val isExpandedFive = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .offset(5.dp, (-70).dp),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        ExposedDropdownMenuBox(expanded = isExpandedFive.value, onExpandedChange ={
            isExpandedFive.value = !isExpandedFive.value
        } ) {
            TextField(value = selectedTextFive.value, onValueChange ={},
                modifier = Modifier
                    .menuAnchor()
                    .width(300.dp)
                    .height(70.dp)
                , readOnly = true, label = {Text(text="SELECT PLAYER",fontSize = 15.sp,color = Color.White,fontWeight = FontWeight.Bold)}, textStyle = TextStyle.Default.copy(fontSize = 25.sp,color = Color.White,fontWeight = FontWeight.Bold)  ,
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedLabelColor =Color.Transparent,
                    focusedLabelColor = Color.Transparent,
                    cursorColor = darkBlue,
                    focusedLeadingIconColor = darkBlue,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedLeadingIconColor = darkBlue,
                    containerColor = darkBlue,
                    focusedTrailingIconColor = Color.White,
                    unfocusedTrailingIconColor = Color.White,
                ),shape = RoundedCornerShape(25.dp),
                enabled = false,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedFive.value ) })

            ExposedDropdownMenu(expanded = isExpandedFive.value, onDismissRequest ={isExpandedFive.value = false}  ) {
                listFive.forEachIndexed{
                        index , text ->
                    DropdownMenuItem(text = { Text(text = text)
                    }, onClick = {
                        selectedTextFive.value = listFive[index]
                        isExpandedFive.value = false
                        globalPlayer = listFive[index]

                    },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding)
                }

            }

        }
    }
}


fun get(v:Int)
{
    mode = v
}


fun analyser(y: Int): List<String> {
    val items = mutableListOf<String>()

    when (y) {
        1 -> {
            items.addAll(listOf("BEST OF 3", "BEST OF 5"))
        }
        2 -> {
            items.addAll(listOf("1:00", "3:00", "5:00"))
        }
        0 -> {
            items.addAll(listOf("2", "3"))
        }
    }

    return items
}
