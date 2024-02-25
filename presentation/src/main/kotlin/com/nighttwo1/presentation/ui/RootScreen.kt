package com.nighttwo1.presentation.ui

import androidx.compose.runtime.Composable
import com.nighttwo1.presentation.theme.MoviesTheme
import com.nighttwo1.presentation.ui.home.HomePage

@Composable
fun RootScreen() {
    MoviesTheme {
        HomePage()
    }
}