package com.parksupark.paractice.feature.auth.domain.usecase

import com.parksupark.paractice.core.domain.util.DataError
import com.parksupark.paractice.core.domain.util.EmptyResult
import com.parksupark.paractice.feature.auth.domain.repository.AuthRepository

class SignupUseCase(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(
        username: String,
        email: String,
        password: String,
    ): EmptyResult<DataError.Network> {
        return authRepository.signup(username, email, password)
    }
}
