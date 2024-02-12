package ir.cafebazaar.filmbazar.domain

import ir.cafebazaar.filmbazar.domain.local_models.RealmMovies

data class Movies(
    val dates: Dates,
    val page: Int,
    val results: List<MovieResult>,
    val total_pages: Int,
    val total_results: Int
)

fun Movies.toRealm(): RealmMovies {
    return RealmMovies().also {
        it.dates = dates.toRealmDates()
        it.page = page
        it.results = results.map { movieResult ->
            movieResult.toRealm()
        }
        it.total_pages = total_pages
        it.total_results = total_results
    }
}
