package com.tarappo.androidtestsamplecode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.tarappo.androidtestsamplecode.ui.theme.AndroidTestSampleCodeTheme

private enum class AppScreen { Main, Detail }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidTestSampleCodeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var currentCount by rememberSaveable { mutableStateOf(0) }
                    var currentScreen by remember { mutableStateOf(AppScreen.Main) }
                    var isLoggedIn by rememberSaveable { mutableStateOf(false) }

                    when (currentScreen) {
                        AppScreen.Main -> CounterScreen(
                            count = currentCount,
                            onIncrement = { currentCount += if (isLoggedIn) 2 else 1 },
                            onNavigateToDetail = { currentScreen = AppScreen.Detail },
                            isLoggedIn = isLoggedIn,
                            onLoginSuccess = { isLoggedIn = true },
                            onLogout = { isLoggedIn = false },
                            modifier = Modifier.padding(innerPadding)
                        )
                        AppScreen.Detail -> DetailScreen(
                            count = currentCount,
                            onReset = { currentCount = 0 },
                            onBack = { currentScreen = AppScreen.Main },
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CounterScreen(
    count: Int,
    onIncrement: () -> Unit,
    onNavigateToDetail: () -> Unit,
    isLoggedIn: Boolean,
    onLoginSuccess: () -> Unit,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LoginSection(
            isLoggedIn = isLoggedIn,
            onLoginSuccess = onLoginSuccess,
            onLogout = onLogout
        )
        
        Text(text = "Count: $count")
        Button(onClick = onIncrement, modifier = Modifier.padding(top = 16.dp)) {
            Text("Increment")
        }
        Button(onClick = onNavigateToDetail, modifier = Modifier.padding(top = 8.dp)) {
            Text("Go to next screen")
        }
    }
}

@Composable
fun LoginSection(
    isLoggedIn: Boolean,
    onLoginSuccess: () -> Unit,
    onLogout: () -> Unit
) {
    var userId by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var showSuccessDialog by remember { mutableStateOf(false) }
    var showFailureDialog by remember { mutableStateOf(false) }

    if (!isLoggedIn) {
        OutlinedTextField(
            value = userId,
            onValueChange = { userId = it },
            label = { Text("UserID") },
            singleLine = true
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.padding(top = 8.dp)
        )
        Button(
            onClick = {
                if (userId == "sample" && password == "sample") {
                    onLoginSuccess()
                    showSuccessDialog = true
                    userId = ""
                    password = ""
                } else {
                    showFailureDialog = true
                }
            },
            modifier = Modifier.padding(top = 12.dp)
        ) {
            Text("Login")
        }
    } else {
        Button(onClick = onLogout) {
            Text("Logout")
        }
    }

    if (showSuccessDialog) {
        AlertDialog(
            onDismissRequest = { showSuccessDialog = false },
            confirmButton = {
                Button(onClick = { showSuccessDialog = false }) { Text("OK") }
            },
            title = { Text("ログイン成功") },
            text = { Text("ログインに成功しました。") }
        )
    }

    if (showFailureDialog) {
        AlertDialog(
            onDismissRequest = { showFailureDialog = false },
            confirmButton = {
                Button(onClick = { showFailureDialog = false }) { Text("OK") }
            },
            title = { Text("ログイン失敗") },
            text = { Text("ログインに失敗しました。") }
        )
    }
}

@Composable
fun DetailScreen(
    count: Int,
    onReset: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Count: $count")
        Button(onClick = onReset, modifier = Modifier.padding(top = 16.dp)) {
            Text("Reset")
        }
        Button(onClick = onBack, modifier = Modifier.padding(top = 8.dp)) {
            Text("Back")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidTestSampleCodeTheme {
        CounterScreen(
            count = 0,
            onIncrement = {},
            onNavigateToDetail = {},
            isLoggedIn = false,
            onLoginSuccess = {},
            onLogout = {}
        )
    }
}