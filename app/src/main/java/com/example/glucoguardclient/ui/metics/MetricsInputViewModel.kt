package com.example.glucoguardclient.ui.metics

import androidx.lifecycle.ViewModel
import com.example.glucoguardclient.data.send.PostData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.viewModelScope
import com.example.glucoguardclient.NavigationEvent
import com.example.glucoguardclient.network.api.RetrofitClient
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class MetricsInputViewModel(private val token: String): ViewModel() {
    private val _uiState = MutableStateFlow(PredictScreenState())
    val uiState: StateFlow<PredictScreenState> = _uiState.asStateFlow()

    private val _navigationEvent = MutableStateFlow<NavigationEvent?>(null)
    val navigationEvent: StateFlow<NavigationEvent?> = _navigationEvent.asStateFlow()

    private val apiService = RetrofitClient.apiService



    fun getPredictions(){

        viewModelScope.launch{
            try{
                val data = PostData(
                    pregnancies = uiState.value.pregnancies,
                    Glucose = uiState.value.Glucose,
                    BloodPressure = uiState.value.BloodPressure,
                    SkinThickness = uiState.value.SkinThickness,
                    Insulin = uiState.value.Insulin,
                    BMI = uiState.value.BMI,
                    DiabetesPedigreeFunction = uiState.value.DiabetesPedigreeFunction,
                    Age = uiState.value.Age
                )
                _uiState.update { it.copy(predicting = true) }

                val response = apiService.getPrediction(data=data, token=token)
                if(response.isSuccessful){
                    val prediction = response.body()!!.prediction[0]
                    val negPercentage = response.body()!!.probability[0]
                    val posPercentage = response.body()!!.probability[1]
                    navigateToResultScreen(
                        prediction,
                        negPercentage,
                        posPercentage
                    )

                }else{
                   _uiState.update { it.copy(errorMessage = "sorry an error occurred") }
                }

            }catch (e: Exception){
                _uiState.update { it.copy(errorMessage = "Sorry an error occurred") }
            }

        }
    }


    fun updatePregnancies(value: Int){
        _uiState.update { it.copy(pregnancies = value) }
    }

    fun updateGlucose(value: Double){
        _uiState.update { it.copy(Glucose = value) }

    }

    fun updateAge(value: Int){
        _uiState.update { it.copy(Age = value) }

    }

    fun updateBMI(value: Double){
        _uiState.update { it.copy(BMI = value) }

    }

    fun updateDiabetesPedigreeFunction(value: Double){
        _uiState.update { it.copy(DiabetesPedigreeFunction = value) }

    }

    fun updateSkinThickness(value: Double){
        _uiState.update { it.copy(SkinThickness = value) }

    }

    fun updateBloodPressure(value: Double){
        _uiState.update { it.copy(BloodPressure = value) }

    }


    fun updateInsulin(value: Double){
        _uiState.update { it.copy(Insulin = value) }

    }


    fun navigateToResultScreen(prediction: Int, negPercentage: Double, posPercentage: Double){
        _navigationEvent.value = NavigationEvent.NavigateToPredictionResult(
            prediction,
            negPercentage,
            posPercentage
        )

    }


}


data class PredictScreenState(
    val pregnancies: Int = 0,
    val Glucose: Double = 0.0,
    val BloodPressure: Double = 0.0,
    val SkinThickness: Double = 0.0,
    val Insulin: Double = 0.0,
    val BMI: Double = 0.0,
    val DiabetesPedigreeFunction: Double = 0.0,
    val Age: Int = 0,
    val predicting: Boolean = false,
    val resultReceived: Boolean = false,
    val errorMessage: String? = null
)