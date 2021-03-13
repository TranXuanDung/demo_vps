package com.dungtx.mvvm.data.model


import com.google.gson.annotations.SerializedName

data class ItemSong(
        @SerializedName("Id")
        var id: String = "",

        var keySearch: String = "",

        @SerializedName("Title")
        var title: String? = null,

        @SerializedName("Avatar")
        var avatar: String? = null,

        @SerializedName("UrlJunDownload")
        var urlJunDownload: String? = null,

        @SerializedName("LyricsUrl")
        var lyricsUrl: String? = null,

        @SerializedName("UrlSource")
        var urlSource: String? = null,

        @SerializedName("SiteId")
        var siteId: String? = null,

        @SerializedName("Artist")
        var artist: String? = null
) {
}