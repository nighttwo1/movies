package com.nighttwo1.presentation.component.bottomNavigationBar

import android.util.Log
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.nighttwo1.presentation.res.vector.MyIconPack
import com.nighttwo1.presentation.res.vector.myiconpack.IcNewBox
import com.nighttwo1.presentation.ui.Home
import com.nighttwo1.presentation.ui.LocalMainViewNavigation
import com.nighttwo1.presentation.ui.Search
import com.nighttwo1.presentation.ui.Upcoming

@Composable
fun BottomNavigationBar() {
    val mainViewNavigation = LocalMainViewNavigation.current
//    Log.d("nav", "${mainViewNavigation.navHostController.currentBackStackEntryAsState().value?.destination?.route}")
    val currentBackStackEntry by mainViewNavigation.navHostController.currentBackStackEntryAsState()

    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = mainViewNavigation.isHome(),
            enabled = currentBackStackEntry?.destination?.route != Home.javaClass.name,
            onClick = { mainViewNavigation.goHome() }
        )
        NavigationBarItem(
            icon = { Icon(MyIconPack.IcNewBox, contentDescription = "MoreVert", modifier = Modifier.size(24.dp)) },
            label = { Text("Up coming") },
            selected = mainViewNavigation.isUpcoming(),
            enabled = currentBackStackEntry?.destination?.route != Upcoming.javaClass.name,
            onClick = { mainViewNavigation.goUpcoming() }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
            label = { Text("Search") },
            selected = mainViewNavigation.isSearch(),
            enabled = currentBackStackEntry?.destination?.route != Search.javaClass.name,
            onClick = { mainViewNavigation.goSearch() }
        )
    }
}