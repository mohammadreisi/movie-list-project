package ir.cafebazaar.filmbazar.domain

sealed class DataState<T> {
    data class Success<T>(val data: T) : DataState<T>()
    data class Error<T>(val errorMessage: String) : DataState<T>()
    data class Loading<T>(val loading: String) : DataState<T>()
}
