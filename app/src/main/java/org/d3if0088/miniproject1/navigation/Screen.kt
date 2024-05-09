package org.d3if0088.miniproject1.navigation

sealed class Screen (val route:String){
    data object Home: Screen("mainScreen")
//    data object Temperature: Screen("temperatureScreen")
    data object About: Screen("aboutScreen")
}