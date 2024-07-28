package com.example.glucoguardclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.glucoguardclient.data.ActivityItem
import com.example.glucoguardclient.ui.auth.login.LoginScreen
import com.example.glucoguardclient.ui.auth.login.LoginViewModel
import com.example.glucoguardclient.ui.auth.register.RegisterViewModel
import com.example.glucoguardclient.ui.auth.register.SignUpScreen
import com.example.glucoguardclient.ui.home.HomeScreen
import com.example.glucoguardclient.ui.home.HomeViewModel
import com.example.glucoguardclient.ui.theme.GlucoGuardClientTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GlucoGuardClientTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   // MyApp(Modifier.padding(innerPadding))
                }
            }
        }
    }
}








@ExperimentalMaterial3Api
@Composable
fun MyApp(modifier: Modifier = Modifier) {
    val activities = listOf(
        ActivityItem(
            title = "Running Distance",
            value = "7.25",
            unit = "km",
            icon = Icons.Filled.Face,
            color = Color(0xFFFFB3BA)
        ),
        ActivityItem(
            title = "Average Running Time",
            value = "58",
            unit = "minutes",
            icon = Icons.Filled.ThumbUp,
            color = Color(0xFFBAE1FF)
        ),
        ActivityItem(
            title = "Average Blood Sugar",
            value = "107",
            unit = "mg/dL",
            icon = Icons.Filled.Home,
            color = Color(0xFFBFFCC6)
        ),
        ActivityItem(
            title = "Average Blood Sugar",
            value = "107",
            unit = "mg/dL",
            icon = Icons.Filled.Home,
            color = Color(0xFFBFFCC6)
        ),
        ActivityItem(
            title = "Average Blood Sugar",
            value = "107",
            unit = "mg/dL",
            icon = Icons.Filled.Home,
            color = Color(0xFFBFFCC6)
        ),
        ActivityItem(
            title = "Average Blood Sugar",
            value = "107",
            unit = "mg/dL",
            icon = Icons.Filled.Home,
            color = Color(0xFFBFFCC6)
        )
    )


    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "register") {

        composable("register") {
            val viewModel: RegisterViewModel = viewModel()
            SignUpScreen(
                viewModel = viewModel,
                onNavigateToLogin = {
                    navController.navigate("login") {
                        popUpTo("register") { inclusive = true }
                    }
                }
            )
        }

        composable("login") {
            val viewModel: LoginViewModel = viewModel()
            LoginScreen(
                viewModel = viewModel,
                onNavigateToSignup = {
                    navController.navigate("register"){
                        popUpTo("login") { inclusive = true}
                    }
                },
                onNavigateToHome = { token ->
                    navController.navigate("home/$token") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }


        composable(
            "home/{token}",
            arguments = listOf(navArgument("token") { type = NavType.StringType })
        ) { backStackEntry ->
            val token = backStackEntry.arguments?.getString("token") ?: ""
            val viewModel: HomeViewModel = viewModel()

            HomeScreen(
                activities,
                viewModel,
            )
        }

        composable(
            "inputMetrics/{token}"
        ){

        }

        composable(
            "predictionResult/{token}"
        ){

        }

    }
}

//todo fix the LoginResponse class and LoginViewModel




