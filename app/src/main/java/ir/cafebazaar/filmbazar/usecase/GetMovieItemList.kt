package ir.cafebazaar.filmbazar.usecase

import ir.cafebazaar.filmbazar.domain.DataState
import ir.cafebazaar.filmbazar.domain.MovieItem
import ir.cafebazaar.filmbazar.domain.repositories.LocalRepository
import ir.cafebazaar.filmbazar.domain.repositories.RemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieItemList @Inject constructor(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) {
    operator fun invoke(pageNumber: Int): Flow<DataState<List<MovieItem>>> = flow {
        remoteRepository.getMovieList(pageNumber).collect { remoteResponse ->
            when (remoteResponse) {
                is DataState.Success -> {
                    localRepository.insertMovies(remoteResponse.data)
                    emit(localRepository.readMovieList(pageNumber).first())
                }

                is DataState.Error -> {
                    emit(localRepository.readMovieList(pageNumber).first())
                }
                else -> {}
            }
        }
    }
}