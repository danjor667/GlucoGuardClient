package com.example.glucoguardclient


sealed class NavigationEvent {
    object NavigateToLogin : NavigationEvent()
    object NavigateToHome : NavigationEvent()
    object NavigateToSignUp : NavigationEvent()
    object NavigateToProfile : NavigationEvent()
    object NavigateToSettings : NavigationEvent()
    data class NavigateToDetails(val itemId: String) : NavigationEvent()
    object NavigateBack : NavigationEvent()

    // Add more navigation events as needed
}