package dev.software.apicorutineroom.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.size.ViewSizeResolver
import dev.software.apicorutineroom.databinding.ItemCharacterBinding
import dev.software.apicorutineroom.model.Characters


class CharacterViewHolder(
    private val binding: ItemCharacterBinding,
    private val onCharacterClicked: (Characters) -> Unit
) : RecyclerView.ViewHolder(binding.root){

    fun bind(character: Characters) {
        with(binding) {
            picture.load(character.url){
                scale(Scale.FIT)
                size(ViewSizeResolver(root))
            }
            textName.text = character.name

            root.setOnClickListener{
                onCharacterClicked(character)
            }
        }
    }
}