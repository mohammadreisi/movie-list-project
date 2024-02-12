package ir.cafebazaar.filmbazar.data_source

import ir.cafebazaar.filmbazar.domain.DataState
import ir.cafebazaar.filmbazar.domain.MovieItem
import ir.cafebazaar.filmbazar.domain.Movies
import ir.cafebazaar.filmbazar.domain.repositories.LocalRepository
import kotlinx.coroutines.flow.Flow

class RealmRepositoryImpl: LocalRepository {
    override suspend fun readMovieList(): Flow<DataState<Movies>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertMovie(movieItem: MovieItem) {
        TODO("Not yet implemented")
    }
}