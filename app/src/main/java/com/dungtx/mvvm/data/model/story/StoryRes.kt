package com.dungtx.mvvm.data.model.story

import com.google.gson.annotations.SerializedName

class StoryRes(@SerializedName("stories") val stories: MutableList<StoryResponse>) {

}