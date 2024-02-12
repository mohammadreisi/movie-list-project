package ir.cafebazaar.filmbazar.domain.remote_models

import ir.cafebazaar.filmbazar.domain.Movies

data class RemoteMovies(
    val dates: RemoteDates,
    val page: Int,
    val results: List<RemoteMovieResult>,
    val total_pages: Int,
    val total_results: Int
)

fun RemoteMovies.toDomain(): Movies {
    return Movies(
        dates = dates.toDomain(),
        page = page,
        results = results.map {
            it.toDomain()
        },
        total_pages = total_pages,
        total_results = total_results
    )
}
