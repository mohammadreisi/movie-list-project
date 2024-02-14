package ir.cafebazaar.filmbazar.data_source

import ir.cafebazaar.filmbazar.domain.remote_models.RemoteMovies
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**Basic interface Class for retrofit methods*/
interface RetrofitApiService {

    @GET("3/movie/upcoming/")
    suspend fun getMovies(
        @Header("Authorization") apiKey: String,
        @Query("language") language: String,
        @Query("page") pageNumber: Int
    ): Response<RemoteMovies>
}