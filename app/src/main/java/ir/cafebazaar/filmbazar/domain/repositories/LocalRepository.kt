package ir.cafebazaar.filmbazar.domain.repositories

import ir.cafebazaar.filmbazar.domain.DataState
import ir.cafebazaar.filmbazar.domain.MovieItem
import ir.cafebazaar.filmbazar.domain.Movies
import kotlinx.coroutines.flow.*

interface LocalRepository {
    suspend fun readMovieList(pageNumber: Int): Flow<DataState<List<MovieItem>>>
    suspend fun insertMovies(movies: Movies)
}