package com.android.imageshowing.net

import com.android.imageshowing.data.model.api.Album
import com.android.imageshowing.data.model.api.Image
import com.android.imageshowing.data.model.api.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("/users/")
    fun getUsers(): Observable<List<User>>

    @GET("/albums/")
    fun getAlbums(@Query("userId") userId: Int): Observable<List<Album>>

    @GET("photos")
    fun getImages(@Query("albumId") albumId: Int): Observable<List<Image>>
}