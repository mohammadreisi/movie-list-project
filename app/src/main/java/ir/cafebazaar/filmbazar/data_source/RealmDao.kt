package ir.cafebazaar.filmbazar.data_source

import io.realm.Realm
import io.realm.RealmList
import ir.cafebazaar.filmbazar.domain.local_models.RealmDates
import ir.cafebazaar.filmbazar.domain.local_models.RealmMovieResult
import ir.cafebazaar.filmbazar.domain.local_models.RealmMovies
import javax.inject.Inject

/**A intermediary class for accessibility to realm database transaction
 *
 * params:
 *          realm: An instance of realm database for transaction that provides
 *          with dagger hilt*/
class RealmDao @Inject constructor(val realm: Realm) {

    suspend fun insertMovies(inputRealmMovies: RealmMovies) {
        var realmMovies: RealmMovies?
        realm.beginTransaction()
        realmMovies =
            realm.where(RealmMovies::class.java)?.equalTo("page", inputRealmMovies.page)
                ?.findFirst()

        if (realmMovies == null) {
            realmMovies =
                realm.createObject(RealmMovies::class.java, inputRealmMovies.id)
        }
        val movieResultRealmList: RealmList<RealmMovieResult> = RealmList()
        inputRealmMovies.results?.forEach {
            movieResultRealmList.add(insertMovieItem(it))
        }
        realmMovies?.let {
            it.dates = insertDates(inputRealmMovies.dates)
            it.page = inputRealmMovies.page
            it.results = movieResultRealmList
            it.total_pages = inputRealmMovies.total_pages
            it.total_results = inputRealmMovies.total_results
        }
        realm.commitTransaction()
    }

    private fun insertDates(inputRealmDates: RealmDates?): RealmDates? {
//            realm.where(RealmDates::class.java)?.equalTo("page", inputRealmMovies.page)
//                ?.findFirst()

//        if (realmMovies == null) {
//    }
        val realmDates: RealmDates? = realm.createObject(RealmDates::class.java, inputRealmDates?.id)
        realmDates?.let {
            it.maximum = inputRealmDates?.maximum
            it.minimum = inputRealmDates?.minimum
        }
//        realm.commitTransaction()
        return realmDates
    }

    fun insertMovieItem(inputRealmMovieResult: RealmMovieResult?): RealmMovieResult?{
        val realmMovieResult: RealmMovieResult? = realm.createObject(RealmMovieResult::class.java, inputRealmMovieResult?.id)

        realmMovieResult?.let {
            it.movieId = inputRealmMovieResult?.movieId
            it.adult = inputRealmMovieResult?.adult
            it.backdrop_path = inputRealmMovieResult?.backdrop_path
            it.genre_ids = inputRealmMovieResult?.genre_ids!!
            it.original_language = inputRealmMovieResult.original_language
            it.original_title = inputRealmMovieResult.original_title
            it.overview = inputRealmMovieResult.overview
            it.popularity = inputRealmMovieResult.popularity
            it.poster_path = inputRealmMovieResult.poster_path
            it.release_date = inputRealmMovieResult.release_date
            it.title = inputRealmMovieResult.title
            it.video = inputRealmMovieResult.video
            it.vote_average = inputRealmMovieResult.vote_average
            it.vote_count = inputRealmMovieResult.vote_count
        }
//        realm.commitTransaction()
        return realmMovieResult
    }

    suspend fun readMovies(pageNumber: Int): RealmMovies? {
        val readObject = realm.where(RealmMovies::class.java)
            .equalTo("page", pageNumber)
            .findFirst()
        return realm.copyFromRealm(readObject)
    }
}