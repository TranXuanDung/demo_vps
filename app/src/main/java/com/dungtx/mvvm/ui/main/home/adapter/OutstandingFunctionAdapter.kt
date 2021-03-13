package com.dungtx.mvvm.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dungtx.mvvm.data.model.home.ItemOutstandingFunction
import com.dungtx.mvvm.databinding.ItemOutstandingFunctionBinding

class OutstandingFunctionAdapter : RecyclerView.Adapter<OutstandingFunctionAdapter.Companion.OutstandingFunctionViewHolder> {

    private var itemOutstandingFunction: MutableList<ItemOutstandingFunction> = mutableListOf()

    constructor(itemOutstandingFunction: MutableList<ItemOutstandingFunction>) {
        this.itemOutstandingFunction = itemOutstandingFunction
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OutstandingFunctionViewHolder {
        val binding = ItemOutstandingFunctionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
        )
        return OutstandingFunctionViewHolder(binding)
    }

    override fun getItemCount() = itemOutstandingFunction.size

    override fun onBindViewHolder(holder: OutstandingFunctionViewHolder, position: Int) {
        val data = itemOutstandingFunction[position]
        data.image?.let {
            holder.binding.ivOutstandingFunction.setImageResource(data.image!!)
        }
    }

    companion object {
        class OutstandingFunctionViewHolder(val binding: ItemOutstandingFunctionBinding) : RecyclerView.ViewHolder(binding.root)
    }

}