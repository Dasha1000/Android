package dev.software.db.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import dev.software.db.database.AppDatabase
import dev.software.db.databinding.FragmentDialogBinding

import dev.software.db.databinding.FragmentListBinding
import dev.software.db.model.Song

class CustomDialogFragment(
        private var item: Song,
        private val bind:FragmentListBinding
) : DialogFragment(){

    private var _binding: FragmentDialogBinding? = null
    private val binding: FragmentDialogBinding
        get() = requireNotNull(_binding)

    private val db by lazy {
        AppDatabase.getDatabase(requireContext()).songDao()
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        return FragmentDialogBinding.inflate(layoutInflater, container, false)
                .also { _binding = it }
                .root
    }

    override fun onStart() {
        super.onStart()

        with(binding){
            editAuthor.setText(item.author)
            editGenre.setText(item.genre)
            editTitle.setText(item.title)

            btnUpdate.setOnClickListener{
                item.author = editAuthor.text.toString()
                item.genre = editGenre.text.toString()
                item.title = editTitle.text.toString()
                db.updateSong(item)

                bind.recyclerView.adapter?.notifyDataSetChanged()

                dismiss()
            }
        }

        val width = (resources.displayMetrics.widthPixels * 0.8).toInt()
        dialog?.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}