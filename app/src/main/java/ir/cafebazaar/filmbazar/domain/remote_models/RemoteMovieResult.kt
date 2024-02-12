package ir.cafebazaar.filmbazar.domain.remote_models

import ir.cafebazaar.filmbazar.domain.MovieResult
import ir.cafebazaar.filmbazar.domain.local_models.toDomain

data class RemoteMovieResult(
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

fun RemoteMovieResult.toDomain(): MovieResult {
    return MovieResult(
        this.adult,
        this.backdrop_path,
        this.genre_ids,
        this.id,
        this.original_language,
        this.original_title,
        this.overview,
        this.popularity,
        this.poster_path,
        this.release_date,
        this.title,
        this.video,
        this.vote_average,
        this.vote_count
    )
}
