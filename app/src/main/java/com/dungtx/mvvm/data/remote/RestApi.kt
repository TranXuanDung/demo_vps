package com.dungtx.mvvm.data.remote

import com.dungtx.mvvm.data.model.story.StoryRes
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET("api/v2/stories")
    fun getStories(@Query("page") page: Int?): Observable<StoryRes>
}