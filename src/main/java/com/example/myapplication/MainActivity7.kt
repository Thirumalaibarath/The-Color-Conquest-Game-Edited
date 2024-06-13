package com.example.myapplication

import android.content.Intent
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import android.graphics.Typeface
import android.os.Bundle
import android.widget.Toast
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


var globalPlayerThree : Int by mutableIntStateOf(0)
var globalPlayerFour : Int by mutableIntStateOf(0)

var globalDivisible : Int by mutableIntStateOf(3)

var colorOne : Color by mutableStateOf(lightRed)
var colorTwo : Color by mutableStateOf(lightBlack)

var reminderOne : Int by mutableIntStateOf(0)
var reminderTwo : Int by mutableIntStateOf(1)
var reminderThree : Int by mutableIntStateOf(2)

var gameTwo : Int by mutableIntStateOf(1)

var inGameOne : Boolean by mutableStateOf(true)
var inGameTwo : Boolean by mutableStateOf(true)
var inGameThree : Boolean by mutableStateOf(true)

var winCount : Int by mutableIntStateOf(1)

val winOrder = mutableStateListOf<Int>().apply {
    repeat(3) { add(0) }
}





class MainActivity7: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            val infoList = mutableListOf(
                        globalMode, playerNameOne, globalPlayerOne.toString(), playerNameTwo, globalPlayerTwo.toString(),playerNameThree, globalPlayerThree.toString(),winOrder[0].toString(),
                winOrder[1].toString(),
                winOrder[2].toString()
                    )



            Box(modifier = Modifier.fillMaxSize())
            {



                GridTwo(globalRow.toInt(), globalColumn.toInt())
                PlayerOneDisplay()
                PlayerTwoDisplay()
                PlayerThreeDisplay()
                PlayerFourDisplay()
                ExitButton()

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
                    winCount = 1
                    winOrder.fill(0)
                    Display(onDismiss = {state = true})
                    if(pause)
                    {
                        GamePause(onDismiss = { stateTwo = true })
                        pause = false
                    }
                    game = 0
                    colorChangerRed.clear()
                    colorChangerBlue.clear()
                    colorChangerGreen.clear()
                    playerOneClicked.clear()
                    playerTwoClicked.clear()
                    playerThreeClicked.clear()
                    score.fill(0)
                    globalPlayerOne = 0
                    globalPlayerTwo = 0
                    globalPlayerThree = 0
                    globalDivisible = 3
                    inGameOne = true
                    inGameTwo = true
                    inGameThree = true
                    reminderOne = 0
                    reminderTwo = 1
                    reminderThree = 2
                    gameTwo = 1
                    restart = false
                }

                if(returnhome)
                {
                    winCount = 1
                    winOrder.fill(0)
                    Display(onDismiss = {state = true})
                    if(pause)
                    {
                        GamePause(onDismiss = {stateTwo = true})
                    }
                    game = 0
                    pause = false
                    colorChangerRed.clear()
                    colorChangerBlue.clear()
                    colorChangerGreen.clear()
                    playerOneClicked.clear()
                    playerTwoClicked.clear()
                    playerThreeClicked.clear()
                    score.fill(0)
                    globalPlayerOne = 0
                    globalPlayerTwo = 0
                    globalPlayerThree = 0
                    globalDivisible = 3
                    inGameOne = true
                    inGameTwo = true
                    inGameThree = true
                    reminderOne = 0
                    reminderTwo = 1
                    reminderThree = 2
                    gameTwo = 1
                    returnhome = false
                    Intent(applicationContext, MainActivity::class.java).also {
                        startActivity(it)
                        finish()}
                }

                if (game > 3 && ((!inGameOne && !inGameTwo) || (!inGameThree&&  !inGameTwo) || (!inGameThree && !inGameOne) ))
                {
                    if (!inGameOne && !inGameTwo) {
                        com.example.myapplication.extractor(playerNameThree.toUpperCase())
                    } else if(!inGameThree && !inGameTwo)
                    {
                        com.example.myapplication.extractor(playerNameOne.toUpperCase())
                    }
                    else if(!inGameThree  && !inGameOne)
                    {
                        com.example.myapplication.extractor(playerNameTwo.toUpperCase())
                    }
                    Display(onDismiss = { state = false })
                    Log.d("added three", "called")
                    primeReciever.add(infoList)
                    loadData = true
                }


            }
        }
    }
}


@Composable
fun GridTwo(rows: Int, columns: Int) {
    val context = LocalContext.current

    Surface (
        modifier = Modifier.fillMaxSize(),
        color = if(globalDivisible == 3) {
            if (game % 3 == 0) {
                lightRed
            } else if (game % 3 == 1) {
                lightBlue
            }
            else {
                yellow
            }
        }
        else
        {
            if(gameTwo % 2==0)
                {
                    colorOne
                }
            else
            {
                colorTwo

            }

        }
        )

    {

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
                        val standard = remember { mutableStateListOf<Int>() }
                        for (d in 0..rows * columns) {
                            standard.add(d)
                        }
                        val principalValues = listOf(4, 5, 6, 7, 8)


                        CellTwo(
                            value = cellValue,
                            changerBlue = colorChangerBlue,
                            changerRed = colorChangerRed,
                            changerGreen = colorChangerGreen,
                            scoree = score,
                            onClick = { clickedValue ->


                                if (game == 0) {
                                    colorChangerRed.add(clickedValue)
                                    playerOneClicked.add(clickedValue)
                                    score[clickedValue] = 3

                                } else if (game == 1 && clickedValue !in playerOneClicked) {

                                    colorChangerBlue.add(clickedValue)
                                    playerTwoClicked.add(clickedValue)
                                    score[clickedValue] = 3
                                } else if (game == 2 && (clickedValue !in playerOneClicked && clickedValue !in playerTwoClicked)) {

                                    colorChangerGreen.add(clickedValue)
                                    playerThreeClicked.add(clickedValue)
                                    score[clickedValue] = 3
                                } else if (clickedValue in playerOneClicked && score[clickedValue] == 3 && game % globalDivisible == reminderOne && inGameOne) {
                                    playerOneClicked.remove(clickedValue)
                                    colorChangerRed.remove(clickedValue)
                                    score[clickedValue] = 0
                                    var count = 0

                                    if(globalDivisible == 3)
                                    {
                                        val adderOne: MutableList<Int> = colorChanger(
                                            clicked = clickedValue,
                                            column = columns
                                        ).toMutableList()


                                        val workOne: List<List<Int>> = workCommonTwo(
                                            clickedValue,
                                            game,
                                            adderOne,
                                            score,
                                            playerOneClicked,
                                            playerTwoClicked,
                                            playerThreeClicked,
                                            colorChangerBlue,
                                            colorChangerRed,
                                            colorChangerGreen
                                        )


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
                                        for (yu in workOne[3]) {
                                            colorChangerGreen[count] = yu
                                            count += 1
                                        }
                                        count = 0
                                        for (u in workOne[4]) {
                                            colorChangerBlue[count] = u
                                            count += 1

                                        }
                                        count = 0
                                        for (v in workOne[5]) {
                                            colorChangerRed[count] = v
                                            count += 1
                                        }
                                        count = 0
                                        for (vuu in workOne[6]) {
                                            colorChangerGreen[count] = vuu
                                            count += 1
                                        }
                                    }
                                    else
                                    {

                                        val adderOneOne: MutableList<Int> = colorChanger(
                                            clicked = clickedValue,
                                            column = globalColumn.toInt()
                                        ).toMutableList()


                                        val workOneOne: List<List<Int>> = workCommon(
                                            clickedValue,
                                            gameTwo,
                                            adderOneOne,
                                            score,
                                            if(colorOne == lightRed) playerOneClicked else if(colorOne == lightBlue) playerTwoClicked else playerThreeClicked,
                                            if(colorTwo == lightRed) playerOneClicked else if(colorTwo == lightBlue) playerTwoClicked else playerThreeClicked,
                                            if(colorTwo == lightRed) colorChangerRed else if(colorTwo == lightBlue) colorChangerBlue else colorChangerGreen,
                                            if(colorOne == lightRed) colorChangerRed else if(colorOne == lightBlue) colorChangerBlue else colorChangerGreen,

                                        )
                                        count = 0

                                        for (nx in workOneOne[0]) {
                                            score[count] = nx
                                            count += 1
                                        }

                                        count = 0

                                        for (yx in workOneOne[1]) {
                                            if(colorOne == lightRed) playerOneClicked[count] else if(colorOne == lightBlue) playerTwoClicked[count] else playerThreeClicked[count] = yx
                                            count += 1
                                        }
                                        count = 0
                                        for (zx in workOneOne[2]) {
                                            if(colorTwo == lightRed) playerOneClicked[count] else if(colorTwo == lightBlue) playerTwoClicked[count] else playerThreeClicked[count]  = zx
                                            count += 1

                                        }
                                        count = 0
                                        for (yux in workOneOne[3]) {
                                            if(colorTwo == lightRed) colorChangerRed[count] else if(colorTwo == lightBlue) colorChangerBlue[count] else colorChangerGreen[count]  = yux
                                            count += 1
                                        }
                                        count = 0
                                        for (ux in workOneOne[4] ) {
                                            if(colorOne == lightRed) colorChangerRed[count] else if(colorOne == lightBlue) colorChangerBlue[count] else colorChangerGreen[count]= ux
                                            count += 1

                                        }
                                        count = 0

                                    }



                                } else if (clickedValue in playerTwoClicked && score[clickedValue] == 3 && game % globalDivisible == reminderTwo && inGameTwo) {
                                    playerTwoClicked.remove(clickedValue)
                                    colorChangerBlue.remove(clickedValue)
                                    score[clickedValue] = 0
                                    var countTwo = 0

                                    if(globalDivisible == 3)
                                    {
                                        val adderTwo: MutableList<Int> = colorChanger(
                                            clicked = clickedValue,
                                            column = globalColumn.toInt()
                                        ).toMutableList()

                                        val workTwo: List<List<Int>> = workCommonTwo(
                                            clickedValue,
                                            game,
                                            adderTwo,
                                            score,
                                            playerOneClicked,
                                            playerTwoClicked,
                                            playerThreeClicked,
                                            colorChangerBlue,
                                            colorChangerRed,
                                            colorChangerGreen
                                        )


                                        for (n in workTwo[0]) {
                                            score[countTwo] = n
                                            countTwo += 1
                                        }

                                        countTwo = 0

                                        for (y in workTwo[1]) {
                                            playerOneClicked[countTwo] = y
                                            countTwo += 1

                                        }
                                        countTwo = 0
                                        for (z in workTwo[2]) {
                                            playerTwoClicked[countTwo] = z
                                            countTwo += 1

                                        }
                                        countTwo = 0
                                        for (zu in workTwo[3]) {
                                            playerThreeClicked[countTwo] = zu
                                            countTwo += 1

                                        }
                                        countTwo = 0
                                        for (u in workTwo[4]) {
                                            colorChangerBlue[countTwo] = u
                                            countTwo += 1

                                        }
                                        countTwo = 0
                                        for (v in workTwo[5]) {
                                            colorChangerRed[countTwo] = v
                                            countTwo += 1

                                        }
                                        countTwo = 0
                                        for (vq in workTwo[6]) {
                                            colorChangerGreen[countTwo] = vq
                                            countTwo += 1

                                        }
                                    }
                                    else
                                    {
                                        val adderOneTwo: MutableList<Int> = colorChanger(
                                            clicked = clickedValue,
                                            column = globalColumn.toInt()
                                        ).toMutableList()


                                        val workOneTwo: List<List<Int>> = workCommon(
                                            clickedValue,
                                            gameTwo,
                                            adderOneTwo,
                                            score,
                                            if(colorOne == lightRed) playerOneClicked else if(colorOne == lightBlue) playerTwoClicked else playerThreeClicked,
                                            if(colorTwo == lightRed) playerOneClicked else if(colorTwo == lightBlue) playerTwoClicked else playerThreeClicked,
                                            if(colorTwo == lightRed) colorChangerRed else if(colorTwo == lightBlue) colorChangerBlue else colorChangerGreen,
                                            if(colorOne == lightRed) colorChangerRed else if(colorOne == lightBlue) colorChangerBlue else colorChangerGreen
                                        )
                                        countTwo = 0

                                        for (nxy in workOneTwo[0]) {
                                            score[countTwo] = nxy
                                            countTwo += 1
                                        }

                                        countTwo = 0

                                        for (yxy in workOneTwo[1]) {
                                            if(colorOne == lightRed) playerOneClicked[countTwo] else if(colorOne == lightBlue) playerTwoClicked[countTwo] else playerThreeClicked[countTwo] = yxy
                                            countTwo += 1
                                        }
                                        countTwo = 0

                                        for (zxy in workOneTwo[2]) {
                                            if(colorTwo == lightRed) playerOneClicked[countTwo] else if(colorTwo == lightBlue) playerTwoClicked[countTwo] else playerThreeClicked[countTwo]  = zxy
                                            countTwo += 1

                                        }
                                        countTwo = 0
                                        for (yux in workOneTwo[3]) {
                                            if(colorTwo == lightRed) colorChangerRed[countTwo] else if(colorTwo == lightBlue) colorChangerBlue[countTwo] else colorChangerGreen[countTwo] = yux
                                            countTwo += 1
                                        }
                                        countTwo = 0
                                        for (uxx in workOneTwo[4]) {
                                            if(colorOne == lightRed) colorChangerRed[countTwo] else if(colorOne == lightBlue) colorChangerBlue[countTwo] else colorChangerGreen[countTwo]   = uxx
                                            countTwo += 1

                                        }
                                        countTwo = 0


                                    }


                                } else if (clickedValue in playerThreeClicked && score[clickedValue] == 3 && game % globalDivisible == reminderThree && inGameThree) {
                                    playerThreeClicked.remove(clickedValue)
                                    colorChangerGreen.remove(clickedValue)
                                    score[clickedValue] = 0

                                    var countThree = 0

                                   if(globalDivisible == 3)
                                   {
                                       val adderThree: MutableList<Int> = colorChanger(
                                           clicked = clickedValue,
                                           column = globalColumn.toInt()
                                       ).toMutableList()


                                       val workThree: List<List<Int>> = workCommonTwo(
                                           clickedValue,
                                           game,
                                           adderThree,
                                           score,
                                           playerOneClicked,
                                           playerTwoClicked,
                                           playerThreeClicked,
                                           colorChangerBlue,
                                           colorChangerRed,
                                           colorChangerGreen
                                       )


                                       for (ne in workThree[0]) {
                                           score[countThree] = ne
                                           countThree += 1
                                       }

                                       countThree = 0

                                       for (ye in workThree[1]) {
                                           playerOneClicked[countThree] = ye
                                           countThree += 1

                                       }
                                       countThree = 0
                                       for (ze in workThree[2]) {
                                           playerTwoClicked[countThree] = ze
                                           countThree += 1

                                       }
                                       countThree = 0
                                       for (zee in workThree[3]) {
                                           playerThreeClicked[countThree] = zee
                                           countThree += 1

                                       }
                                       countThree = 0
                                       for (ue in workThree[4]) {
                                           colorChangerBlue[countThree] = ue
                                           countThree += 1

                                       }
                                       countThree = 0
                                       for (ve in workThree[5]) {
                                           colorChangerRed[countThree] = ve
                                           countThree+= 1
                                       }
                                       countThree= 0
                                       for (vrre in workThree[6]) {
                                           colorChangerGreen[countThree] = vrre
                                           countThree += 1
                                       }
                                   }
                                    else
                                   {
                                       val adderThreeOne: MutableList<Int> = colorChanger(
                                           clicked = clickedValue,
                                           column = globalColumn.toInt()
                                       ).toMutableList()


                                       val workThreeOne: List<List<Int>> = workCommon(
                                           clickedValue,
                                           gameTwo,
                                           adderThreeOne,
                                           score,
                                           if(colorOne == lightRed) playerOneClicked else if(colorOne == lightBlue) playerTwoClicked else playerThreeClicked,
                                           if(colorTwo == lightRed) playerOneClicked else if(colorTwo == lightBlue) playerTwoClicked else playerThreeClicked,
                                           if(colorTwo == lightRed) colorChangerRed else if(colorTwo == lightBlue) colorChangerBlue else colorChangerGreen,
                                           if(colorOne == lightRed) colorChangerRed else if(colorOne == lightBlue) colorChangerBlue else colorChangerGreen,

                                       )
                                       countThree = 0

                                       for (nxe in workThreeOne[0]) {
                                           score[countThree] = nxe
                                           countThree += 1
                                       }

                                       countThree = 0

                                       for (yxe in workThreeOne[1]) {
                                           if(colorOne == lightRed) playerOneClicked[countThree] else if(colorOne == lightBlue) playerTwoClicked[countThree] else playerThreeClicked[countThree] = yxe
                                           countThree += 1
                                       }
                                       countThree = 0
                                       for (zxe in workThreeOne[2]) {
                                           if(colorTwo == lightRed) playerOneClicked[countThree] else if(colorTwo == lightBlue) playerTwoClicked[countThree] else playerThreeClicked[countThree]  = zxe
                                           countThree += 1

                                       }
                                       countThree = 0
                                       for (yuxe in workThreeOne[3]) {
                                           if(colorTwo == lightRed) colorChangerRed[countThree] else if(colorTwo == lightBlue) colorChangerBlue[countThree] else colorChangerGreen[countThree] = yuxe
                                           countThree += 1
                                       }
                                       countThree = 0
                                       for (uxe in workThreeOne[4]) {
                                           if(colorOne == lightRed) colorChangerRed[countThree] else if(colorOne == lightBlue) colorChangerBlue[countThree] else colorChangerGreen[countThree]   = uxe
                                           countThree += 1
                                       }
                                       countThree = 0
                                   }

                                } else if (clickedValue in playerOneClicked && game % globalDivisible == reminderOne && score[clickedValue] < 3 && inGameOne) {
                                    score[clickedValue] += 1
                                } else if (clickedValue in playerTwoClicked && game % globalDivisible == reminderTwo && score[clickedValue] < 3 && inGameTwo) {
                                    score[clickedValue] += 1
                                } else if (clickedValue in playerThreeClicked && game % globalDivisible == reminderThree && score[clickedValue] < 3 && inGameThree) {
                                    score[clickedValue] += 1
                                } else {
                                    if(globalDivisible == 2)
                                    {
                                        gameTwo-= 1
                                        Toast.makeText(context, "WRONG TURN", Toast.LENGTH_SHORT).show()
                                    }
                                    game -= 1
                                    Toast.makeText(context, "WRONG TURN", Toast.LENGTH_SHORT).show()
                                    Log.d("GAME", "Rebound")
                                }
                                var extra = 0

                                while (score.any { it in principalValues }) {
                                    while (gridChecker(score)) {
                                        val indeX = valueReturner(score, 4)

                                        var county = 0

                                        val adderEx: MutableList<Int> = colorChanger(
                                            clicked = indeX,
                                            column = globalColumn.toInt()
                                        ).toMutableList()

                                        for (cu in fourAdder(indeX, adderEx, score)) {
                                            score[county] = cu
                                            county += 1
                                        }


                                        if (indeX in colorChangerRed) {
                                            for (xu in adderEx) {
                                                if (xu !in colorChangerRed) {
                                                    colorChangerRed.add(xu)
                                                    colorChangerBlue.remove(xu)
                                                    colorChangerGreen.remove(xu)
                                                    playerTwoClicked.remove(xu)
                                                    playerThreeClicked.remove(xu)
                                                    playerOneClicked.add(xu)
                                                }


                                            }

                                        } else if (indeX in colorChangerBlue) {
                                            for (vu in adderEx) {
                                                if (vu !in colorChangerBlue) {
                                                    colorChangerBlue.add(vu)
                                                    colorChangerRed.remove(vu)
                                                    colorChangerGreen.remove(vu)
                                                    playerOneClicked.remove(vu)
                                                    playerTwoClicked.add(vu)
                                                    playerThreeClicked.remove(vu)
                                                }
                                            }

                                        } else {
                                            for (vue in adderEx) {
                                                if (vue !in colorChangerGreen) {
                                                    colorChangerGreen.add(vue)
                                                    colorChangerRed.remove(vue)
                                                    colorChangerBlue.remove(vue)
                                                    playerThreeClicked.add(vue)
                                                    playerOneClicked.remove(vue)
                                                    playerTwoClicked.remove(vue)
                                                }
                                            }

                                        }

                                        if (adderEx.size == 4) {
                                            if (indeX in colorChangerRed) {
                                                colorChangerRed.remove(indeX)
                                                playerOneClicked.remove(indeX)
                                            } else if (indeX in colorChangerBlue) {
                                                colorChangerBlue.remove(indeX)
                                                playerTwoClicked.remove(indeX)
                                            } else {
                                                colorChangerGreen.remove(indeX)
                                                playerThreeClicked.remove(indeX)
                                            }
                                        }

                                    }
                                    while (moreThanFour(score)) {
                                        val t = valueReturnerTwo(score)
                                        val num = valueReturner(score, t)

                                        var who = 0
                                        if (num in colorChangerRed) {
                                            who = 0
                                        } else if (num in colorChangerBlue) {
                                            who = 1
                                        } else {
                                            who = 2
                                        }


                                        extra = score[num] - 4
                                        score[num] = 4

                                        val indEX = valueReturner(score, 4)

                                        var county = 0

                                        val adderEX: MutableList<Int> = colorChanger(
                                            clicked = indEX,
                                            column = globalColumn.toInt()
                                        ).toMutableList()

                                        for (qu in fourAdder(indEX, adderEX, score)) {
                                            score[county] = qu
                                            county += 1
                                        }


                                        if (indEX in colorChangerRed) {
                                            for (au in adderEX) {
                                                if (au !in colorChangerRed) {
                                                    colorChangerRed.add(au)
                                                    colorChangerBlue.remove(au)
                                                    colorChangerGreen.remove(au)
                                                    playerTwoClicked.remove(au)
                                                    playerThreeClicked.remove(au)
                                                    playerOneClicked.add(au)
                                                }

                                            }

                                        } else if (indEX in colorChangerBlue) {
                                            for (ou in adderEX) {
                                                if (ou !in colorChangerBlue) {
                                                    colorChangerBlue.add(ou)
                                                    colorChangerRed.remove(ou)
                                                    colorChangerGreen.remove(ou)
                                                    playerOneClicked.remove(ou)
                                                    playerTwoClicked.add(ou)
                                                    playerThreeClicked.remove(ou)
                                                }
                                            }

                                        } else {
                                            for (oute in adderEX) {
                                                if (oute !in colorChangerGreen) {
                                                    colorChangerGreen.add(oute)
                                                    colorChangerRed.remove(oute)
                                                    colorChangerBlue.remove(oute)
                                                    playerThreeClicked.add(oute)
                                                    playerOneClicked.remove(oute)
                                                    playerTwoClicked.remove(oute)
                                                }
                                            }

                                        }

                                        score[indEX] = extra

                                    }

                                }

                                if(inGameOne)
                                {
                                    globalPlayerOne = scoreDisplayer(sCORES = score, colour = colorChangerRed)

                                }

                                if(inGameTwo)
                                {
                                    globalPlayerTwo = scoreDisplayer(sCORES = score, colour = colorChangerBlue)


                                }
                                if(inGameThree)
                                {
                                    globalPlayerThree = scoreDisplayer(sCORES = score, colour = colorChangerGreen)

                                }




                                    if(game >  2 )
                                    {
                                        if (globalPlayerOne == 0) {
                                            globalDivisible = 2
                                            inGameOne = false


                                            if(winCount == 1)
                                            {
                                                if(game%3 == 1)
                                                {
                                                    if((game+1)%2 == 0)
                                                    {
                                                        reminderTwo = 1
                                                        reminderThree = 0

                                                    }
                                                    else
                                                    {
                                                        reminderTwo = 0
                                                        reminderThree = 1
                                                    }
                                                    colorOne = yellow
                                                    colorTwo = lightBlue
                                                }
                                                else if(game%3 == 2)
                                                {
                                                    if((game+1)%2 == 0)
                                                    {
                                                        reminderTwo = 0
                                                        reminderThree = 1
                                                    }
                                                    else
                                                    {
                                                        reminderTwo = 1
                                                        reminderThree = 0
                                                    }

                                                    colorOne = lightBlue
                                                    colorTwo = yellow
                                                }
                                            }


                                            globalPlayerOne = -1
                                            winOrder[0] = winCount
                                            winCount++

                                        } else if (globalPlayerTwo == 0)
                                        {
                                            globalDivisible = 2
                                            inGameTwo = false

                                            if(winCount == 1)
                                            {
                                                if (game % 3 == 0) {
                                                    if((game+1) %2 == 0)
                                                    {
                                                        reminderThree = 0
                                                        reminderOne =  1
                                                    }
                                                    else{
                                                        reminderThree = 1
                                                        reminderOne = 0
                                                    }

                                                    colorOne = yellow
                                                    colorTwo = lightRed
                                                }
                                                else if(game%3 == 2){
                                                    if((game+1)%2 == 0)
                                                    {
                                                        reminderOne = 0
                                                        reminderThree = 1
                                                    }
                                                    else
                                                    {
                                                        reminderOne = 1
                                                        reminderThree = 0
                                                    }

                                                    colorOne = lightRed
                                                    colorTwo = yellow

                                                }
                                            }

                                            globalPlayerTwo = -1
                                            winOrder[1] = winCount
                                            winCount ++

                                        } else if (globalPlayerThree == 0)
                                        {
                                            globalDivisible = 2
                                            inGameThree = false


                                            if(winCount == 1)
                                            {
                                                if (game % 3 == 0) {
                                                    if((game+1) %2 == 0)
                                                    {
                                                        reminderOne = 1
                                                        reminderTwo = 0
                                                    }
                                                    else{
                                                        reminderOne = 0
                                                        reminderTwo = 1
                                                    }

                                                    colorOne = lightBlue
                                                    colorTwo = lightRed
                                                } else if(game%3 ==1 ) {
                                                    if((game+1)%2 == 0)
                                                    {
                                                        reminderOne = 0
                                                        reminderTwo = 1
                                                    }
                                                    else
                                                    {
                                                        reminderOne = 1
                                                        reminderTwo = 0
                                                    }

                                                    colorOne = lightRed
                                                    colorTwo = lightBlue
                                                }
                                            }


                                            globalPlayerThree = -1
                                            winOrder[2] = winCount
                                            winCount ++
                                        }

                                    }



                                game += 1
                                if(globalDivisible == 2)
                                {
                                    gameTwo+= 1
                                }


                                Log.d(
                                    "Color - Blue - clicked",
                                    "${playerTwoClicked.joinToString()}"
                                )
                                Log.d("Color - Blue ", "${colorChangerBlue.joinToString()}")
                                Log.d(
                                    "Color - Green - clicked",
                                    "${playerThreeClicked.joinToString()}"
                                )
                                Log.d("Color - Green ", "${colorChangerGreen.joinToString()}")
                                Log.d("Color - Red - clicked", "${playerOneClicked.joinToString()}")
                                Log.d("Color - Red", "${colorChangerRed.joinToString()}")
                                Log.d("Game", "$game")
                                Log.d("Divisible","$globalDivisible")
                                Log.d("Reminder -1 ","$reminderOne")
                                Log.d("Reminder -2 ","$reminderTwo")
                                Log.d("Reminder -3 ","$reminderThree")
                                if(colorOne == lightRed)
                                {
                                    Log.d("activeColor - 1 ","red")
                                }
                                else if(colorOne == lightBlue)
                                {
                                    Log.d("activeColor - 1 ","blue")
                                }
                                else
                                {
                                    Log.d("activeColor - 1 ","yellow")
                                }
                                if(colorTwo == lightBlue)
                                {
                                    Log.d("activeColor - 2 ","blue")
                                }
                                else if(colorTwo == lightRed)
                                {
                                    Log.d("activeColor - 2 ","red")
                                }
                                else
                                {
                                    Log.d("activeColor - 2 ","yellow")
                                }
                                Log.d("game ","$game")



                            }


                        )
                    }
                }
            }
        }
    }
}


fun workCommonTwo(
    press: Int,
    game: Int,
    addOn: MutableList<Int>,
    scores: MutableList<Int>,
    playerOne: MutableList<Int>,
    playerTwo: MutableList<Int>,
    playerThree: MutableList<Int>,
    blueColor: MutableList<Int>,
    redColor: MutableList<Int>,
    greenColor: MutableList<Int>
): List<List<Int>> {

    val activeOne: MutableList<Int> = if (game % 3 == 0) redColor else if (game % 3 == 1) blueColor else greenColor
    val activeTwo: MutableList<Int> = if (game % 3 == 0) blueColor else if (game % 3 == 1) greenColor else redColor
    val activeThree: MutableList<Int> = if (game % 3 == 0) greenColor else if (game % 3 == 1) redColor else blueColor


    val localOne: MutableList<Int> = if (game % 3 == 0) playerOne else if (game % 3 == 1) playerTwo else playerThree
    val localTwo: MutableList<Int> = if (game % 3 == 0) playerTwo else if (game % 3 == 1) playerThree else playerOne
    val localThree: MutableList<Int> = if (game % 3 == 0) playerThree else if (game % 3 == 1) playerOne else playerTwo



    val sizee = addOn.size


    val commonOne: List<Int> = addOn.filter { it in activeTwo }
    val commonTwo: List<Int> = addOn.filter{ it in activeThree  }

    if (commonOne.isEmpty() && commonTwo.isEmpty())
    {
        for (j in addOn)
        {
            scores[j] = 1+scores[j]
            if(j !in localOne)
            {
                localOne.add(j)
                activeOne.add(j)
            }
        }

        if(sizee<=3)
        {
            activeOne.add(press)
            scores[press] = 4 - sizee
            localOne.add(press)
        }

    } else {

        for (k in commonOne) {
            activeTwo.remove(k)
            localTwo.remove(k)
            scores[k] = 1 + scores[k]
            localOne.add(k)
            activeOne.add(k)
        }
        for(ui in commonTwo)
        {
            activeThree.remove(ui)
            localThree.remove(ui)
            scores[ui] = 1 + scores[ui]
            localOne.add(ui)
            activeOne.add(ui)

        }

        if(sizee<=3)
        {
            activeOne.add(press)
            scores[press] = 4 - sizee
            localOne.add(press)
        }

        addOn.removeAll(commonOne + commonTwo)


        for (l in addOn) {
            scores[l] = scores[l] + 1
            if(l !in activeOne)
            {
                localOne.add(l)
                activeOne.add(l)
            }

        }

    }

    val fire: List<List<Int>> = listOf(scores, playerOne, playerTwo, playerThree,blueColor, redColor,greenColor)
    return fire
}


@Composable
fun CellTwo(
    value: Int,
    changerBlue: List<Int>,
    changerRed: List<Int>,
    changerGreen: List<Int>,
    scoree: List<Int>,
    onClick:  (Int) -> Unit
) {
    val color = if (changerBlue.contains(value)) {
        lightBlue
    } else if (changerRed.contains(value)) {
        lightRed
    }
    else if(changerGreen.contains(value)){
        yellow
    }
    else {
        cellColor
    }
    val size = if(globalColumn.toInt() == 3)
    {
        if(globalRow.toInt() <4)  90 else 75
    }
    else if(globalColumn.toInt() == 4)
    {
        if(globalRow.toInt() <4)  80 else 65
    }
    else if(globalColumn.toInt() == 5)
    {
        if(globalRow.toInt() <4)  70 else 55
    }
    else if(globalColumn.toInt() == 6)
    {
        if(globalRow.toInt() <4)  60 else 55
    }
    else if(globalColumn.toInt() == 7)
    {
        50
    }
    else
    {
        50
    }
    val word = scoree[value]
    BoxWithText(
        modifier = Modifier
            .size(size.dp)
            .padding(4.dp)
            .clickable { onClick(value) },
        color = color,
        word = word
    )
}


@Composable
fun PlayerTwoDisplay()
{
    Box(modifier = Modifier
        .width(110.dp)
        .height(220.dp)
        .drawBehind {
            val u = Path().apply {
                moveTo(0f,200f)
                lineTo(200f,200f)
                lineTo(200f,400f)
                lineTo(0f,400f)
                moveTo(200f,300f)
                val rr = Rect(100f,200f,300f,400f)
                arcTo(
                    rect = rr,
                    startAngleDegrees = 90f,
                    sweepAngleDegrees = -180f,
                    forceMoveTo = false

                )
                close()
            }
            val y = Path().apply{
                moveTo(0f,220f)
                lineTo(200f,220f)
                lineTo(200f,380f)
                lineTo(0f,380f)
                moveTo(200f,300f)
                val rrr = Rect(120f,220f,280f,380f)
                arcTo(
                    rect = rrr,
                    startAngleDegrees = 90f,
                    sweepAngleDegrees = -180f,
                    forceMoveTo = false
                )
            }
            drawPath(
                path = u,
                color = Color.Black
            )
            drawPath(
                path = y,
                color = lightBlack
            )
        },
        contentAlignment = Alignment.Center)
    {
        Text(text= if(globalPlayerTwo != -1) "$globalPlayerTwo" else "X",fontSize = 40.sp, fontWeight = FontWeight.Bold,color = lightBlue, modifier = Modifier.rotate(180f))
    }
}

@Composable
fun PlayerOneDisplay()
{
    Box(
        modifier = Modifier
            .width(1000.dp)
            .height(1000.dp)
            .drawBehind {
                val fuf = Path().apply {
                    moveTo(1100f,1700f)
                    lineTo(900f,1700f)
                    lineTo(900f,1900f)
                    lineTo(1100f,1900f)

                    moveTo(900f,1800f)
                    val tyu = Rect(800f,1700f,1000f,1900f)
                    arcTo(
                        rect = tyu,
                        startAngleDegrees = 90f,
                        sweepAngleDegrees = 180f,
                        forceMoveTo = false
                    )
                }
                val fufu = Path().apply {
                    moveTo(1100f,1720f)
                    lineTo(900f,1720f)
                    lineTo(900f,1880f)
                    lineTo(1100f,1880f)
                    moveTo(900f,1900f)
                    val tyuu = Rect(820f,1720f,980f,1880f)
                    arcTo(
                        rect = tyuu,
                        startAngleDegrees = 90f,
                        sweepAngleDegrees = 180f,
                        forceMoveTo = false
                    )
                }


                drawPath(
                    path = fuf,
                    color = Color.Black
                )
                drawPath(
                    path = fufu,
                    color = lightBlack
                )
                val textPaint = Paint().asFrameworkPaint().apply {
                    isAntiAlias = true
                    textSize = 40.sp.toPx()
                    color = lightRed.toArgb()
                    textAlign = android.graphics.Paint.Align.LEFT
                    typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                }

                drawContext.canvas.nativeCanvas.drawText(
                    if(globalPlayerOne != -1) "$globalPlayerOne" else "X",
                    900f, // x-coordinate of the text
                    1840f, // y-coordinate of the text
                    textPaint
                )
            }
        ,contentAlignment = Alignment.Center
    )
    {

    }
}

@Composable
fun PlayerFourDisplay()
{
    Box(modifier = Modifier
        .fillMaxSize()
        .drawBehind {
            val u = Path().apply {
                moveTo(1100f,200f)
                lineTo(900f,200f)
                lineTo(900f,400f)
                lineTo(1100f,400f)
                moveTo(900f,300f)
                val rr = Rect(800f,200f,1000f,400f)
                arcTo(
                    rect = rr,
                    startAngleDegrees = -90f,
                    sweepAngleDegrees = -180f,
                    forceMoveTo = false

                )
                close()
            }
            val y = Path().apply{
                moveTo(1100f,220f)
                lineTo(900f,220f)
                lineTo(900f,380f)
                lineTo(1100f,380f)
                moveTo(900f,300f)
                val rrr = Rect(820f,220f,980f,380f)
                arcTo(
                    rect = rrr,
                    startAngleDegrees = -90f,
                    sweepAngleDegrees = -180f,
                    forceMoveTo = false
                )
            }
            drawPath(
                path = u,
                color = Color.Black
            )
            drawPath(
                path = y,
                color =lightBlack
            )
            val textPaintTwo = Paint().asFrameworkPaint().apply {
                isAntiAlias = true
                textSize = 40.sp.toPx()
                color = lightGreen.toArgb()
                textAlign = android.graphics.Paint.Align.LEFT
                typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            }

            drawContext.canvas.nativeCanvas.drawText(
                if(globalPlayerFour != -1) "$globalPlayerFour" else "X",
                930f, // x-coordinate of the text
                340f, // y-coordinate of the text
                textPaintTwo
            )
        },
        contentAlignment = Alignment.Center)
    {

    }
}

@Composable
fun PlayerThreeDisplay()
{
    Box(
        modifier = Modifier
            .fillMaxSize()
            .drawBehind {
                val fuf = Path().apply {
                    moveTo(0f,1700f)
                    lineTo(200f,1700f)
                    lineTo(200f,1900f)
                    lineTo(0f,1900f)

                    moveTo(200f,1800f)
                    val tyu = Rect(100f,1700f,300f,1900f)
                    arcTo(
                        rect = tyu,
                        startAngleDegrees = -90f,
                        sweepAngleDegrees = 180f,
                        forceMoveTo = false
                    )
                }
                val fufu = Path().apply {
                    moveTo(0f,1720f)
                    lineTo(200f,1720f)
                    lineTo(200f,1880f)
                    lineTo(0f,1880f)
                    moveTo(200f,1800f)
                    val tyuu = Rect(120f,1720f,280f,1880f)
                    arcTo(
                        rect = tyuu,
                        startAngleDegrees = -90f,
                        sweepAngleDegrees = 180f,
                        forceMoveTo = false
                    )
                }


                drawPath(
                    path = fuf,
                    color = Color.Black
                )
                drawPath(
                    path = fufu,
                    color = lightBlack
                )
                val textPaint = Paint().asFrameworkPaint().apply {
                    isAntiAlias = true
                    textSize = 40.sp.toPx()
                    color = yellow.toArgb()
                    textAlign = android.graphics.Paint.Align.LEFT
                    typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                }

                drawContext.canvas.nativeCanvas.drawText(
                    if(globalPlayerThree != -1) "$globalPlayerThree" else "X",
                    100f, // x-coordinate of the text
                    1840f, // y-coordinate of the text
                    textPaint
                )
            }
        ,contentAlignment = Alignment.Center
    )
    {

    }
}





