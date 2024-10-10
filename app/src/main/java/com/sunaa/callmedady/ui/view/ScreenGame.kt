package com.sunaa.callmedady.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sunaa.callmedady.R
import com.sunaa.callmedady.ui.theme.CallMEDadyTheme
import com.sunaa.callmedady.ui.view.utils.GameScreen
import com.sunaa.callmedady.ui.view.utils.IntroduceText
import com.sunaa.callmedady.ui.view.utils.WelComeText
import kotlinx.coroutines.delay

@Composable
fun ScreenGame(
    mainViewModel: MainViewModel,
    onUpdateAction : ((String,String,Boolean)->Unit)
) {
    var gameVisibility by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(2000) // delay for 2 sec
        gameVisibility = true
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF81D4FA))
            .padding(WindowInsets.statusBars.asPaddingValues())
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            WelComeText("WELCOME DEAR")
        }
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(R.drawable.mennn),
                contentDescription = "men",
                modifier = Modifier.size(200.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            contentAlignment = Alignment.Center
        ) {
            IntroduceText("Let's Know You Better")
        }
        Spacer(Modifier.height(60.dp))
        Box(
            modifier = Modifier
                .wrapContentSize()
                .padding(10.dp)
        ) {
            if (gameVisibility) {
                GameScreen(mainViewModel, onUpdateAction = onUpdateAction)
            }

        }


    }
}


