package com.dungtx.mvvm.data.model.story

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StoryLinks(
    @SerializedName("user") val user: Long,
    @SerializedName("comments") val comments: List<Long>,
    @SerializedName("upvotes") val upvotes: List<Long>,
    @SerializedName("downvotes") val downvotes: List<Long>
) : Parcelable
