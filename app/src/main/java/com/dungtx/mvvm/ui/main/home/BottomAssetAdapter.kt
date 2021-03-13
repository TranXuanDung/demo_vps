package com.dungtx.mvvm.ui.main.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dungtx.mvvm.R
import com.dungtx.mvvm.data.model.asset.ItemAsset
import com.dungtx.mvvm.data.model.asset.StatusType
import com.dungtx.mvvm.databinding.ItemAssetBinding
import java.text.DecimalFormat
import java.text.NumberFormat

class BottomAssetAdapter : RecyclerView.Adapter<BottomAssetAdapter.Companion.AssetViewHolder> {

    private val inter: IAssetAdapter

    constructor(inter: IAssetAdapter) {
        this.inter = inter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetViewHolder {
        val binding = ItemAssetBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
        )
        return AssetViewHolder(binding)
    }

    override fun getItemCount() = inter.countNetAsset()

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: AssetViewHolder, position: Int) {
        val data = inter.getDataNetAsset(position)
        holder.binding.tvTitle.text = data.title
        val formatter: NumberFormat = DecimalFormat("#,###")
        data.content.let {
            val formattedNumber: String = formatter.format(data.content)
            holder.binding.tvContent.text = formattedNumber
        }

        data.status?.let {
            if (it == StatusType.UP.value){
                holder.binding.ivStatus.setImageResource(R.drawable.bg_asset_up)
                holder.binding.tvContent.setTextColor(holder.itemView.resources.getColor(R.color.text_color_blue))
            }else if (it == StatusType.DOWN.value){
                holder.binding.ivStatus.setImageResource(R.drawable.bg_asset_down)
                holder.binding.tvContent.setTextColor(holder.itemView.resources.getColor(R.color.text_color_red))
            }else if (it == StatusType.STAND.value){
                holder.binding.ivStatus.setImageResource(R.drawable.bg_asset_stand)
                holder.binding.tvContent.setTextColor(holder.itemView.resources.getColor(R.color.text_color_yellow))
            }else {
                holder.binding.ivStatus.setImageResource(R.drawable.bg_asset_default)
                holder.binding.tvTitle.setTextColor(holder.itemView.resources.getColor(R.color.text_color_gray))
                holder.binding.tvContent.setTextColor(holder.itemView.resources.getColor(R.color.text_color_gray))
            }
        }
    }

    companion object {
        class AssetViewHolder(val binding: ItemAssetBinding) : RecyclerView.ViewHolder(binding.root)
    }

    interface IAssetAdapter {
        fun countNetAsset(): Int
        fun getDataNetAsset(position: Int): ItemAsset
    }
}