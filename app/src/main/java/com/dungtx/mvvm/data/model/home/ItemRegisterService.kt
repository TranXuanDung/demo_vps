package com.dungtx.mvvm.data.model.home

class ItemRegisterService() {

    var id: Int? = null
    var title: String = ""
    var image: Int? = null

    constructor(id: Int, title: String, image: Int) : this() {
        this.id = id
        this.title = title
        this.image = image
    }
}