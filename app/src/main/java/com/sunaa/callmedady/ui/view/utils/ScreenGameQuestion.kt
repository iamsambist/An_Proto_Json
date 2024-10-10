package com.sunaa.callmedady.ui.view.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sunaa.callmedady.ui.view.MainViewModel

@Composable
fun ScreenQuestionOne(mainViewModel: MainViewModel) {
    val text by mainViewModel.questionOneText.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "NAME OF YOUR PROFESSION PLEASE ?",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(start = 5.dp)
        )
        Spacer(Modifier.heightIn(10.dp))
        OutlinedTextField(value = text,
            onValueChange = { newValue ->
                mainViewModel.updateQuestionsTextField(newValue)
            })
    }
}

@Composable
fun ScreenQuestionTwo(mainViewModel: MainViewModel) {
    val text by mainViewModel.questiontwoText.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "WHAT IS YOUR NAME DEAR ?",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(start = 5.dp)
        )
        Spacer(Modifier.heightIn(10.dp))
        OutlinedTextField(value = text,
            onValueChange = { newValue ->
                mainViewModel.updateQuestionsTextField(newValue)
            })
    }
}

@Composable
fun ScreenQuestionThree(mainViewModel: MainViewModel) {
    val radioSelection by mainViewModel.selectedRadioButton.collectAsState()
    var isSelected by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "ARE YOU HAPPY WITH THE JOB?",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(start = 5.dp)
        )
        Spacer(Modifier.heightIn(10.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Row (modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically){
                RadioButton(selected = radioSelection == 1,
                    onClick = { mainViewModel.updateRadioSelection( 1 ) })
                Text(text = "YES")
            }
            Row (modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically){
                RadioButton(selected = radioSelection == 2,
                    onClick = { mainViewModel.updateRadioSelection(2) })
                Text(text = "No")
            }
        }
    }
}
