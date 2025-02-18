package com.parksupark.paractice.feature.auth.domain.repository

import com.parksupark.paractice.core.domain.util.DataError
import com.parksupark.paractice.core.domain.util.EmptyResult

interface AuthRepository {
    suspend fun login(
        email: String,
        password: String,
    ): EmptyResult<DataError.Network>

    suspend fun signup(
        username: String,
        email: String,
        password: String,
    ): EmptyResult<DataError.Network>
}
