package com.googlebooks.ca.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.googlebooks.ca.databinding.ItemBookBinding
import com.googlebooks.ca.model.Volume

class BookListAdapter(private val clickListener: VolumeListener) : ListAdapter<Volume,
        BookListAdapter.BookHolder>(VolumeCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        return BookHolder.from(parent)
    }

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        val volume = getItem(position)
        holder.bind(clickListener, volume)
    }

    class BookHolder private constructor(private val binding: ItemBookBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(clickListener: VolumeListener, volume: Volume) {
            binding.volume = volume
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): BookHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemBookBinding.inflate(layoutInflater, parent, false)
                return BookHolder(binding)
            }
        }
    }
}

class VolumeCallBack : DiffUtil.ItemCallback<Volume>() {
    override fun areItemsTheSame(oldItem: Volume, newItem: Volume): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Volume, newItem: Volume): Boolean {
        return oldItem == newItem
    }
}

class VolumeListener(val clickListener: (volume: Volume) -> Unit) {
    fun onClick(volume: Volume) = clickListener(volume)
}
