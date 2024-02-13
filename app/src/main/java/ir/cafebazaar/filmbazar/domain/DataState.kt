package ir.cafebazaar.filmbazar.domain

sealed class DataState<T> {
    data class Success<T>(val data: T) : DataState<T>()
    data class Error<T>(val errorMessage: String) : DataState<T>()
    class Loading<T>: DataState<T>()
}
