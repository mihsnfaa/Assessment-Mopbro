package org.d3if0088.miniproject1.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.d3if0088.miniproject1.model.Comic
import org.d3if0088.miniproject1.model.OpStatus
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

private const val BASE_URL = "https://rest-api-comic-mihsnfaaa.000webhostapp.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
interface ComicApiService {
    @GET("/api/comic.php")
    suspend fun getComic(
        @Header("Authorization") userId: String
    ): List<Comic>

    @Multipart
    @POST("/api/comic.php")
    suspend fun postComic(
        @Header("Authorization") userId: String,
        @Part("title") title: RequestBody,
        @Part("genre") genre: RequestBody,
        @Part image: MultipartBody.Part
    ): OpStatus


    @FormUrlEncoded
    @POST("api/deleteComic.php")
    suspend fun deleteComic(
        @Header("Authorization") userId: String,
        @Field("id") comicId: String
    ): OpStatus
}

object ComicApi{
    val service: ComicApiService by lazy {
        retrofit.create(ComicApiService::class.java)
    }
    fun getComicUrl(imageId: String): String {
        return "${BASE_URL}api/image.php?id=$imageId"}
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }