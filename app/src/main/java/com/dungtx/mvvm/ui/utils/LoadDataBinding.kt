package com.dungtx.mvvm.ui.utils

import androidx.databinding.BindingAdapter
import androidx.appcompat.widget.AppCompatImageView
import com.dungtx.mvvm.R
import com.dungtx.mvvm.ui.customview.GlideApp

object LoadDataBinding {
    @BindingAdapter(value = ["bind:urlImage"], requireAll = false)
    fun AppCompatImageView.setUrlImage(urlImage:String?){
       if (!StringUtils.isBlank(urlImage)){
           GlideApp.with(this)
                   .load(urlImage)
                   .placeholder(R.drawable.ao_dai)
                   .error(R.drawable.ao_dai)
                   .into(this)
       }
    }
}