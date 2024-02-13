package ir.cafebazaar.filmbazar.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ir.cafebazaar.filmbazar.data_source.RealmDao
import ir.cafebazaar.filmbazar.data_source.RealmRepositoryImpl
import ir.cafebazaar.filmbazar.data_source.RemoteRepositoryImpl
import ir.cafebazaar.filmbazar.data_source.RetrofitApiService
import ir.cafebazaar.filmbazar.domain.repositories.LocalRepository
import ir.cafebazaar.filmbazar.domain.repositories.RemoteRepository
import ir.cafebazaar.filmbazar.usecase.GetMovieItemList

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideRemoteRepository(retrofitApiService: RetrofitApiService): RemoteRepository {
        return RemoteRepositoryImpl(retrofitApiService)
    }

    @Provides
    @ViewModelScoped
    fun provideLocalRepository(realmDao: RealmDao): LocalRepository {
        return RealmRepositoryImpl(realmDao)
    }

    @Provides
    @ViewModelScoped
    fun provideGetMovieItemList(
        localRepository: LocalRepository,
        remoteRepository: RemoteRepository
    ): GetMovieItemList {
        return GetMovieItemList(localRepository, remoteRepository)
    }
}