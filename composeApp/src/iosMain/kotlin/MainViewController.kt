import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import io.ktor.client.engine.darwin.Darwin
import networking.InsultSensorClient
import networking.createHttpClient

fun MainViewController() = ComposeUIViewController {
    App(
        remember {
            InsultSensorClient(createHttpClient(Darwin.create()))
        }
    )
}