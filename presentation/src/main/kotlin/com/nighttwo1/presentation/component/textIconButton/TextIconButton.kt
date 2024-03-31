package com.nighttwo1.presentation.component.textIconButton

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nighttwo1.presentation.theme.MoviesTheme

@Composable
fun TextIconButton(
    onClick: (() -> Unit),
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    prefixIcon: @Composable () -> Unit = {},
    text: @Composable () -> Unit = {},
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.textButtonColors(
            containerColor = Color.Black,
            disabledContainerColor = Color.Gray,
            contentColor = Color.White,
            disabledContentColor = MaterialTheme.colorScheme.onSecondary,
        ),
        enabled = enabled,
        shape = RoundedCornerShape(10.dp)
    ) {
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = Color.White,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        } else {
            prefixIcon()
        }
        Spacer(modifier = Modifier.width(10.dp))
        text()
    }

}

@Preview
@Composable
fun TextIconButtonLoadingDisabledPreview() {
    MoviesTheme {
        TextIconButton(
            onClick = {},
            loading = true,
            enabled = false,
            prefixIcon = { Icon(Icons.Default.PlayArrow, contentDescription = null) }) {
            Text("Loading Button")
        }
    }
}

@Preview
@Composable
fun TextIconButtonPreview() {
    MoviesTheme {
        TextIconButton(
            onClick = {},
            prefixIcon = { Icon(Icons.Default.PlayArrow, contentDescription = null) }) {
            Text("Sample Button")
        }
    }
}