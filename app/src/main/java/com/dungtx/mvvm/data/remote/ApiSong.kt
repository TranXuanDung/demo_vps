package com.dungtx.mvvm.data.remote

import com.dungtx.mvvm.data.model.ItemSong
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiSong {
    @GET("/jOut.ashx")
    fun getSongs(
            @Query(value = "k") name: String,
            @Query(value = "k") webName: String = "nhaccuatui.com",
            @Query(value = "code") code: String = "sdfsdf"
    ): Observable<MutableList<ItemSong>>

    @POST("/api/v1/update-user-profile")
    fun test(@Body body: RequestBody): Observable<String>
}