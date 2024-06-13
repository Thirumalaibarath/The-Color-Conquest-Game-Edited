package com.example.myapplication

import android.content.Intent
import android.content.Context
import android.graphics.Typeface
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import com.example.myapplication.Display
import androidx.compose.foundation.layout.Arrangement
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
import android.widget.Toast
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

var returnhome: Boolean by mutableStateOf(false)
var state = false
var stateTwo = false


var restart : Boolean by mutableStateOf(false)

val playerOneClicked = mutableStateListOf<Int>()
val playerTwoClicked = mutableStateListOf<Int>()
val playerThreeClicked = mutableStateListOf<Int>()
val colorChangerBlue = mutableStateListOf<Int>()
val colorChangerRed = mutableStateListOf<Int>()
val colorChangerGreen = mutableStateListOf<Int>()

var starCounterOne : Int by mutableStateOf(0)
var starCounterTwo : Int by mutableStateOf(0)

var loadData : Boolean by mutableStateOf(false)


val score = mutableStateListOf<Int>().apply {
    repeat(7 * 7) { add(0) }
}

val starAdderOne = mutableStateListOf<Boolean>().apply {
    repeat(5) { add(false) }
}
val starAdderTwo = mutableStateListOf<Boolean>().apply {
    repeat(5) { add(false) }
}


var game by mutableIntStateOf(0)
var pause by mutableStateOf(false)
var resume by mutableStateOf(false)
var restartHero by mutableStateOf(false)


var added by mutableStateOf(false)


class MainActivity6 : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val gameClick : MediaPlayer = MediaPlayer.create(this, R.raw.gameclick)
            gameClick.start()

            if (pause) {
                GamePause(onDismiss = { stateTwo = false })
            }

            if (resume) {
                GamePause(onDismiss = { stateTwo = true })
                resume = false
                pause = false
            }

            if (restart) {
                added = false
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
                heroTwoPoint = 0
                heroOnePoint = 0
                if (globalSubMode == "BEST OF 3") {
                    heroCount = 3
                } else if (globalSubMode == "BEST OF 5") {
                    heroCount = 5
                }
                starCounterOne = 0
                starCounterTwo = 0
                starAdderOne.fill(false)
                starAdderTwo.fill(false)
                colorChangerRed.clear()
                colorChangerBlue.clear()
                playerOneClicked.clear()
                playerTwoClicked.clear()
                score.fill(0)
                globalPlayerOne = 0
                globalPlayerTwo = 0
                restart = false
            }

            if (restartHero)
            {
                game = 0
                colorChangerRed.clear()
                colorChangerBlue.clear()
                playerOneClicked.clear()
                playerTwoClicked.clear()
                score.fill(0)
                globalPlayerOne = 0
                globalPlayerTwo = 0
                restartHero = false
                Log.d("hey","im open hero")
            }

            if(returnhome)
            {
                added = false
                if(heroCount == 0)
                {
                    heroTwoPoint = 0
                    heroOnePoint = 0
                    if(globalSubMode == "BEST OF 3")
                    {
                        heroCount = 3
                    }
                    else if(globalSubMode == "BEST OF 5")
                    {
                        heroCount = 5
                    }
                    starCounterOne = 0
                    starCounterTwo = 0
                    starAdderOne.fill(false)
                    starAdderTwo.fill(false)
                }
                pause = false
                returnhome = false
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
                Intent(applicationContext, MainActivity::class.java).also {
                    startActivity(it)
                    finish()}
            }
            val infoList =
                if(!heroMode)
                {
                    mutableListOf(
                        globalMode, playerNameOne, globalPlayerOne.toString(), playerNameTwo, globalPlayerTwo.toString()
                    )
                }

            else{
                    mutableListOf(
                        globalMode, playerNameOne, heroOnePoint.toString(), playerNameTwo, heroTwoPoint.toString(),
                        globalSubMode
                    )
                }


            if (game >= 2 && (globalPlayerOne == 0 || globalPlayerTwo == 0) && !heroMode)
            {
                if (globalPlayerOne == 0) {
                    com.example.myapplication.extractor(playerNameTwo.toUpperCase())
                } else {
                    com.example.myapplication.extractor(playerNameOne.toUpperCase())
                }
                primeReciever.add(infoList)
                loadData = true
                Display(onDismiss = { state = false })
            }


            Log.d("value","$heroCount")

            if(heroCount == 0 && heroMode )
            {
                restartHero = false
                if (heroOnePoint > heroTwoPoint) {
                    com.example.myapplication.extractor(playerNameOne.toUpperCase())
                } else {
                    com.example.myapplication.extractor(playerNameTwo.toUpperCase())
                }
                Display(onDismiss = { state = false })
                if(!added)
                {
                    primeReciever.add(infoList)
                    loadData = true
                    added = true
                }
                Log.d("hey","im open")
            }

            if(game >= 2 && (globalPlayerOne == 0 || globalPlayerTwo == 0) && heroMode && heroCount > 0)
            {
                restartHero = true
                if(globalPlayerOne > globalPlayerTwo)
                {
                    starAdderOne[starCounterOne] = true
                    heroOnePoint ++
                    starCounterOne ++
                }
                else{
                    starAdderTwo[starCounterTwo] = true
                    heroTwoPoint ++
                    starCounterTwo ++
                }
                heroCount -= 1

            }

            Box(modifier = Modifier.fillMaxSize()) {
                GridOne(globalRow.toInt(), globalColumn.toInt())
                PlayerNameTwoDisplay()
                PlayerNameOneDisplay()
                PlayerOneDisplay()
                PlayerTwoDisplay()
                ExitButton()
                StarOne(starAdderTwo)
                StarTwo(starAdderOne)


            }
        }
    }
}

fun processor() {
    restart = true
}


@Composable
fun GridOne(rows: Int, columns: Int) {
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



fun returnHome()
{
    returnhome = true
}

fun gridChecker(scoreS: List<Int>): Boolean {
    for (value in scoreS) {
        if (value == 4)
        {
            return true
        }
    }
    return false
}

fun valueReturnerTwo(scORES:List<Int>):Int
{
    for(en in scORES)
    {
        if(en>4)
        {
            return en
        }

    }
    return 0
}

fun moreThanFour(scorES:List<Int>): Boolean{
    for (valu in scorES) {
        if (valu > 4) {
            return true
        }
    }
    return false

}



fun valueReturner(scoreS: List<Int>,vAL :Int): Int {
    for ((index, score) in scoreS.withIndex()) {
        if (score == vAL) {
            return index
        }
    }
    return 0
}

fun workCommon(
    press: Int,
    game: Int,
    addOn: MutableList<Int>,
    scores: MutableList<Int>,
    playerOne: MutableList<Int>,
    playerTwo: MutableList<Int>,
    blueColor: MutableList<Int>,
    redColor: MutableList<Int>,
): List<List<Int>> {

    val activeOne: MutableList<Int> = if (game % 2 == 0) redColor else blueColor
    val activeTwo: MutableList<Int> = if (game % 2 == 0) blueColor else redColor
    val localOne : MutableList<Int> = if (game%2 == 0) playerOne else playerTwo
    val localTwo : MutableList<Int> = if (game%2 == 0) playerTwo else playerOne


    val sizee = addOn.size

    val common: List<Int> = addOn.filter { it in activeTwo }

    if (common.isEmpty())
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

        for (k in common) {
            activeTwo.remove(k)
            localTwo.remove(k)
            scores[k] = 1 + scores[k]
            localOne.add(k)
            activeOne.add(k)
        }

        if(sizee<=3)
        {
            activeOne.add(press)
            scores[press] = 4 - sizee
            localOne.add(press)
        }

        addOn.removeAll(common)


        for (l in addOn) {
            scores[l] = scores[l] + 1
            if(l !in activeOne)
            {
                localOne.add(l)
                activeOne.add(l)
            }

        }

    }

    val fire: List<List<Int>> = listOf(scores, playerOne, playerTwo, blueColor, redColor)
    return fire
}

fun fourAdder(clicker:Int,aDD:MutableList<Int>,scorees: MutableList<Int>):MutableList<Int>
{


    for(wu in aDD)
    {

        scorees[wu] = scorees[wu] + 1

    }
    scorees[clicker] = 4 - aDD.size

    return scorees
}
fun numberFinder(rew:Int,colemn:Int):List<List<Int>>
{
    val list_One =  mutableStateListOf<Int>()
    val list_Two =  mutableStateListOf<Int>()
    val list_Three =  mutableStateListOf<Int>()
    val list_four =  mutableStateListOf<Int>()
    val list_Five =  mutableStateListOf<Int>()
    val list_Six =  mutableStateListOf<Int>()
    val list_Seven =  mutableStateListOf<Int>()
    val list_Eight =  mutableStateListOf<Int>()

    list_One.add(0)
    for (ion in 1..<colemn-1)
    {
        list_Two.add(ion)
    }
    list_Three.add(colemn-1)
    for(jio in 1..<rew-1)
    {
        list_four.add(colemn*jio)
    }
    list_Five.add((rew-1)*colemn)
    for(kio in 2..<rew)
    {
        list_Six.add((colemn*kio)-1)
    }
    for(l in 1..<colemn-1)
    {
        list_Seven.add((rew*colemn)-1-l)
    }
    list_Eight.add((colemn*rew)-1)

    val listFinale = listOf(list_One,list_Two,list_Three,list_four,list_Six,list_Five,list_Seven.reversed(),list_Eight)

    return listFinale

}



fun colorChanger(clicked:Int,column:Int):List<Int>
{
    val checker : MutableList<Int> = mutableListOf()

    val gotNumbers = numberFinder(globalRow.toInt(), globalColumn.toInt())

    val offsets = listOf(1,-1,column,-column)

    val offsetsOne = listOf(1,column)
    val offsetsTwo = listOf(-1,1,column)
    val offsetsThree = listOf(-1,column)
    val offsetsFour = listOf(1,column,-column)
    val offsetsFive = listOf(-1,column,-column)
    val offsetsSix = listOf(1,-column)
    val offsetsSeven = listOf(-1,1,-column)
    val offsetsEight = listOf(-1,-column)

    val sidesOne = gotNumbers[0]
    val sidesTwo =  gotNumbers[1]
    val sidesThree =  gotNumbers[2]
    val sidesFour =  gotNumbers[3]
    val sidesFive =  gotNumbers[4]
    val sidesSix =  gotNumbers[5]
    val sidesSeven =  gotNumbers[6]
    val sidesEight =  gotNumbers[7]

    if(clicked in sidesOne)
    {
        for(mi in offsetsOne)
        {
            checker.add(mi+clicked)
        }
    }
    else if(clicked in sidesTwo)
    {
        for(ni in offsetsTwo)
        {
            checker.add(ni+clicked)
        }
    }
    else if(clicked in sidesThree)
    {
        for(pi in offsetsThree)
        {
            checker.add(pi+clicked)
        }
    }
    else if(clicked in sidesFour)
    {
        for(qi in offsetsFour)
        {
            checker.add(qi+clicked)
        }
    }
    else if(clicked in sidesFive)
    {
        for(ri in offsetsFive)
        {
            checker.add(ri+clicked)
        }
    }
    else if(clicked in sidesSix)
    {
        for(si in offsetsSix)
        {
            checker.add(si+clicked)
        }
    }
    else if(clicked in sidesSeven)
    {
        for(ti in offsetsSeven)
        {
            checker.add(ti+clicked)
        }
    }
    else if(clicked in sidesEight)
    {
        for(ui in offsetsEight)
        {
            checker.add(ui+clicked)
        }
    }
    else
    {
        for(vu in offsets)
        {
            checker.add(vu+clicked)
        }
    }
    return checker
}

@Composable
fun StarOne(cond : List<Boolean>)
{
    if(cond[4])
    {
        Box(){
            Icon(Icons.Default.Star, contentDescription = "staOne", tint = Color.Yellow,modifier = Modifier.offset(115.dp,25.dp).size(20.dp))
        }
    }

    if(cond[1])
    {
        Box(){
            Icon(Icons.Default.Star, contentDescription = "staOne", tint = Color.Yellow,modifier = Modifier.offset(140.dp,20.dp).size(30.dp))
        }
    }
    if(cond[0])
    {
        Box(){
            Icon(Icons.Default.Star, contentDescription = "staOne", tint = Color.Yellow,modifier = Modifier.offset(175.dp,15.dp).size(40.dp))
        }
    }
    if(cond[2])
    {
        Box(){
            Icon(Icons.Default.Star, contentDescription = "staOne", tint = Color.Yellow,modifier = Modifier.offset(220.dp,20.dp).size(30.dp))
        }

    }
    if(cond[3])
    {
        Box(){
            Icon(Icons.Default.Star, contentDescription = "staOne", tint = Color.Yellow,modifier = Modifier.offset(255.dp,25.dp).size(20.dp))
        }
    }


}

@Composable
fun StarTwo(vond:List<Boolean>)
{
    if(vond[4])
    {
        Box(){
            Icon(Icons.Default.Star, contentDescription = "staOne", tint = Color.Yellow,modifier = Modifier.offset(115.dp,710.dp).size(20.dp))
        }
    }

    if(vond[1])
    {
        Box(){
            Icon(Icons.Default.Star, contentDescription = "staOne", tint = Color.Yellow,modifier = Modifier.offset(140.dp,705.dp).size(30.dp))
        }
    }

    if(vond[0])
    {
        Box(){
            Icon(Icons.Default.Star, contentDescription = "staOne", tint = Color.Yellow,modifier = Modifier.offset(175.dp,700.dp).size(40.dp))
        }
    }
    if(vond[2])
    {
        Box(){
            Icon(Icons.Default.Star, contentDescription = "staOne", tint = Color.Yellow,modifier = Modifier.offset(220.dp,705.dp).size(30.dp))
        }
    }
    if(vond[3])
    {
        Box(){
            Icon(Icons.Default.Star, contentDescription = "staOne", tint = Color.Yellow,modifier = Modifier.offset(255.dp,710.dp).size(20.dp))
        }
    }


}

@Composable
fun Cell(
    value: Int,
    changerBlue: List<Int>,
    changerRed: List<Int>,
    scoree: List<Int>,
    onClick:  (Int) -> Unit
) {
    val color = if (changerBlue.contains(value)) {
        lightBlue
    } else if (changerRed.contains(value)) {
        lightRed
    } else {
        cellColor
    }
    val size = if(globalColumn.toInt() == 3)
    {
        if(globalRow.toInt() <4)  90 else 55
    }
    else if(globalColumn.toInt() == 4)
    {
        if(globalRow.toInt() <4)  80 else 55
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
fun scoreDisplayer(sCORES: List<Int>, colour: List<Int>): Int
{
    var scco = 0
    for(sco in colour)
    {
        scco +=  sCORES[sco]
    }

    return scco
}

@Composable
fun ExitButton()
{
    Box(modifier = Modifier
        .size(48.dp)
        .offset(330.dp, 15.dp)
        .clickable {
            pause = true
            Log.d("hi", "Im clicked ")
        }
        .background(shape = RoundedCornerShape(30.dp), color = Color.White),
        contentAlignment = Alignment.Center
    )
    {
        Icon(painter = painterResource(id = R.drawable.baseline_pause_24),  modifier = Modifier
            .size(32.dp),tint = Color.Gray ,contentDescription = "pause icon")
    }
}

@Composable
fun BoxWithText(modifier: Modifier = Modifier, color: Color, word: Int) {


    Box(
        modifier = modifier.background(cellColor,shape = RoundedCornerShape(18.dp)),
        contentAlignment = Alignment.Center
    ) {
        val sizeCircle = if(globalColumn.toInt() == 3)
        {
            if(globalRow.toInt() <4)  70 else 40

        }
        else if(globalColumn.toInt() == 4)
        {
            if(globalRow.toInt() <4)  60 else 40
        }
        else if(globalColumn.toInt() == 5)
        {
            if(globalRow.toInt() <4)  50 else 40
        }
        else if(globalColumn.toInt() == 6)
        {
            40
        }
        else if(globalColumn.toInt() == 7)
        {
            30
        }
        else
        {
            30
        }

        val sizeText = if(globalColumn.toInt() == 3)
        {
            if(globalRow.toInt() <4)  50 else 30
        }
        else if(globalColumn.toInt() == 4)
        {
            if(globalRow.toInt() <4)  40 else 30
        }
        else if(globalColumn.toInt() == 5)
        {
            if(globalRow.toInt() <4)  30 else 25
        }
        else if(globalColumn.toInt() == 6)
        {
            20
        }
        else if(globalColumn.toInt() == 7)
        {
            20
        }
        else
        {
            20
        }
        Box(
            modifier = Modifier
                .size(sizeCircle.dp)
                .background(color = color, shape = RoundedCornerShape(30.dp)),
            contentAlignment = Alignment.Center
        ) {
            var angle = 0f
            if(game%2 != 0)
            {
                angle = 180f
            }
            else
            {
                angle = 0f
            }
            if (word != 0)
            {
                Text(
                    text = "$word",
                    style = TextStyle(color = Color.White, fontSize = sizeText.sp),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.rotate(angle)
                )
            }
            else
            {
                Text(text = " ")
            }
        }
    }
}

@Composable
fun PlayerNameTwoDisplay()
{
    Box(
        modifier = Modifier
            .width(400.dp)
            .height(220.dp)
            .drawBehind {
                val q = Path().apply {
                    moveTo(360f, 300f)

                    val tt = Rect(330f, 270f, 390f, 330f)
                    arcTo(
                        rect = tt,
                        startAngleDegrees = 120f,
                        sweepAngleDegrees = 120f,
                        forceMoveTo = false
                    )
                    moveTo(345f, 274f)
                    lineTo(500f, 200f)
                    lineTo(800f, 200f)
                    lineTo(800f, 400f)
                    lineTo(500f, 400f)
                    lineTo(345f, 325.9f)
                    moveTo(800f, 250f)
                    val pp = Rect(750f, 200f, 850f, 300f)
                    arcTo(
                        rect = pp,
                        startAngleDegrees = 0f,
                        sweepAngleDegrees = -90f,
                        forceMoveTo = false
                    )
                    moveTo(800f, 350f)
                    val ppp = Rect(750f, 300f, 850f, 400f)
                    arcTo(
                        rect = ppp,
                        startAngleDegrees = 0f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false
                    )
                    moveTo(850f, 250f)
                    lineTo(850f, 350f)
                    lineTo(800f, 350f)
                    lineTo(800f, 250f)
                    close()
                }
                val w = Path().apply {
                    moveTo(360f, 300f)
                    val uu = Rect(340f, 280f, 380f, 320f)
                    arcTo(
                        rect = uu,
                        startAngleDegrees = 120f,
                        sweepAngleDegrees = 120f,
                        forceMoveTo = false
                    )
                    moveTo(350f, 282.6f)
                    lineTo(500f, 215f)
                    lineTo(800f, 215f)
                    lineTo(800f, 385f)
                    lineTo(500f, 385f)
                    lineTo(350f, 317f)
                    moveTo(800f, 250f)
                    val yyy = Rect(765f, 215f, 835f, 285f)
                    arcTo(
                        rect = yyy,
                        startAngleDegrees = 0f,
                        sweepAngleDegrees = -90f,
                        forceMoveTo = false
                    )
                    moveTo(800f, 350f)
                    val yy = Rect(765f, 315f, 835f, 385f)
                    arcTo(
                        rect = yy,
                        startAngleDegrees = 0f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false
                    )
                    moveTo(835f, 350f)
                    lineTo(835f, 250f)
                    lineTo(800f, 250f)
                    lineTo(800f, 350f)

                }
                drawPath(
                    path = q,
                    color = Color.Black
                )
                drawPath(
                    path = w,
                    color = lightBlack
                )
            }
        ,contentAlignment = Alignment.Center)

    {
        Row(verticalAlignment = Alignment.CenterVertically)
        {
            Spacer(modifier = Modifier.width(80.dp))
            Text(text = playerNameTwo.toUpperCase(), fontSize = 35.sp, fontWeight = FontWeight.Bold, color = lightBlue, modifier = Modifier.rotate(180f))
        }

    }
}


@Composable
fun PlayerNameOneDisplay()
{
    Box(
        modifier = Modifier
            .fillMaxSize()
            .drawBehind {
                val yui = Path().apply {
                    moveTo(740f, 1800f)
                    val vuv = Rect(710f, 1770f, 770f, 1830f)
                    arcTo(
                        rect = vuv,
                        startAngleDegrees = 60f,
                        sweepAngleDegrees = -120f,
                        forceMoveTo = false
                    )
                    moveTo(755f, 1774f)
                    lineTo(600f, 1700f)
                    lineTo(300f, 1700f)
                    lineTo(300f, 1900f)
                    lineTo(600f, 1900f)
                    lineTo(755f, 1825.9f)
                    moveTo(300f, 1750f)
                    val ruy = Rect(250f, 1700f, 350f, 1800f)
                    arcTo(
                        rect = ruy,
                        startAngleDegrees = -90f,
                        sweepAngleDegrees = -90f,
                        forceMoveTo = false
                    )
                    moveTo(300f, 1850f)
                    val ruyy = Rect(250f, 1800f, 350f, 1900f)
                    arcTo(
                        rect = ruyy,
                        startAngleDegrees = 90f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false
                    )
                    moveTo(250f, 1850f)
                    lineTo(250f, 1750f)
                    lineTo(300f, 1750f)
                    lineTo(300f, 1850f)

                }

                val opp = Path().apply {
                    moveTo(740f, 1800f)
                    val vuvu = Rect(720f, 1780f, 760f, 1820f)
                    arcTo(
                        rect = vuvu,
                        startAngleDegrees = 60f,
                        sweepAngleDegrees = -120f,
                        forceMoveTo = false
                    )
                    moveTo(750f, 1782.6f)
                    lineTo(600f, 1715f)
                    lineTo(300f, 1715f)
                    lineTo(300f, 1885f)
                    lineTo(600f, 1885f)
                    lineTo(750f, 1817.3f)
                    moveTo(300f, 1750f)
                    val ruyyy = Rect(265f, 1715f, 335f, 1785f)
                    arcTo(
                        rect = ruyyy,
                        startAngleDegrees = -90f,
                        sweepAngleDegrees = -90f,
                        forceMoveTo = false
                    )
                    moveTo(300f, 1850f)
                    val ruyyu = Rect(265f, 1815f, 335f, 1885f)
                    arcTo(
                        rect = ruyyu,
                        startAngleDegrees = 90f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false
                    )
                    moveTo(265f, 1750f)
                    lineTo(265f, 1850f)
                    lineTo(300f, 1850f)
                    lineTo(300f, 1750f)
                    close()

                }
                drawPath(
                    path = yui,
                    color = Color.Black
                )
                drawPath(
                    path = opp,
                    color = lightBlack
                )
                val textPainter = Paint()
                    .asFrameworkPaint()
                    .apply {
                        isAntiAlias = true
                        textSize = 35.sp.toPx()
                        color = lightRed.toArgb()
                        textAlign = android.graphics.Paint.Align.LEFT
                        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                    }

                drawContext.canvas.nativeCanvas.drawText(
                    playerNameOne.toUpperCase(),
                    320f,
                    1840f,
                    textPainter
                )
            }
    )
    {

    }
}


