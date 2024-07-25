package networking

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import networking.dto.CensoredText
import utils.NetworkError
import utils.Result

class InsultSensorClient(
    private val httpClient: HttpClient
) {
    suspend fun censorInsults(stringToSensor: String): Result<String, NetworkError>
    {
        // wrap in try catch to handle exceptions
        val response = try {
            httpClient.get("https://www.purgomalum.com/service/json") {
                // give it the parameter and Ktor will construct the URL params
                // https://www.purgomalum.com/service/json?text=$stringToSensor
                parameter("text", stringToSensor)

                // can add headers like
                header("Content-Type", "application/json")
            }
        } catch (e: UnresolvedAddressException){
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        // get response from server and return either the result or the error enum
        return when (response.status.value) {
            in 200 .. 299 -> {
                val censoredText = response.body<CensoredText>()
                return Result.Success(censoredText.result)
            }
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            402 -> Result.Error(NetworkError.CONFLICT)
            403 -> Result.Error(NetworkError.TOO_MANY_REQUESTS)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }

        // post example
//        httpClient.post("https://www.purgomalum.com/service/json") {
//            // give it the parameter and Ktor will construct the URL params
//            // https://www.purgomalum.com/service/json?text=$stringToSensor
//            parameter("text", stringToSensor)
//            contentType(ContentType.Application.Json)
//            // can add headers like
//            header("Content-Type", "application/json")
//        }
    }
}