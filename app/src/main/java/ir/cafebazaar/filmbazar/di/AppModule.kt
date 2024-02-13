package ir.cafebazaar.filmbazar.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import ir.cafebazaar.filmbazar.data_source.RealmDao
import ir.cafebazaar.filmbazar.domain.local_models.RealmDates
import ir.cafebazaar.filmbazar.domain.local_models.RealmMovieResult
import ir.cafebazaar.filmbazar.domain.local_models.RealmMovies
import javax.inject.Scope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGlideInstance(
        @ApplicationContext context: Context
    ) = Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(32)))
            .diskCacheStrategy(DiskCacheStrategy.DATA)
    )

    @Singleton
    @Provides
    fun provideRealmDao(realm: Realm): RealmDao {
        return RealmDao(realm)
    }

    @Singleton
    @Provides
    fun provideRealm(): Realm {
        val config = RealmConfiguration.create(
            setOf(
                RealmDates::class,
                RealmMovies::class,
                RealmMovieResult::class
            )
        )
        return Realm.open(config)
    }

}