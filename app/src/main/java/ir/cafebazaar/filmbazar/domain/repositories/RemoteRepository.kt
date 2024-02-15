package ir.cafebazaar.filmbazar.domain.repositories

import ir.cafebazaar.filmbazar.domain.DataState
import ir.cafebazaar.filmbazar.domain.Movies
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {
    suspend fun getMovieList(pageNumber: Int): Flow<DataState<Movies>>
    suspend fun getMoviePoster(pageNumber: Int): Flow<DataState<String>>
}