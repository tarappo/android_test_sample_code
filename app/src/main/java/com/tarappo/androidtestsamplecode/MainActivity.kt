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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tarappo.androidtestsamplecode.ui.theme.AndroidTestSampleCodeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidTestSampleCodeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CounterScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CounterScreen(modifier: Modifier = Modifier) {
    var currentCount by remember { mutableStateOf(0) }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Count: $currentCount")
        Button(onClick = { currentCount += 1 }, modifier = Modifier.padding(top = 16.dp)) {
            Text("Increment")
        }
        Button(onClick = { currentCount = 0 }, modifier = Modifier.padding(top = 8.dp)) {
            Text("Reset")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidTestSampleCodeTheme {
        CounterScreen()
    }
}