package dev.software.db.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.software.db.databinding.ItemBinding
import dev.software.db.model.Song

class ListAdapter(
        private val onClicked: (item: Song) -> Unit
) : ListAdapter<Song, RecyclerView.ViewHolder>(DIFF_UTIL) {

    var removedItems = ArrayList<Song>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SongViewHolder(
                binding = ItemBinding.inflate(layoutInflater, parent, false),
                onClicked = onClicked
        )
    }

    fun removeItem(position: Int): Song? {
        if (position >= itemCount) return null
        val item = currentList[position]
        removedItems.add(item)
        val curList = currentList - removedItems
        if (curList.isEmpty()) removedItems.clear()
        submit(curList, true)
        return item
    }

    private fun submit(list: List<Song>?, isLocalSubmit: Boolean) {
        if (!isLocalSubmit) removedItems.clear()
        super.submitList(list)
    }

    override fun submitList(list: List<Song>?) {
        submit(list, false)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val songViewHolder = holder as? SongViewHolder ?: return
        val song = getItem(position) ?: return
        songViewHolder.bind(song)
    }


    companion object {
        private var DIFF_UTIL = object : DiffUtil.ItemCallback<Song>() {

            override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
                return oldItem == newItem
            }
        }
    }
}

class SongViewHolder(
        private val binding: ItemBinding,
        private val onClicked: (item: Song) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Song) {
        with(binding) {
            textFields.text = "Название песни: ${item.title}\nЖанр: ${item.genre}\nАвтор: ${item.author}"
            buttonChange.setOnClickListener {
                onClicked(item)
            }
        }
    }
}