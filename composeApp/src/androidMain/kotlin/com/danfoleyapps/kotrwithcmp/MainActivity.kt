package com.danfoleyapps.kotrwithcmp

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import io.ktor.client.engine.okhttp.OkHttp
import networking.InsultSensorClient
import networking.createHttpClient

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App(
                // use remember to create the the client like so..
                client = remember {
                    InsultSensorClient(createHttpClient(OkHttp.create()))
                }
            )
        }
    }
}

