package com.parksupark.paractice.feature.auth.data

import kotlinx.serialization.Serializable

@Serializable
data class SignupRequest(
    val username: String,
    val email: String,
    val rawPassword: String,
)
