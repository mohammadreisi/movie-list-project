package ir.cafebazaar.filmbazar.data_source

import ir.cafebazaar.filmbazar.Constants
import ir.cafebazaar.filmbazar.domain.DataState
import ir.cafebazaar.filmbazar.domain.Movies
import ir.cafebazaar.filmbazar.domain.remote_models.toDomain
import ir.cafebazaar.filmbazar.domain.repositories.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(val retrofitApiService: RetrofitApiService) :
    RemoteRepository {
    override suspend fun getMovieList(pageNumber: Int): Flow<DataState<Movies>> = flow {
        withContext(Dispatchers.IO) {
            val remoteResponse =
                retrofitApiService.getMovies(Constants.API_KEY, Constants.LANGUAGE, pageNumber)
            withContext(Dispatchers.Main.immediate) {
                if (remoteResponse.isSuccessful) {
                    if (remoteResponse.body() != null) {
                        emit(DataState.Success(remoteResponse.body()?.toDomain()!!))
                    }
                } else {
                    emit(DataState.Error("Network error"))
                }
            }
        }
    }

    override suspend fun getMoviePoster(pageNumber: Int): Flow<DataState<String>> {
        TODO("Not yet implemented")
    }
}