package ir.cafebazaar.filmbazar.domain.local_models

import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import ir.cafebazaar.filmbazar.domain.MovieResult

open class RealmMovieResult : RealmObject {
    @PrimaryKey
    var id: Long? = null
    var adult: Boolean? = null
    var backdrop_path: String? = null
    var genre_ids: RealmList<Int>? = null
    var original_language: String? = null
    var original_title: String? = null
    var overview: String? = null
    var popularity: Double? = null
    var poster_path: String? = null
    var release_date: String? = null
    var title: String? = null
    var video: Boolean? = null
    var vote_average: Float? = null
    var vote_count: Int? = null
}

fun RealmMovieResult.toDomain(): MovieResult {
    return MovieResult(
        this.adult ?: false,
        this.backdrop_path ?: "",
        this.genre_ids?.toList()!!,
        this.id ?: 0L,
        this.original_language ?: "",
        this.original_title ?: "",
        this.overview ?: "",
        this.popularity ?: 0.0,
        this.poster_path ?: "",
        this.release_date ?: "",
        this.title ?: "",
        this.video ?: false,
        this.vote_average ?: 0f,
        this.vote_count ?: 0
    )
}