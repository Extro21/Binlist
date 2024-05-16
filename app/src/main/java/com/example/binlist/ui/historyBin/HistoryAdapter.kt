package com.example.binlist.ui.historyBin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.binlist.R
import com.example.binlist.databinding.HistoryItemBinding
import com.example.binlist.domain.models.BinInfoItem

class HistoryAdapter :
    ListAdapter<BinInfoItem, HistoryAdapter.ItemHistoryHolder>(ItemComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHistoryHolder {
        return ItemHistoryHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemHistoryHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemHistoryHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = HistoryItemBinding.bind(view)

        fun bind(binInfo: BinInfoItem) = with(binding) {
            nameBank.text = binInfo.bankName
            phoneBank.text = binInfo.bankPhone
            emailBank.text = binInfo.bankUrl
            scheme.text = binInfo.scheme
            latitudeLongitudeSt.text = itemView.context.getString(
                R.string.coordinates,
                binInfo.latitude,
                binInfo.longitude
            )
            country.text = binInfo.countryName
        }

        companion object {

            fun create(parent: ViewGroup): ItemHistoryHolder {
                return ItemHistoryHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.history_item, parent, false)
                )
            }

        }
    }

    class ItemComparator : DiffUtil.ItemCallback<BinInfoItem>() {
        override fun areItemsTheSame(oldItem: BinInfoItem, newItem: BinInfoItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BinInfoItem, newItem: BinInfoItem): Boolean {
            return oldItem == newItem
        }
    }

}