package networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class CensoredText(
    val result: String
)