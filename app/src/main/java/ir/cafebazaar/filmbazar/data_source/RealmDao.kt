package ir.cafebazaar.filmbazar.data_source

import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import ir.cafebazaar.filmbazar.domain.local_models.RealmMovieResult
import ir.cafebazaar.filmbazar.domain.local_models.RealmMovies
import javax.inject.Inject

/**A intermediary class for accessibility to realm database transaction
 *
 * params:
 *          realm: An instance of realm database for transaction that provides
 *          with dagger hilt*/
class RealmDao @Inject constructor(val realm: Realm) {

    suspend fun insertMove(realmMovies: RealmMovies) {
        realm.writeBlocking {
            copyToRealm(realmMovies)
        }
    }

    suspend fun readMovies(pageNumber: Int): RealmMovies? {
        val realmResult = realm.query(RealmMovies::class,"page == $0", pageNumber).find()
        return realmResult.first()
    }

    suspend fun readAllMovieList(): RealmResults<RealmMovieResult> {
        return realm.query<RealmMovieResult>().find()
    }
}