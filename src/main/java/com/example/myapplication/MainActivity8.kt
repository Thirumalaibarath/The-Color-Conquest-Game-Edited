package com.example.myapplication

import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.toUpperCase
import kotlinx.coroutines.delay

var counter : Float by mutableFloatStateOf(995F)
var counterOne :  Float by mutableFloatStateOf(105F)
var clockState :  Boolean by mutableStateOf(true)

class MainActivity8 : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var time by remember { mutableIntStateOf(0) }

            if(globalSubMode == "1:00")
            {
                time = 60
            }
            else if(globalSubMode == "3:00")
            {
                time = 180
            }
            else
            {
                time = 300
            }

            val setter = 890/time

            Box(modifier = Modifier.fillMaxSize())
            {
                GridThree(globalRow.toInt(), globalColumn.toInt())
                PlayerNameTwoDisplay()
                PlayerNameOneDisplay()
                PlayerOneDisplay()
                PlayerTwoDisplay()
                ExitButton()

                val infoList = mutableListOf(
                    globalMode, playerNameOne, globalPlayerOne.toString(), playerNameTwo, globalPlayerTwo.toString(),
                    globalSubMode
                )

                if (pause) {
                    GamePause(onDismiss = { stateTwo = false })
                    clockState = false
                }

                if (resume) {
                    GamePause(onDismiss = { stateTwo = true })
                    resume = false
                    pause = false
                    clockState = true
                }

                if(restart)
                {
                    if((game>2&&(globalPlayerOne == 0 || globalPlayerTwo == 0)) || (counter ==99F) )
                    {
                        Display(onDismiss = { state = true })
                    }
                    if(pause)
                    {
                        GamePause(onDismiss = { stateTwo = true })
                        pause = false
                    }
                    counter = 995F
                    game = 0
                    added = false
                    clockState = true
                    colorChangerRed.clear()
                    colorChangerBlue.clear()
                    playerOneClicked.clear()
                    playerTwoClicked.clear()
                    score.fill(0)
                    globalPlayerOne = 0
                    globalPlayerTwo = 0
                    restart = false
                }

                if(returnhome)
                {
                    counter = 995F
                    pause = false
                    clockState = true
                    Display(onDismiss = {state = true})
                    if(pause)
                    {
                        GamePause(onDismiss = {stateTwo = true})
                    }
                    game = 0
                    colorChangerRed.clear()
                    colorChangerBlue.clear()
                    playerOneClicked.clear()
                    playerTwoClicked.clear()
                    score.fill(0)
                    globalPlayerOne = 0
                    globalPlayerTwo = 0
                    added = false
                    Intent(applicationContext, MainActivity::class.java).also {
                        startActivity(it)
                        finish()}
                    returnhome = false
                }


                if(counter ==99F)
                {
                    if(globalPlayerOne>globalPlayerTwo)
                    {
                        com.example.myapplication.extractor(playerNameOne.toUpperCase())
                    }
                    else if(globalPlayerOne == globalPlayerTwo)
                    {
                        com.example.myapplication.extractor("DRAW")
                    }
                    else
                    {
                        com.example.myapplication.extractor(playerNameTwo.toUpperCase())
                    }
                    Display(onDismiss = { state = false })
                    if(!added)
                    {
                        primeReciever.add(infoList)
                        loadData = true
                        added = true
                    }
                }
                else if (game >= 2 && (globalPlayerOne == 0 || globalPlayerTwo == 0))
                {
                    clockState = false
                    if (globalPlayerOne == 0) {
                        com.example.myapplication.extractor(playerNameTwo.toUpperCase())
                    } else {
                        com.example.myapplication.extractor(playerNameOne.toUpperCase())
                    }
                    Display(onDismiss = { state = false })
                    if(!added)
                    {
                        primeReciever.add(infoList)
                        loadData = true
                        added = true
                    }
                }



                LaunchedEffect(counter, clockState) {
                    if(clockState)
                    {
                        while (counter > 105F) {
                            delay(1000L)
                            counter -= setter
                        }

                    }

                }

                LaunchedEffect(counterOne, clockState) {
                    if(clockState)
                    {
                        while (counterOne < 995F) {
                            delay(1000L)
                            counterOne += setter
                        }
                    }

                }

                Board(x = counter)

            }
        }
    }
}

@Composable
fun Board(x:Float)
{
    Box(
        modifier = Modifier
            .fillMaxSize()
            .drawBehind()
            {
                val pathOne = Path().apply {
                    moveTo(100f, 2000f)
                    lineTo(1000f, 2000f)
                    lineTo(1000f, 2030f)
                    lineTo(100f, 2030f)
                }
                val pathTwo = Path().apply {
                    moveTo(105f, 2005f)
                    lineTo(x, 2005f)
                    lineTo(x, 2025f)
                    lineTo(105f, 2025f)

                }

                drawPath(
                    path = pathOne,
                    color = Color.Black
                )
                drawPath(
                    path = pathTwo,
                    color = if (x > 550F) Color.Green else if (x > 330F && x < 550F) Color.Yellow else Color.Red
                )


            }
    )
}

@Composable
fun BoardTwo(y:Float)
{
    Box(
        modifier = Modifier
            .fillMaxSize()
            .drawBehind()
            {
                val pathThree = Path().apply {
                    moveTo(100f, 100f)
                    lineTo(1000f, 100f)
                    lineTo(1000f, 130f)
                    lineTo(100f, 130f)
                }
                val pathFour = Path().apply {
                    moveTo(y, 105f)
                    lineTo(995f, 105f)
                    lineTo(995f, 125f)
                    lineTo(y, 125f)

                }

                drawPath(
                    path = pathThree,
                    color = Color.Black
                )
                drawPath(
                    path = pathFour,
                    color = if (y < 550F) Color.Green else if (y < 770F && y > 550F) Color.Yellow else Color.Red
                )


            }
    )
}

@Composable
fun GridThree(rows: Int, columns: Int) {
    val context = LocalContext.current

    Surface (
        modifier = Modifier.fillMaxSize(),
        color = if(game %2 != 0) lightBlue else lightRed) {

        val cellValues = List(rows) { rowIndex ->
            List(columns) { colIndex ->
                rowIndex * columns + colIndex
            }
        }


        val cellTexts = remember { mutableStateListOf<String>() }
        repeat(rows * columns) {
            cellTexts.add("")
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            for (rowIndex in cellValues.indices) {
                Row {
                    for (colIndex in cellValues[rowIndex].indices) {
                        val cellValue = cellValues[rowIndex][colIndex]
                        val standard = remember{ mutableStateListOf<Int>()}
                        for(d in 0..rows*columns)
                        {
                            standard.add(d)
                        }
                        val principalValues = listOf(4,5,6,7,8)


                        Cell(
                            value = cellValue,
                            changerBlue = colorChangerBlue,
                            changerRed = colorChangerRed,
                            scoree = score,
                            onClick = { clickedValue ->
                                if(restart)
                                {
                                    colorChangerRed.clear()
                                    colorChangerBlue.clear()
                                    playerTwoClicked.clear()
                                    playerOneClicked.clear()
                                    score.clear()
                                }

                                if(game == 0)
                                {
                                    colorChangerRed.add(clickedValue)
                                    playerOneClicked.add(clickedValue)
                                    score[clickedValue] = 3

                                }
                                else if(game == 1 && clickedValue !in playerOneClicked)
                                {

                                    colorChangerBlue.add(clickedValue)
                                    playerTwoClicked.add(clickedValue)
                                    score[clickedValue] = 3
                                }
                                else if(clickedValue in playerOneClicked && score[clickedValue] == 3  && game%2 == 0)
                                {
                                    playerOneClicked.remove(clickedValue)
                                    colorChangerRed.remove(clickedValue)
                                    score[clickedValue] = 0

                                    val adderOne: MutableList<Int> = colorChanger(
                                        clicked = clickedValue,
                                        column = columns
                                    ).toMutableList()


                                    val workOne: List<List<Int>> = workCommon(
                                        clickedValue,
                                        game,
                                        adderOne,
                                        score,
                                        playerOneClicked,
                                        playerTwoClicked,
                                        colorChangerBlue,
                                        colorChangerRed
                                    )
                                    var count = 0

                                    for(n in workOne[0])
                                    {
                                        score[count] = n
                                        count +=1
                                    }

                                    count = 0

                                    for(y in workOne[1])
                                    {
                                        playerOneClicked[count] = y
                                        count += 1

                                    }
                                    count = 0
                                    for(z in workOne[2])
                                    {
                                        playerTwoClicked[count] = z
                                        count += 1

                                    }
                                    count = 0
                                    for(u in workOne[3])
                                    {
                                        colorChangerBlue[count] = u
                                        count += 1

                                    }
                                    count = 0
                                    for(v in workOne[4])
                                    {
                                        colorChangerRed[count] = v
                                        count += 1
                                    }

                                }

                                else if(clickedValue in playerTwoClicked && score[clickedValue] == 3  && game%2 == 1)
                                {
                                    playerTwoClicked.remove(clickedValue)
                                    colorChangerBlue.remove(clickedValue)
                                    score[clickedValue] = 0


                                    val adderTwo : MutableList<Int> = colorChanger(
                                        clicked = clickedValue,
                                        column = columns
                                    ).toMutableList()

                                    val workTwo: List<List<Int>> = workCommon(
                                        clickedValue,
                                        game,
                                        adderTwo,
                                        score,
                                        playerOneClicked,
                                        playerTwoClicked,
                                        colorChangerBlue,
                                        colorChangerRed
                                    )
                                    var countTwo = 0

                                    for(n in workTwo[0])
                                    {
                                        score[countTwo] = n
                                        countTwo +=1
                                    }

                                    countTwo = 0

                                    for(y in workTwo[1])
                                    {
                                        playerOneClicked[countTwo] = y
                                        countTwo += 1

                                    }
                                    countTwo = 0
                                    for(z in workTwo[2])
                                    {
                                        playerTwoClicked[countTwo] = z
                                        countTwo += 1

                                    }
                                    countTwo = 0
                                    for(u in workTwo[3])
                                    {
                                        colorChangerBlue[countTwo] = u
                                        countTwo += 1

                                    }
                                    countTwo = 0
                                    for(v in workTwo[4])
                                    {
                                        colorChangerRed[countTwo] = v
                                        countTwo += 1

                                    }
                                }

                                else if(clickedValue in playerOneClicked && game%2 == 0 && score[clickedValue] < 3)
                                {
                                    score[clickedValue] += 1
                                }
                                else if(clickedValue in playerTwoClicked && game%2 == 1 && score[clickedValue] < 3)
                                {
                                    score[clickedValue] += 1
                                }
                                else
                                {
                                    game -= 1
                                    Toast.makeText(context, "WRONG TURN", Toast.LENGTH_SHORT).show()
                                    Log.d("GAME","Rebound")
                                }
                                var extra = 0

                                while(score.any { it in principalValues })
                                {
                                    while(gridChecker(score))
                                    {
                                        val indeX  = valueReturner(score,4)

                                        var county = 0

                                        val adderEx: MutableList<Int> = colorChanger(
                                            clicked = indeX,
                                            column = globalColumn.toInt()
                                        ).toMutableList()

                                        for (cu in fourAdder(indeX,adderEx,score))
                                        {
                                            score[county] = cu
                                            county+=1
                                        }


                                        if(indeX in colorChangerRed)
                                        {
                                            for(xu in adderEx)
                                            {
                                                if(xu !in colorChangerRed)
                                                {
                                                    colorChangerRed.add(xu)
                                                    colorChangerBlue.remove(xu)
                                                    playerTwoClicked.remove(xu)
                                                    playerOneClicked.add(xu)
                                                }


                                            }

                                        }
                                        else
                                        {
                                            for(vu in adderEx)
                                            {
                                                if(vu !in colorChangerBlue)
                                                {
                                                    colorChangerBlue.add(vu)
                                                    colorChangerRed.remove(vu)
                                                    playerOneClicked.remove(vu)
                                                    playerTwoClicked.add(vu)
                                                }
                                            }

                                        }

                                        if(adderEx.size == 4)
                                        {
                                            if(indeX in colorChangerRed)
                                            {
                                                colorChangerRed.remove(indeX)
                                                playerOneClicked.remove(indeX)
                                            }
                                            else
                                            {
                                                colorChangerBlue.remove(indeX)
                                                playerTwoClicked.remove(indeX)
                                            }
                                        }

                                    }
                                    while(moreThanFour(score))
                                    {
                                        val t = valueReturnerTwo(score)
                                        val num = valueReturner(score,t)

                                        var who = 0
                                        if(num in colorChangerRed)
                                        {
                                            who = 0
                                        }
                                        else
                                        {
                                            who = 1
                                        }


                                        extra = score[num] - 4
                                        score[num] = 4

                                        val indEX  = valueReturner(score,4)

                                        var county = 0

                                        val adderEX: MutableList<Int> = colorChanger(
                                            clicked = indEX,
                                            column = globalColumn.toInt()
                                        ).toMutableList()

                                        for (qu in fourAdder(indEX,adderEX,score))
                                        {
                                            score[county] = qu
                                            county+=1
                                        }


                                        if(indEX in colorChangerRed)
                                        {
                                            for(au in adderEX)
                                            {
                                                if(au !in colorChangerRed)
                                                {
                                                    colorChangerRed.add(au)
                                                    colorChangerBlue.remove(au)
                                                    playerTwoClicked.remove(au)
                                                    playerOneClicked.add(au)
                                                }

                                            }

                                        }
                                        else
                                        {
                                            for(ou in adderEX)
                                            {
                                                if(ou !in colorChangerBlue)
                                                {
                                                    colorChangerBlue.add(ou)
                                                    colorChangerRed.remove(ou)
                                                    playerTwoClicked.remove(ou)
                                                    playerTwoClicked.add(ou)
                                                }
                                            }

                                        }

                                        score[indEX] = extra

                                    }

                                }


                                game += 1

                                globalPlayerOne = scoreDisplayer(score,colorChangerRed)
                                globalPlayerTwo = scoreDisplayer(score,colorChangerBlue)


                            }


                        )

                    }
                }
            }
        }
    }
}