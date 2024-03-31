package com.nighttwo1.presentation.component.bottomNavigationBar

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nighttwo1.presentation.res.vector.MyIconPack
import com.nighttwo1.presentation.res.vector.myiconpack.IcNewBox
import com.nighttwo1.presentation.ui.LocalMainViewNavigation

@Composable
fun BottomNavigationBar() {
    val mainViewNavigation = LocalMainViewNavigation.current

    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = mainViewNavigation.isHome(),
            onClick = { mainViewNavigation.goHome() }
        )
        NavigationBarItem(
            icon = { Icon(MyIconPack.IcNewBox, contentDescription = "MoreVert", modifier = Modifier.size(24.dp)) },
            label = { Text("Up coming") },
            selected = mainViewNavigation.isUpcoming(),
            onClick = { mainViewNavigation.goUpcoming() }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
            label = { Text("Search") },
            selected = mainViewNavigation.isSearch(),
            onClick = { mainViewNavigation.goSearch() }
        )
    }
}