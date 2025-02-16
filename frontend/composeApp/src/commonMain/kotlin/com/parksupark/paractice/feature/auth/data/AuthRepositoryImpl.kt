package com.parksupark.paractice.feature.auth.data

import com.parksupark.paractice.core.data.networking.post
import com.parksupark.paractice.core.domain.util.DataError
import com.parksupark.paractice.core.domain.util.EmptyResult
import com.parksupark.paractice.core.domain.util.asEmptyDataResult
import com.parksupark.paractice.feature.auth.domain.repository.AuthRepository
import io.ktor.client.HttpClient

class AuthRepositoryImpl(
    private val httpClient: HttpClient,
) : AuthRepository {
    override suspend fun login(
        email: String,
        password: String,
    ): EmptyResult<DataError.Network> {
        val result = httpClient.post<LoginRequest, Unit>(
            route = "/auth/log-in",
            body = LoginRequest(
                email = email,
                password = password,
            ),
        )
        return result.asEmptyDataResult()
    }
}
