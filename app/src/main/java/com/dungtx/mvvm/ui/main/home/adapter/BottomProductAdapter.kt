package com.dungtx.mvvm.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dungtx.mvvm.data.model.asset.ItemProduct
import com.dungtx.mvvm.databinding.ItemProductBinding
import java.util.*

class BottomProductAdapter : RecyclerView.Adapter<BottomProductAdapter.Companion.ProductViewHolder> {

    private var itemProduct: MutableList<ItemProduct> = mutableListOf()

    constructor(itemProduct: MutableList<ItemProduct>) {
        this.itemProduct = itemProduct
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
        )
        return ProductViewHolder(binding)
    }

    override fun getItemCount() = itemProduct.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val data = itemProduct[position]
        holder.binding.tvTitle.text = data.title
        data.image?.let {
            holder.binding.ivStatus.setImageResource(data.image!!)
        }
    }

    fun onMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(itemProduct, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(itemProduct, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    fun getDataProducts(): MutableList<ItemProduct>{
        return itemProduct
    }

    companion object {
        class ProductViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)
    }
}