package com.mongodb.passkeeper.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.mongodb.passkeeper.android.ui.theme.PassKeeperTheme
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

class HomeScreen : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewModel: HomeViewModel = viewModel()
            val currentPassword: String = viewModel.currentPassword.observeAsState("").value

            PassKeeperTheme {
                Scaffold(topBar = {
                    TopAppBar {
                        Text(
                            text = stringResource(id = R.string.title_app_name),
                            fontSize = 24.sp,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    }
                }) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        color = MaterialTheme.colors.background
                    ) {
                        PasswordView(currentPassword) {
                            viewModel.onRefreshPassword()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PasswordView(password: String, onRefreshClick: () -> Unit) {

    Column(
        modifier = Modifier.padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        OutlinedTextField(
            readOnly = true,
            modifier = Modifier
                .align(CenterHorizontally),
            value = password,
            onValueChange = { },
            textStyle = TextStyle(fontWeight = FontWeight.Bold)
        )

        Button(
            modifier = Modifier.padding(top = 12.dp),
            onClick = { onRefreshClick() }) {
            Text(text = "Refresh")
        }
    }
}

