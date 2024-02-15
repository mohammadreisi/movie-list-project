package ir.cafebazaar.filmbazar.domain

import io.realm.RealmList
import ir.cafebazaar.filmbazar.domain.local_models.RealmMovieResult
import java.util.Random

data class MovieItem(
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

fun MovieItem.toRealm(): RealmMovieResult {
    val genreIdRealmList = RealmList<Int>()
    genre_ids.forEach {
        genreIdRealmList.add(it)
    }
    return RealmMovieResult().also {
        it.id = Random().nextLong()
        it.movieId = id
        it.adult = adult
        it.backdrop_path = backdrop_path
        it.genre_ids = genreIdRealmList
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
