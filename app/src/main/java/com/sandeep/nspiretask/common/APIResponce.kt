package com.sandeep.nspiretask.common


sealed class APIResponce<out T : Any> {
    data class Success<T : Any>(val items: T?): APIResponce<T>()
    data class Error<T : Any>(val errorcode: Int?, val errorMessage: String): APIResponce<Nothing>()
    data class Exception(val exception: kotlin.Exception?): APIResponce<Nothing>()
}