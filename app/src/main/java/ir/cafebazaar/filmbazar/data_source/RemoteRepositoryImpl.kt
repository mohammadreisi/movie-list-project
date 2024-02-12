package ir.cafebazaar.filmbazar.data_source

import ir.cafebazaar.filmbazar.domain.DataState
import ir.cafebazaar.filmbazar.domain.Movies
import ir.cafebazaar.filmbazar.domain.repositories.RemoteRepository
import kotlinx.coroutines.flow.Flow

class RemoteRepositoryImpl: RemoteRepository {
    override suspend fun getMovieList(pageNumber: Int): Flow<DataState<Movies>> {
        TODO("Not yet implemented")
    }
}