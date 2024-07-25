import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import kotrwithcmp.composeapp.generated.resources.Res
import kotrwithcmp.composeapp.generated.resources.compose_multiplatform
import networking.InsultSensorClient
import utils.NetworkError
import utils.onError
import utils.onSuccess

@Composable
@Preview
fun App(client: InsultSensorClient) {
    MaterialTheme {

        // create state variables in UI for example.
        var censoredText by remember { mutableStateOf<String?>(null) }
        var textToCensor by remember { mutableStateOf("") }
        var isLoading by remember { mutableStateOf(false) }
        var error by remember { mutableStateOf<NetworkError?>(null) }
        val coroutineScope = rememberCoroutineScope()

        Column(Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {

            TextField(
                value = textToCensor,
                onValueChange = {textToCensor = it},
                modifier = Modifier.padding(16.dp),
                placeholder ={ Text("What do you want to censor?") }
            )
            Button(onClick = {
                coroutineScope.launch {
                    isLoading = true
                    error = null

                    client.censorInsults(textToCensor)
                        .onSuccess {
                            censoredText = it
                        }
                        .onError {
                            error = it
                        }
                    isLoading = false

                }
            }) {
                if(isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(15.dp),
                        strokeWidth = 1.dp,
                        color = Color.White
                    )
                } else {
                    Text("Click me!")
                }
            }
            censoredText?.let {
                Text(it)
            }
            error?.let {
                Text(text = it.name, color = Color.Red)
            }
        }
    }
}