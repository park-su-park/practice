package com.parksupark.paractice

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform