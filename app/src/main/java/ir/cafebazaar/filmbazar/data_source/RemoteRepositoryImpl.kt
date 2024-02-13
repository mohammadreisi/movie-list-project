package ir.cafebazaar.filmbazar.data_source

import ir.cafebazaar.filmbazar.Constants
import ir.cafebazaar.filmbazar.domain.DataState
import ir.cafebazaar.filmbazar.domain.Movies
import ir.cafebazaar.filmbazar.domain.remote_models.toDomain
import ir.cafebazaar.filmbazar.domain.repositories.RemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(val retrofitApiService: RetrofitApiService) :
    RemoteRepository {
    override suspend fun getMovieList(pageNumber: Int): Flow<DataState<Movies>> = flow {
        retrofitApiService.getMovies(Constants.API_KEY, pageNumber).collect { remoteMovies ->
            emit(DataState.Success(remoteMovies.toDomain()))
        }
    }
}