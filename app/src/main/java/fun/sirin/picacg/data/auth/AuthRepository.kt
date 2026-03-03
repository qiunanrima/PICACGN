package fun.sirin.picacg.data.auth

import fun.sirin.picacg.core.network.ApiResult
import fun.sirin.picacg.core.network.LoginRequest
import fun.sirin.picacg.core.network.PicacgApiService

class AuthRepository(
    private val apiService: PicacgApiService,
    private val tokenStore: AuthTokenStore
) {
    suspend fun login(email: String, password: String): ApiResult<Unit> {
        return runCatching {
            val response = apiService.signIn(LoginRequest(email = email, password = password))
            val token = response.data?.token
            if (token.isNullOrBlank()) {
                ApiResult.Error(response.message ?: "登录失败", response.code)
            } else {
                tokenStore.saveToken(token)
                ApiResult.Success(Unit)
            }
        }.getOrElse { throwable ->
            ApiResult.Error(message = throwable.message ?: "网络错误", cause = throwable)
        }
    }
}
