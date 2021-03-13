package com.dungtx.mvvm.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dungtx.mvvm.data.model.home.ItemRegisterService
import com.dungtx.mvvm.databinding.ItemRegisterServiceBinding

class RegisterServiceAdapter : RecyclerView.Adapter<RegisterServiceAdapter.Companion.RegisterServiceViewHolder> {

    private var itemRegisterService: MutableList<ItemRegisterService> = mutableListOf()

    constructor(itemRegisterService: MutableList<ItemRegisterService>) {
        this.itemRegisterService = itemRegisterService
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegisterServiceViewHolder {
        val binding = ItemRegisterServiceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
        )
        return RegisterServiceViewHolder(binding)
    }

    override fun getItemCount() = itemRegisterService.size

    override fun onBindViewHolder(holder: RegisterServiceViewHolder, position: Int) {
        val data = itemRegisterService[position]
        data.image?.let {
            holder.binding.ivRegisterService.setImageResource(data.image!!)
        }
        holder.binding.tvRegisterService.text = data.title
    }
    companion object {
        class RegisterServiceViewHolder(val binding: ItemRegisterServiceBinding) : RecyclerView.ViewHolder(binding.root)
    }
}