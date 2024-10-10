package com.sunaa.callmedady.ui.view

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sunaa.callmedady.protojson.DadyDetails

@Composable
fun ScreenMain(
    dadyDetails: DadyDetails,
    onUpdateAction: ((String, String, Boolean) -> Unit),
    onResetAction: (() -> Unit),
    mainViewModel: MainViewModel = viewModel()
) {

    if (dadyDetails.profession != "000") {
        val progression = dadyDetails.profession.uppercase()
        val nameOfProfessional = dadyDetails.nameOfProfessional.uppercase()
        val liking = dadyDetails.liking
        ScreenHome(progression, nameOfProfessional, liking, onResetAction = onResetAction)
    } else {
        ScreenGame(mainViewModel, onUpdateAction = onUpdateAction)
    }

}