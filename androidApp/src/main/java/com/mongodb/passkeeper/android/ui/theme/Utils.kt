package com.mongodb.passkeeper.android.ui.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TopContainer(container: (paddingValue: PaddingValues) -> Unit, title: String) {
    PassKeeperTheme {
        Scaffold(topBar = {
            TopAppBar {
                Text(
                    text = title,
                    fontSize = 24.sp,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }) {
            container(it)
        }
    }
}
