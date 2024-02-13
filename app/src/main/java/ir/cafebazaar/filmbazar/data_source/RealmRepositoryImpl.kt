package ir.cafebazaar.filmbazar.data_source

import ir.cafebazaar.filmbazar.domain.DataState
import ir.cafebazaar.filmbazar.domain.MovieItem
import ir.cafebazaar.filmbazar.domain.Movies
import ir.cafebazaar.filmbazar.domain.local_models.toDomain
import ir.cafebazaar.filmbazar.domain.repositories.LocalRepository
import ir.cafebazaar.filmbazar.domain.toRealm
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RealmRepositoryImpl @Inject constructor(private val realmDao: RealmDao) : LocalRepository {

    override suspend fun readAllMovieList(): Flow<DataState<List<MovieItem>>> = flow {
        val realmResults = realmDao.readAllMovieList()
        val movieItems = realmResults.map {
            it.toDomain()
        }
        if (movieItems.isNotEmpty()) {
            emit(DataState.Success(movieItems))
        } else {
            emit(DataState.Error("Not found"))
        }
    }

    override suspend fun readMovieList(pageNumber: Int): Flow<DataState<List<MovieItem>>> = flow {
        val realmResults = realmDao.readMovies(pageNumber)
        val movieItems = realmResults?.results?.map {
            it.toDomain()
        }
        if (!movieItems.isNullOrEmpty()) {
            emit(DataState.Success(movieItems))
        } else {
            emit(DataState.Error("Not found"))
        }
    }

    override suspend fun insertMovies(movies: Movies) {
        realmDao.insertMove(movies.toRealm())
    }
}