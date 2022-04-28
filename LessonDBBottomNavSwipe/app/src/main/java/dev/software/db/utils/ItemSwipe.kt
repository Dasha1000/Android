package dev.software.db.utils

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import dev.software.db.adapter.ListAdapter
import dev.software.db.model.Song


class ItemSwipe (
    private val adapter: ListAdapter,
    private val onItemRemoved: ((item: Song) -> Unit)? = null
    ): ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false

/*    override fun getSwipeDirs(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return super.getSwipeDirs(recyclerView, viewHolder)
    }*/

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val pos = viewHolder.adapterPosition
        val item = adapter.removeItem(pos) ?: return
        onItemRemoved?.invoke(item)
    }
}