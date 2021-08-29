package com.sandeep.nspiretask.common

sealed class UiState {
    object  Idle: UiState()
    object  Loading : UiState()
    class Success<out T>(val result: T) : UiState()
    class Error(val errorcode: Int?, val errorMessage: String) : UiState()
}