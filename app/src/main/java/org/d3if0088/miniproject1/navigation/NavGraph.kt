package org.d3if0088.miniproject1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.d3if0088.miniproject1.ui.screen.AboutScreen
import org.d3if0088.miniproject1.ui.screen.LandingScreen
import org.d3if0088.miniproject1.ui.screen.MainScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()){
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route){
            LandingScreen(navController)
        }
//        composable(route = Screen.Temperature.route){
//            MainScreen(navController)
//        }
        composable(route = Screen.About.route){
            AboutScreen(navController)
        }
    }
}