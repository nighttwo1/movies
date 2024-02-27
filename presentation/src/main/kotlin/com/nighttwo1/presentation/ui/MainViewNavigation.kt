package com.nighttwo1.presentation.ui

import androidx.navigation.NavHostController

class MainViewNavigation(
    val navHostController: NavHostController,
) {
    fun goHome() {
        navHostController.navigate(MainViewNavGraph.Home.route) {
            navHostController.graph.startDestinationRoute?.let {
                popUpTo(it) { saveState = true }
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun goSearch() {
        navHostController.navigate(MainViewNavGraph.Search.route) {
            navHostController.graph.startDestinationRoute?.let {
                popUpTo(it) { saveState = true }
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun isHome(): Boolean {
        return navHostController.currentBackStackEntry?.destination?.route == MainViewNavGraph.Home.route
    }

    fun isSearch(): Boolean {
        return navHostController.currentBackStackEntry?.destination?.route == MainViewNavGraph.Search.route
    }
}

interface NavigationRoute {
    val route: String
}

object MainViewNavGraph {
    object Home : NavigationRoute {
        override val route = "Home"
    }

    object Search : NavigationRoute {
        override val route = "Search"
    }
}
