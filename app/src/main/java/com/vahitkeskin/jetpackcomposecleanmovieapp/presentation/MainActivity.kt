package com.vahitkeskin.jetpackcomposecleanmovieapp.presentation

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vahitkeskin.jetpackcomposecleanmovieapp.R
import com.vahitkeskin.jetpackcomposecleanmovieapp.presentation.movie_detail.views.MovieDetailScreen
import com.vahitkeskin.jetpackcomposecleanmovieapp.presentation.movies.views.MovieScreen
import com.vahitkeskin.jetpackcomposecleanmovieapp.presentation.theme.JetpackComposeCleanMovieAppTheme
import com.vahitkeskin.jetpackcomposecleanmovieapp.util.Constants.IMDB_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeCleanMovieAppTheme(darkTheme = false) {
                //Navigation Bar color from Jetpack Compose
                val view = LocalView.current
                val activity = view.context as Activity
                activity.window.navigationBarColor = Color.Black.toArgb()

                val navController = rememberNavController()
                val navBackStackEntry =
                    navController.currentBackStackEntryAsState().value?.destination?.route
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.Black,
                                titleContentColor = Color.White,
                            ),
                            title = {
                                val cinema = getString(R.string.icon_cinema)
                                val toolBarTitle = getString(R.string.app_toolbar_title)
                                val popCorn = getString(R.string.icon_popcorn)
                                Text(
                                    "$cinema $toolBarTitle $popCorn",
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            },
                            navigationIcon = {
                                IconButton(
                                    modifier = Modifier.alpha(if (navBackStackEntry?.contains(Screen.MovieDetailScreen.route) == true) 1f else 0f),
                                    onClick = {
                                        navController.popBackStack()
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Back Button",
                                        tint = Color.White
                                    )
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = Screen.MovieScreen.route
                    ) {
                        composable(route = Screen.MovieScreen.route) {
                            MovieScreen(navController = navController)
                        }
                        composable(route = Screen.MovieDetailScreen.route + "/{${IMDB_ID}}") {
                            MovieDetailScreen()
                        }
                    }
                }
            }
        }
    }
}