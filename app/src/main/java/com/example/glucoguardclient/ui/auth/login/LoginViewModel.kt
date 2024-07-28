package com.example.glucoguardclient.ui.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.glucoguardclient.NavigationEvent
import com.example.glucoguardclient.data.response.LoginResponse
import com.example.glucoguardclient.data.send.LogInUser
import com.example.glucoguardclient.network.api.ApiService
import com.example.glucoguardclient.network.api.RetrofitClient
import com.example.glucoguardclient.ui.auth.register.SignUpUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _navigationEvent = MutableStateFlow<NavigationEvent?>(null)
    val navigationEvent: StateFlow<NavigationEvent?> = _navigationEvent.asStateFlow()

    val apiService = RetrofitClient.apiService

    private var _authToken: String? = null
    val authToken: String?
        get() = _authToken

    fun signIn() {
        viewModelScope.launch {
            _uiState.update { it.copy(isAuthenticating = true) }
            try {
                val userLogin = LogInUser(
                    email = uiState.value.email,
                    password = uiState.value.password
                )

                val response = apiService.loginUser(userLogin)
                if (response.isSuccessful) {
                    response.body()?.let { body ->
                        val token = body as String //["token"] as? String //todo correct it
                        if (token != null) {
                            _authToken = token  // Store the token in the ViewModel
                            _uiState.update {
                                it.copy(
                                    authenticationSucceed = true,
                                )
                            }
                            navigateToHome()
                        } else {
                            _uiState.update {
                                it.copy(authErrorMessage = "Invalid server response: missing token")
                            }
                        }
                    }
                } else {
                    _uiState.update {
                        it.copy(authErrorMessage = "Login failed: ${response.message()}")
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(authErrorMessage = "Network error: ${e.localizedMessage}")
                }
            } finally {
                _uiState.update { it.copy(isAuthenticating = false) }
            }
        }
    }

    fun updateEmail(email: String){
        _uiState.update{it.copy(email = email)}

    }

    fun updatePassword(password: String){
        _uiState.update{it.copy(password = password)}

    }

    fun navigateToHome(){
        _navigationEvent.value = NavigationEvent.NavigateToLogin

    }

    fun onNavigateToSignup(){
        _navigationEvent.value = NavigationEvent.NavigateToSignUp
    }

    fun onNavigationHandled() {
        _navigationEvent.value = null
    }

}


data class LoginUiState(
    var email: String = "",
    var password: String = "",
    var isAuthenticating: Boolean = false,
    var authErrorMessage: String? = null,
    var authenticationSucceed: Boolean = false
)