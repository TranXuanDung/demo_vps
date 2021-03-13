package com.dungtx.mvvm.data.model.asset

class ItemAsset() {

    var id: Int? = null
    var title: String = ""
    var content: Long = 0
    var status: Int? = null

    constructor(id: Int, title: String, content: Long, status: Int) : this() {
        this.id = id
        this.title = title
        this.content = content
        this.status = status
    }
}