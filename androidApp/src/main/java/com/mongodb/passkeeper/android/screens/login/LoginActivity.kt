package com.mongodb.passkeeper.android.screens.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mongodb.passkeeper.android.R
import com.mongodb.passkeeper.android.screens.home.HomeScreen
import com.mongodb.passkeeper.android.ui.theme.PassKeeperTheme

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Container()
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun Container() {
        PassKeeperTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                LoginView()
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun LoginView() {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            val userName = remember { mutableStateOf("") }
            val password = remember { mutableStateOf("") }
            val loginViewModel = viewModel<LoginViewModel>()

            loginViewModel.loginStatus.observeAsState(false).apply {
                if (this.value) {
                    navigateToHome()
                }
            }

            Image(
                painter = painterResource(id = R.drawable.ic_realm_logo),
                contentScale = ContentScale.Fit,
                contentDescription = "App Logo",
                modifier = Modifier
                    .width(200.dp)
                    .defaultMinSize(minHeight = 200.dp)
                    .padding(bottom = 20.dp)
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                value = userName.value,
                onValueChange = {
                    userName.value = it
                },
                label = { Text(text = "Username") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                value = password.value,
                onValueChange = {
                    password.value = it
                },
                label = { Text(text = "Password") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                )
            )

            Button(onClick = {
                loginViewModel.doLogin(userName.value, password.value)
            }) {
                Text(text = "Login")
            }
        }
    }

    @Composable
    fun navigateToHome() {
        val context = LocalContext.current
        context.startActivity(Intent(context, HomeScreen::class.java))
    }

}