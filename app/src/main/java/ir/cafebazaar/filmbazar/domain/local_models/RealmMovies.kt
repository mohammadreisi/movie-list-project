package ir.cafebazaar.filmbazar.domain.local_models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import ir.cafebazaar.filmbazar.domain.Movies

open class RealmMovies : RealmObject() {
    @PrimaryKey
    var id: Long? = null
    var dates: RealmDates? = null
    var page: Int? = null
    var results: RealmList<RealmMovieResult>? = null
    var total_pages: Int? = null
    var total_results: Int? = null
}

fun RealmMovies.toDomain(): Movies {
    return Movies(
        dates = dates?.toDomain()!!,
        page = page ?: 0,
        results = results?.toList()?.map {
            it.toDomain()
        }!!,
        total_pages = total_pages ?: 0,
        total_results = total_results ?: 0
    )
}