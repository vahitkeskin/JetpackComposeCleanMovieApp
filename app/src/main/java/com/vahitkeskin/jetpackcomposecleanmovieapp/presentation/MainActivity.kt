package com.vahitkeskin.jetpackcomposecleanmovieapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vahitkeskin.jetpackcomposecleanmovieapp.presentation.movies.views.MovieScreen
import com.vahitkeskin.jetpackcomposecleanmovieapp.presentation.theme.JetpackComposeCleanMovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeCleanMovieAppTheme(darkTheme = false) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.MovieScreen.route) {
                        composable(route = Screen.MovieScreen.route) {
                            MovieScreen(navController = navController)
                        }
                        composable(route = Screen.MovieDetailScreen.route) {

                        }
                    }
                }
            }
        }
    }
}