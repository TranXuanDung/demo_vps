package com.dungtx.mvvm.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dungtx.mvvm.data.model.asset.ItemProduct
import com.dungtx.mvvm.databinding.ItemHomeProductBinding

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.Companion.ProductViewHolder> {

    private var itemProduct: MutableList<ItemProduct> = mutableListOf()
    private var interf: IAssetAdapter? = null

    constructor(itemProduct: MutableList<ItemProduct>, interf: IAssetAdapter) {
        this.itemProduct = itemProduct
        this.interf = interf
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemHomeProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
        )
        return ProductViewHolder(binding)
    }

    override fun getItemCount() = itemProduct.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val data = itemProduct[position]
        holder.binding.tvProduct.text = data.title
        data.image?.let {
            holder.binding.ivProduct.setImageResource(data.image!!)
        }

        holder.itemView.setOnClickListener {
            interf!!.onClickItemProduct(position)
        }
    }

    companion object {
        class ProductViewHolder(val binding: ItemHomeProductBinding) : RecyclerView.ViewHolder(binding.root)
    }

    interface IAssetAdapter {
        fun onClickItemProduct(position: Int)
    }
}