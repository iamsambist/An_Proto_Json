package com.sunaa.callmedady

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.datastore.dataStore
import com.sunaa.callmedady.protojson.DadyDetails
import com.sunaa.callmedady.protojson.DadyDetailsSerializer
import com.sunaa.callmedady.ui.theme.CallMEDadyTheme
import com.sunaa.callmedady.ui.view.ScreenMain
import kotlinx.coroutines.launch

val Context.dataStore by dataStore("dady_details.json", DadyDetailsSerializer)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CallMEDadyTheme {
                val dadyDetailState by dataStore.data.collectAsState(
                    initial = DadyDetails(
                        profession = "000",
                        nameOfProfessional = "***",
                        liking = true
                    )
                )

                val scope = rememberCoroutineScope()
                ScreenMain(dadyDetailState, onUpdateAction = { prof, des, liking ->
                    scope.launch {
                        updateDetails(prof, des, liking)
                    }

                }, onResetAction = {
                    scope.launch {
                        resetUserPreferences()
                    }
                })
            }
        }
    }

    private suspend fun resetUserPreferences() {
      dataStore.updateData {
          it.copy(
              profession = "000",
              nameOfProfessional = "ABC",
              liking = true
          )
      }
    }

    private suspend fun updateDetails(prof: String, des: String, liking: Boolean) {
        dataStore.updateData {
            it.copy(
                profession = prof,
                nameOfProfessional = des,
                liking = liking
            )
        }
    }
}
