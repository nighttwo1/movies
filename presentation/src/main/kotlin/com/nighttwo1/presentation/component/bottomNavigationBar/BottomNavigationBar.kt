package com.nighttwo1.presentation.component.bottomNavigationBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.nighttwo1.presentation.ui.LocalMainViewNavigation

@Composable
fun BottomNavigationBar() {
    val mainViewNavigation = LocalMainViewNavigation.current

    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Menu, contentDescription = "New & Popular") },
            label = { Text("New & Popular") },
            selected = mainViewNavigation.isMovie(),
            onClick = { mainViewNavigation.goMovie() }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
            label = { Text("Search") },
            selected = mainViewNavigation.isSearch(),
            onClick = { mainViewNavigation.goSearch() }
        )
    }
}