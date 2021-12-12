package com.example.core

sealed class Resource<out T> {
    object Loading : Resource<Nothing>()

    data class Success<out T>(
        val value: T
    ) : Resource<T>()

    data class Fail(
        val error: Throwable
    ) : Resource<Nothing>()

    val isLoading get() = this is Loading
    val isFail get() = this is Fail
    val valueOrNull get() = (this as? Success)?.value
}