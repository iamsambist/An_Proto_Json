package com.sunaa.callmedady.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sunaa.callmedady.R
import com.sunaa.callmedady.ui.view.utils.WelComeText

@Composable
fun ScreenHome(
    profession: String, nameOfProfessional: String, happyStatus: Boolean,
    onResetAction: (() -> Unit)
) {

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
        ) {

            WelComeText("HELLO ${nameOfProfessional} BABY ")
        }
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column() {
                Text(text = "HERE IS WHAT I HAVE FOR YOU")
                Text(text = "YOU ARE AN ${profession}")
                Text(text = if (happyStatus) "& YOU LOVE TO WORK" else "& YOU HATE TO WORK")
            }
            Box() {
                Image(
                    painter = painterResource(if (happyStatus) R.drawable.happy else R.drawable.sad),
                    contentDescription = "men",
                    modifier = Modifier.size(100.dp)
                )
            }
        }
        Row(modifier = Modifier.padding(10.dp)) {
            Button(
                onClick = { onResetAction() },

                colors = ButtonDefaults.buttonColors(Color.White)
            ) {
                Text(
                    text = "RESET YOURSELF",
                    color = Color.Black
                )
            }
        }


    }
}
