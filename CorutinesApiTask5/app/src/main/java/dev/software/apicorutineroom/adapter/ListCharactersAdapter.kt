package dev.software.apicorutineroom.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.software.apicorutineroom.databinding.ItemCharacterBinding
import dev.software.apicorutineroom.databinding.ItemLoadingBinding
import dev.software.apicorutineroom.model.Characters
import dev.software.apicorutineroom.model.PagingData



class ListCharactersAdapter(
    context: Context,
    private val onCharacterClicked: (Characters) -> Unit
) : ListAdapter<PagingData<Characters>, RecyclerView.ViewHolder>(DIFF_UTIL) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is PagingData.Content -> TYPE_CHARACTERS
            PagingData.Loading -> TYPE_LOADING
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_CHARACTERS -> {
                CharacterViewHolder(
                    binding = ItemCharacterBinding.inflate(layoutInflater, parent, false),
                    onCharacterClicked = onCharacterClicked
                )
            }
            TYPE_LOADING -> {
                LoadingViewHolder (
                    binding = ItemLoadingBinding.inflate(layoutInflater, parent, false)
                )
            }
            else -> error("Incorrect view type - $viewType")
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val characterViewHolder = holder as? CharacterViewHolder ?: return
        val item = (getItem(position) as? PagingData.Content)?.data ?: return
        characterViewHolder.bind(item)
    }



    companion object {

        private const val TYPE_CHARACTERS = 0
        private const val TYPE_LOADING = 1

        private var DIFF_UTIL = object : DiffUtil.ItemCallback<PagingData<Characters>>() {

            override fun areItemsTheSame(
                oldItem: PagingData<Characters>,
                newItem: PagingData<Characters>
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: PagingData<Characters>,
                newItem: PagingData<Characters>
            ): Boolean {
                val oldCharacter = oldItem as PagingData.Content
                val newCharacter = newItem as PagingData.Content
                return oldCharacter?.data == newCharacter?.data
            }
        }
    }
}
