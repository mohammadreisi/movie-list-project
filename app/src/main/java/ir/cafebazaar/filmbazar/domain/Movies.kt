package ir.cafebazaar.filmbazar.domain

import io.realm.RealmList
import ir.cafebazaar.filmbazar.domain.local_models.RealmMovieResult
import ir.cafebazaar.filmbazar.domain.local_models.RealmMovies
import java.util.Random

data class Movies(
    val dates: Dates,
    val page: Int,
    val results: List<MovieItem>,
    val total_pages: Int,
    val total_results: Int
)

fun Movies.toRealm(): RealmMovies {
    val movieResultsRealmList = RealmList<RealmMovieResult>()
    results.forEach {
        movieResultsRealmList.add(it.toRealm())
    }

    return RealmMovies().also {
        it.id = Random().nextLong()
        it.dates = dates.toRealmDates()
        it.page = page
        it.results = movieResultsRealmList
        it.total_pages = total_pages
        it.total_results = total_results
    }
}
