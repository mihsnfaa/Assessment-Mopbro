package org.d3if0088.miniproject1.navigation

import org.d3if0088.miniproject1.ui.screen.KEY_ID_COMICS

sealed class Screen (val  route: String){
    data object Landing: Screen("landingScreen")
    data object Home: Screen("mainScreen")
    data object About: Screen("aboutScreen")
    data object FormBaru: Screen("detailScreen")
    data object FormUbah: Screen("detailScreen/{$KEY_ID_COMICS}") {
        fun withId(id: Long) = "detailScreen/$id"
    }
}