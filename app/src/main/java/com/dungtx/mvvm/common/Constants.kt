package com.dungtx.mvvm.common

import java.util.*

object Constants {
    const val DEBUG = true
//    const val BASE_URL = "https://www.designernews.co/"
    const val BASE_URL = "http://j.ginggong.com/"
    const val FORMAT_DATE = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    const val FORMAT_DATE_STORY = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val CODE_TOKEN_EXPIRE = 401
    const val DURATION_ANIMATION = 500
    val LIST_FORMAT_TIME: List<String> = Arrays.asList(FORMAT_DATE_STORY)

    const val AUTHORIZATION = "Authorization"
    const val BEARER = "Bearer "
}