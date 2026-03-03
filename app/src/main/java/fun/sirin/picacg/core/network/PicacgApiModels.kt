package fun.sirin.picacg.core.network

import com.squareup.moshi.Json

data class ApiEnvelope<T>(
    @Json(name = "code") val code: Int,
    @Json(name = "message") val message: String?,
    @Json(name = "data") val data: T?
)

data class LoginRequest(
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String
)

data class LoginResponseData(
    @Json(name = "token") val token: String
)
