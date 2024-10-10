package com.sunaa.callmedady.ui.view.utils

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseOutBounce
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.sunaa.callmedady.ui.view.MainViewModel
import kotlinx.coroutines.delay


@Composable
fun GameScreen(mainViewModel: MainViewModel,
               onUpdateAction : ((String,String,Boolean)->Unit)) {
    val gameState by mainViewModel.gameState.collectAsState()
    val errorState by mainViewModel.error.collectAsState()
    val gameEndNotifi by mainViewModel.gameEnd.collectAsState()
    var offsetX by remember { mutableStateOf(0f) }
    var swipeActionPerformed by remember { mutableStateOf(false) }
    val swipeThreshold = 100f

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 40.dp, end = 10.dp)

    ) {
        if(!gameEndNotifi){
            when (gameState) {
                1 -> ScreenQuestionOne(mainViewModel)
                2 -> ScreenQuestionTwo(mainViewModel)
                3 -> ScreenQuestionThree(mainViewModel)
            }
        }
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .height(100.dp)
                .padding(top = 20.dp)
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onDragEnd = {
                            swipeActionPerformed = false
                            offsetX = 0f
                        }
                    ) { change, dragAmount ->
                        offsetX += dragAmount

                        if (!swipeActionPerformed) {
                            if (offsetX > swipeThreshold) {
                                mainViewModel.performRightSwipe()
                                swipeActionPerformed = true
                            } else if (offsetX < (-swipeThreshold)) {
                                mainViewModel.performLeftSwipe()
                                swipeActionPerformed = true
                            }
                        }

                    }
                },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if(!gameEndNotifi){
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back swipe",
                    modifier = Modifier.padding(end = 10.dp)
                )
                Text(
                    text = " SWAP ME BABY "
                )
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "front swipe",
                    modifier = Modifier.padding(start = 10.dp)
                )
            } else {
                Button(onClick = {
                    val prof = mainViewModel.dadyChoices[0].toString()
                    val des = mainViewModel.dadyChoices[1].toString()
                    val liking = mainViewModel.dadyChoices[2].toString().toBoolean()
                    onUpdateAction(prof,des,liking)
                }) {
                    Text(text = "Let's Go In",
                        style = MaterialTheme.typography.headlineSmall)
                }
            }

        }

        if(errorState.errorStatus){
            Text(text = errorState.errorMessage,
                style = MaterialTheme.typography.headlineSmall)
        }
    }

}

@Composable
fun IntroduceText(text: String) {
    // State to track if the text should be fully visible
    var isVisible by remember { mutableStateOf(false) }

    // Animate the alpha value from 0 (invisible) to 1 (fully visible)
    val alpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,  // Alpha changes based on visibility
        animationSpec = tween(durationMillis = 2000)  // 0.5 seconds animation
    )

    // LaunchedEffect to delay visibility by 0.5 seconds
    LaunchedEffect(Unit) {
        delay(500)  // Wait 0.5 seconds
        isVisible = true  // Make the text fully visible
    }

    // Display the text with the animated alpha
    Text(
        text = text,
        modifier = Modifier.alpha(alpha),
        style = MaterialTheme.typography.headlineSmall,

        )
}


@Composable
fun WelComeText(text: String) {


    // List to hold each letter with its animation state
    val animatables = remember {
        text.map { Animatable(initialValue = -500f) }
    }

    // Coroutine scope to trigger animations with a delay
    LaunchedEffect(Unit) {
        animatables.forEachIndexed { index, animatable ->
            // Launch a delay for each letter drop
            delay(1L * index)
            // Animate the position of the letter
            animatable.animateTo(
                targetValue = 0f,  // Target position (final position)
                animationSpec = tween(
                    durationMillis = 100,
                    easing = EaseOutBounce
                )  // Bouncing effect
            )
        }
    }

    // Row to display the text with each letter animated separately
    Row {
        text.forEachIndexed { index, char ->
            // Translate each letter using the animated state
            Text(
                text = char.toString(),
                modifier = Modifier
                    .graphicsLayer(
                        translationY = animatables[index].value  // Applying the Y translation
                    ),
                // You can change the style based on your needs
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}
