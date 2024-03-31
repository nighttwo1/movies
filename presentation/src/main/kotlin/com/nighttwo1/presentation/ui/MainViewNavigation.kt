package com.nighttwo1.presentation.ui

import androidx.navigation.NavHostController
import com.nighttwo1.domain.model.MovieId

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

//    fun goMovie() {
//        navHostController.navigate(MainViewNavGraph.Movie.route) {
//            navHostController.graph.startDestinationRoute?.let {
//                popUpTo(it) { saveState = true }
//            }
//            launchSingleTop = true
//            restoreState = true
//        }
//    }
//
//    fun goTVSeries() {
//        navHostController.navigate(MainViewNavGraph.TVSeries.route) {
//            navHostController.graph.startDestinationRoute?.let {
//                popUpTo(it) { saveState = true }
//            }
//            launchSingleTop = true
//            restoreState = true
//        }
//    }

    fun goUpcoming() {
        navHostController.navigate(MainViewNavGraph.Upcoming.route) {
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

    fun goMovieDetail(movieId: MovieId) {
        navHostController.navigate("${MainViewNavGraph.Movie.route}/${movieId.value}") {
            launchSingleTop = true
            restoreState = true
            // 디테일 페이지로 이동할 때 뒤로 가기 버튼을 눌렀을 때 이전 화면으로 이동하도록 popUpTo 설정
            popUpTo(MainViewNavGraph.Home.route) { inclusive = false }
        }
    }

    fun goBack(){
        navHostController.popBackStack()
    }

    fun isHome(): Boolean {
        return navHostController.currentBackStackEntry?.destination?.route == MainViewNavGraph.Home.route
    }

//    fun isMovie(): Boolean {
//        return navHostController.currentBackStackEntry?.destination?.route == MainViewNavGraph.Movie.route
//    }
//
//    fun isTVSeries(): Boolean {
//        return navHostController.currentBackStackEntry?.destination?.route == MainViewNavGraph.TVSeries.route
//    }

    fun isUpcoming(): Boolean {
        return navHostController.currentBackStackEntry?.destination?.route == MainViewNavGraph.Upcoming.route
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

    object Movie : NavigationRoute {
        override val route = "Home/Movie"
    }

    object TVSeries : NavigationRoute {
        override val route = "Home/TVSeries"
    }

    object Upcoming : NavigationRoute {
        override val route = "Upcoming"
    }
}
