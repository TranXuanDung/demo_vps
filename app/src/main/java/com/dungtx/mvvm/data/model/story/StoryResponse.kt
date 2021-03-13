package com.dungtx.mvvm.data.model.story

import com.dungtx.mvvm.data.model.story.StoryLinks
import com.google.gson.annotations.SerializedName
import java.util.*


class StoryResponse(var type: String?) {
    @SerializedName("id") val id: Long? = null
    @SerializedName("title") val title: String? = null
    @SerializedName("url") val url: String? = null
    @SerializedName("comment") val comment: String? = null
    @SerializedName("comment_html") val comment_html: String? = null
    @SerializedName("comment_count") val comment_count: Int = 0
    @SerializedName("vote_count") val vote_count: Int = 0
    @SerializedName("created_at") val created_at: Date? = null
    @SerializedName("links") val links: StoryLinks? = null
}




