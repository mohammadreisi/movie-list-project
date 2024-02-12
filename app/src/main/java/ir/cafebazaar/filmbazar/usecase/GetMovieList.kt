package ir.cafebazaar.filmbazar.usecase

import ir.cafebazaar.filmbazar.domain.DataState
import ir.cafebazaar.filmbazar.domain.Movies
import ir.cafebazaar.filmbazar.domain.repositories.LocalRepository
import ir.cafebazaar.filmbazar.domain.repositories.RemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieList @Inject constructor(
    val localRepository: LocalRepository,
    val remoteRepository: RemoteRepository
) {
    operator fun invoke(pageNumber: Int): Flow<DataState<Movies>> = flow {
        remoteRepository.getMovieList(pageNumber).collect { remoteResponse ->
            when (remoteResponse) {
                is DataState.Success -> {
                    remoteResponse.data.results.forEach { item ->
                        localRepository.insertMovie(item)
                    }
                    localRepository.readMovieList()
                }

                is DataState.Loading -> {

                }

                is DataState.Error -> {

                }
            }
        }
    }
}