package com.dungtx.mvvm.data.model.home

import android.os.Parcel
import android.os.Parcelable

class Banner() : Parcelable {
    var id: Int? = null
    var image: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        image = parcel.readValue(String::class.java.classLoader) as? String
    }

    constructor(id: Int, image: String) : this() {
        this.id = id
        this.image = image
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest!!.writeInt(id!!)
        dest.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Banner> {
        override fun createFromParcel(parcel: Parcel): Banner {
            return Banner(parcel)
        }

        override fun newArray(size: Int): Array<Banner?> {
            return arrayOfNulls(size)
        }
    }
}