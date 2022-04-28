package dev.software.db.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import dev.software.db.R
import dev.software.db.adapter.ListAdapter
import dev.software.db.addSpaceDecoration
import dev.software.db.database.AppDatabase
import dev.software.db.databinding.FragmentListBinding
import dev.software.db.model.Song
import dev.software.db.utils.ItemSwipe


class ListFragment (): Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding
        get() = requireNotNull(_binding)

    private val adapter by lazy {
        ListAdapter{
            CustomDialogFragment(it, binding).show(childFragmentManager, "CustomDialogFragment")
        }
    }


    private val db by lazy {
        AppDatabase.getDatabase(requireContext()).songDao()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return FragmentListBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var resDB = makeDB()

        with(binding) {
            adapter.submitList(resDB)

            val findItem: MenuItem = toolbar.menu.findItem(R.id.search)
            ((findItem.actionView) as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    println(query)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    println(newText)
                    var findAll = db.findAll(newText)
                    println("size findAll ${findAll.size}")
                    adapter.submitList(findAll)
                    return true
                }

            })

            ItemTouchHelper(ItemSwipe(adapter) { removeItem(it) })
                .attachToRecyclerView(recyclerView)

            swipeRefresh.setOnRefreshListener {
                    swipeRefresh.isRefreshing = false
            }
            swipeRefresh.isRefreshing = false

            val layoutManager = LinearLayoutManager(view.context)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = layoutManager

            recyclerView.addSpaceDecoration(SPACE_SIZE)

        }
    }

    private fun removeItem(item: Song) {
        db.deleteSong(item)
    }


    private fun makeDB() : List<Song> {
        return db
            .getAllSongs()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val SPACE_SIZE = 50
    }
}
