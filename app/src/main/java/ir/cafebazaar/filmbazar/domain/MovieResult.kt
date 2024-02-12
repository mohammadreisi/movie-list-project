package ir.cafebazaar.filmbazar.domain

import ir.cafebazaar.filmbazar.domain.local_models.RealmMovieResult

data class MovieResult(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Long,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Float,
    val vote_count: Int
)

fun MovieResult.toRealm(): RealmMovieResult{
    return RealmMovieResult().also {
        it.adult = adult
        it.backdrop_path = backdrop_path
        it.genre_ids = genre_ids
        it.id = id
        it.original_language = original_language
        it.original_title = original_title
        it.overview = overview
        it.popularity = popularity
        it.poster_path = poster_path
        it.release_date = release_date
        it.title = title
        it.video = video
        it.vote_average = vote_average
        it.vote_count = vote_count
    }
}
