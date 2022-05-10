package com.mongodb.passkeeper.android.screens.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mongodb.passkeeper.android.R
import com.mongodb.passkeeper.android.ui.theme.PassKeeperTheme

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
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                    ) {
                        NewPasswordView(currentPassword) {
                            viewModel.onRefreshPassword()
                        }

                        Divider(
                            color = Color.LightGray,
                            modifier = Modifier
                                .padding(horizontal = 36.dp, vertical = 8.dp)
                                .height(1.dp)
                                .fillMaxWidth()
                        )

                        Text(
                            text = "Saved Password",
                            modifier = Modifier
                                .align(CenterHorizontally)
                                .padding(vertical = 16.dp),
                            fontWeight = FontWeight.Normal,
                            fontSize = 20.sp
                        )

                        LazyColumn(content = {
                            item {
                                listItemView()
                                listItemView()
                                listItemView()
                                listItemView()
                                listItemView()
                                listItemView()
                                listItemView()
                                listItemView()
                                listItemView()
                                listItemView()
                                listItemView()
                                listItemView()
                                listItemView()
                                listItemView()
                            }
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun NewPasswordView(password: String, onRefreshClick: () -> Unit) {

    Column(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        horizontalAlignment = CenterHorizontally
    ) {

        var websiteName by remember { mutableStateOf("") }
        var websiteUrl by remember { mutableStateOf("") }
        var onAddClicked by remember { mutableStateOf(false) }

        OutlinedTextField(
            label = { Text(text = "New Password") },
            readOnly = true,
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(vertical = 4.dp),
            value = password,
            onValueChange = { },
            textStyle = TextStyle(fontWeight = FontWeight.Bold)
        )

        if (onAddClicked) {
            OutlinedTextField(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .align(CenterHorizontally),
                value = websiteName,
                onValueChange = { websiteName = it },
                textStyle = TextStyle(fontWeight = FontWeight.Bold),
                label = { Text(text = "Name of website") },
            )

            OutlinedTextField(
                label = { Text(text = "Url") },
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .align(CenterHorizontally),
                value = websiteUrl,
                onValueChange = { websiteUrl = it },
                textStyle = TextStyle(fontWeight = FontWeight.Bold)
            )
        }

        if (onAddClicked) {
            Row(horizontalArrangement = Arrangement.Center) {
                Button(
                    modifier = Modifier.padding(top = 12.dp, end = 6.dp),
                    onClick = {
                        onAddClicked = !onAddClicked
                    }) {
                    Text(text = "Cancel")
                }

                Button(
                    modifier = Modifier.padding(top = 12.dp, start = 6.dp),
                    onClick = {
                        onAddClicked = !onAddClicked
                        onRefreshClick()
                        //TODO : Call viewmodel
                    }) {
                    Text(text = "Save")
                }
            }
        } else {
            Row(horizontalArrangement = Arrangement.Center) {
                Button(
                    modifier = Modifier.padding(top = 12.dp, end = 6.dp),
                    onClick = {
                        onAddClicked = !onAddClicked
                    }) {
                    Text(text = "Add")
                }

                Button(
                    modifier = Modifier.padding(top = 12.dp, start = 6.dp),
                    onClick = { onRefreshClick() }) {
                    Text(text = "Refresh")
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun listItemView() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        var showPassword by remember { mutableStateOf(false) }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "Realm",
            onValueChange = {},
            readOnly = true,
            trailingIcon = {

                val image = if (showPassword)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                Image(
                    imageVector = image,
                    contentDescription = "show password",
                    modifier = Modifier.clickable {
                        showPassword = !showPassword
                    }
                )
            },
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            label = { Text(text = "Website 1") }
        )
    }
}