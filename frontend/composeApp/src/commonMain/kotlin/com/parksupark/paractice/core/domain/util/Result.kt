package com.parksupark.paractice.core.domain.util

import com.parksupark.paractice.core.domain.util.Error as DomainError

sealed interface Result<out D, out E : DomainError> {
    data class Success<out D>(
        val data: D,
    ) : Result<D, Nothing>

    data class Error<out E : DomainError>(
        val error: E,
    ) : Result<Nothing, E>
}

inline fun <T, E : DomainError, R> Result<T, E>.map(map: (T) -> R): Result<R, E> {
    return when (this) {
        is Result.Error -> Result.Error(error)
        is Result.Success -> Result.Success(map(data))
    }
}

fun <T, E : DomainError> Result<T, E>.asEmptyDataResult(): EmptyResult<E> {
    return map { }
}

typealias EmptyResult<E> = Result<Unit, E>
