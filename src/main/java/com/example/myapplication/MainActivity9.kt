package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.toUpperCase
import kotlinx.coroutines.delay

var row : Int by mutableIntStateOf(globalRow.toInt())
var column : Int by mutableIntStateOf(globalColumn.toInt())

var gameStart : Boolean by mutableStateOf(false)

var gameOver : Boolean by mutableStateOf(false)


var initialValue : Int by mutableIntStateOf(0)




class MainActivity9 : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            Box(modifier = Modifier.fillMaxSize())
            {
                GridFour(globalRow.toInt(),globalColumn.toInt())
                PlayerNameTwoDisplay()
                PlayerNameOneDisplay()
                PlayerOneDisplay()
                PlayerTwoDisplay()
                ExitButton()

                val infoList = mutableListOf(
                    globalMode, playerNameOne, globalPlayerOne.toString(), playerNameTwo, globalPlayerTwo.toString())

                if (pause) {
                    GamePause(onDismiss = { stateTwo = false })
                }

                if (resume) {
                    GamePause(onDismiss = { stateTwo = true })
                    resume = false
                    pause = false
                }


                if(restart)
                {
                    initialValue = 0
                    gameStart = false
                    if(game>2&&(globalPlayerOne == 0 || globalPlayerTwo == 0))
                    {
                        Display(onDismiss = { state = true })
                    }
                    if(pause)
                    {
                        GamePause(onDismiss = { stateTwo = true })
                        pause = false
                    }
                    game = 0
                    colorChangerRed.clear()
                    colorChangerBlue.clear()
                    playerOneClicked.clear()
                    playerTwoClicked.clear()
                    score.fill(0)
                    globalPlayerOne = 0
                    globalPlayerTwo = 0
                    restart = false
                    added = false
                }

                if(returnhome)
                {
                    initialValue = 0
                    gameStart = false
                    if(pause)
                    {
                        GamePause(onDismiss = {stateTwo = true})
                    }
                    Display(onDismiss = {state = true})
                    game = 0
                    gameStart = false
                    colorChangerRed.clear()
                    colorChangerBlue.clear()
                    playerOneClicked.clear()
                    playerTwoClicked.clear()
                    score.fill(0)
                    globalPlayerOne = 0
                    globalPlayerTwo = 0
                    Intent(applicationContext, MainActivity::class.java).also {
                        startActivity(it)
                        finish()}
                    returnhome = false
                    added = false
                }


                if (game >= 2 && (globalPlayerOne == 0 || globalPlayerTwo == 0) && !pause)
                {
                    gameOver = true
                    if (globalPlayerOne == 0) {
                        com.example.myapplication.extractor(playerNameTwo.toUpperCase())
                    } else {
                        com.example.myapplication.extractor(playerNameOne.toUpperCase())
                    }
                    if(!added)
                    {
                        primeReciever.add(infoList)
                        loadData = true
                        added = true
                    }
                    Display(onDismiss = { state = false })
                }



            }
        }
    }
}


@Composable
fun GridFour(rows: Int, columns: Int) {
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

                        val clickedValueAuto: MutableState<Int> = remember {
                            mutableIntStateOf(0)
                        }


                        Cell(
                            value = cellValue,
                            changerBlue = colorChangerBlue,
                            changerRed = colorChangerRed,
                            scoree = score,
                            onClick = { clickedValue ->

                                if (game == 0) {
                                    initialValue = clickedValue
                                    colorChangerRed.add(clickedValue)
                                    playerOneClicked.add(clickedValue)
                                    score[clickedValue] = 3
                                }
                                else if (clickedValue in playerOneClicked && score[clickedValue] == 3 && game % 2 == 0) {
                                    playerOneClicked.remove(clickedValue)
                                    colorChangerRed.remove(clickedValue)
                                    score[clickedValue] = 0

                                    val adderOne: MutableList<Int> = colorChanger(
                                        clicked = clickedValue,
                                        column = globalColumn.toInt()
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

                                    for (n in workOne[0]) {
                                        score[count] = n
                                        count += 1
                                    }

                                    count = 0

                                    for (y in workOne[1]) {
                                        playerOneClicked[count] = y
                                        count += 1

                                    }
                                    count = 0
                                    for (z in workOne[2]) {
                                        playerTwoClicked[count] = z
                                        count += 1

                                    }
                                    count = 0
                                    for (u in workOne[3]) {
                                        colorChangerBlue[count] = u
                                        count += 1

                                    }
                                    count = 0
                                    for (v in workOne[4]) {
                                        colorChangerRed[count] = v
                                        count += 1
                                    }

                                }
                                else if(clickedValue in playerOneClicked && game%2 == 0 && score[clickedValue] < 3)
                                {
                                    score[clickedValue] += 1
                                }
                                else {
                                    game -= 1
                                    Log.d("GAME", "Rebound")
                                    Toast.makeText(context, "WRONG TURN", Toast.LENGTH_SHORT).show()
                                }
                                game += 1

                                var extraa = 0

                                while(score.any { it in principalValues })
                                {
                                    while(gridChecker(score))
                                    {
                                        val indeXa  = valueReturner(score,4)

                                        var countya = 0

                                        val adderExa: MutableList<Int> = colorChanger(
                                            clicked = indeXa,
                                            column = globalColumn.toInt()
                                        ).toMutableList()

                                        for (cua in fourAdder(indeXa,adderExa,score))
                                        {
                                            score[countya] = cua
                                            countya+=1
                                        }


                                        if(indeXa in colorChangerRed)
                                        {
                                            for(xua in adderExa)
                                            {
                                                if(xua !in colorChangerRed)
                                                {
                                                    colorChangerRed.add(xua)
                                                    colorChangerBlue.remove(xua)
                                                    playerTwoClicked.remove(xua)
                                                    playerOneClicked.add(xua)
                                                }


                                            }

                                        }
                                        else
                                        {
                                            for(vua in adderExa)
                                            {
                                                if(vua !in colorChangerBlue)
                                                {
                                                    colorChangerBlue.add(vua)
                                                    colorChangerRed.remove(vua)
                                                    playerOneClicked.remove(vua)
                                                    playerTwoClicked.add(vua)
                                                }
                                            }

                                        }

                                        if(adderExa.size == 4)
                                        {
                                            if(indeXa in colorChangerRed)
                                            {
                                                colorChangerRed.remove(indeXa)
                                                playerOneClicked.remove(indeXa)
                                            }
                                            else
                                            {
                                                colorChangerBlue.remove(indeXa)
                                                playerTwoClicked.remove(indeXa)
                                            }
                                        }

                                    }
                                    while(moreThanFour(score))
                                    {
                                        val ta = valueReturnerTwo(score)
                                        val numa = valueReturner(score,ta)


                                        extraa = score[numa] - 4
                                        score[numa] = 4

                                        val indEXo  = valueReturner(score,4)

                                        var countyo = 0

                                        val adderEXo: MutableList<Int> = colorChanger(
                                            clicked = indEXo,
                                            column = globalColumn.toInt()
                                        ).toMutableList()

                                        for (qua in fourAdder(indEXo,adderEXo,score))
                                        {
                                            score[countyo] = qua
                                            countyo+=1
                                        }


                                        if(indEXo in colorChangerRed)
                                        {
                                            for(aua in adderEXo)
                                            {
                                                if(aua !in colorChangerRed)
                                                {
                                                    colorChangerRed.add(aua)
                                                    colorChangerBlue.remove(aua)
                                                    playerTwoClicked.remove(aua)
                                                    playerOneClicked.add(aua)
                                                }

                                            }

                                        }
                                        else
                                        {
                                            for(oua in adderEXo)
                                            {
                                                if(oua !in colorChangerBlue)
                                                {
                                                    colorChangerBlue.add(oua)
                                                    colorChangerRed.remove(oua)
                                                    playerTwoClicked.remove(oua)
                                                    playerTwoClicked.add(oua)
                                                }
                                            }

                                        }

                                        score[indEXo] = extraa

                                    }

                                }

                                Log.d("Red Color","${colorChangerRed.joinToString()}")
                                Log.d("Red Color Clicked","${playerOneClicked.joinToString()}")


                                Log.d("Blue Color","${colorChangerBlue.joinToString()}")
                                Log.d("Blue Color Clicked","${playerTwoClicked.joinToString()}")
                            }






                        )

                        LaunchedEffect(game) {

                            delay(2000L)

                            if (game%2 == 1)
                            {
                                if(game%2 ==1 && game>2)
                                {
                                    Log.d("Hey","Again Me")
                                    if(playerTwoClicked.isNotEmpty())
                                    {
                                        clickedValueAuto.value = compRand(playerTwoClicked)
                                    }

                                }
                                if (game ==1) {
                                    clickedValueAuto.value = rand()
                                    if(clickedValueAuto.value == initialValue)
                                    {
                                        game -= 1
                                    }
                                }
                                if (game == 1 && clickedValueAuto.value !in playerOneClicked) {
                                    colorChangerBlue.add(clickedValueAuto.value)
                                    playerTwoClicked.add(clickedValueAuto.value)
                                    score[clickedValueAuto.value] = 3
                                }
                                else if(clickedValueAuto.value in playerTwoClicked && score[clickedValueAuto.value] == 3  && game%2 == 1)
                                {

                                    playerTwoClicked.remove(clickedValueAuto.value)
                                    colorChangerBlue.remove(clickedValueAuto.value)
                                    score[clickedValueAuto.value] = 0


                                    val adderTwo : MutableList<Int> = colorChanger(
                                        clicked =clickedValueAuto.value,
                                        column = globalColumn.toInt()
                                    ).toMutableList()

                                    val workTwo: List<List<Int>> = workCommon(
                                        clickedValueAuto.value,
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
                                else if (clickedValueAuto.value in playerTwoClicked && game % 2 == 1 && score[clickedValueAuto.value] < 3) {
                                    score[clickedValueAuto.value] += 1
                                }


                                game += 1

                                Log.d("Red Color","${colorChangerRed.joinToString()}")
                                Log.d("Red Color Clicked","${playerOneClicked.joinToString()}")


                                Log.d("Blue Color","${colorChangerBlue.joinToString()}")
                                Log.d("Blue Color Clicked","${playerTwoClicked.joinToString()}")
                            }

                        // Delay for 4 seconds

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


                        globalPlayerOne = scoreDisplayer(score,colorChangerRed)
                        globalPlayerTwo = scoreDisplayer(score,colorChangerBlue)

                    }
                }
            }
        }
    }
}

fun rand(): Int {
    val re = (0..<(row* column)).random()
    return re
}
fun compRand(gugu : List<Int>):Int
{
    val num = gugu.size
    val ze = (0..<num).random()
    return(gugu[ze])
}

