package dev.software.db.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import dev.software.db.database.AppDatabase
import dev.software.db.databinding.FragmentAddBinding
import dev.software.db.model.Song

class AddFragment : Fragment(){

    private var _binding: FragmentAddBinding? = null
    private val binding
        get() = requireNotNull(_binding)

    private val db by lazy {
        AppDatabase.getDatabase(requireContext()).songDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentAddBinding.inflate(layoutInflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            btnAdd.setOnClickListener {

                titleContainer.error=""
                genreContainer.error = ""
                authorContainer.error = ""
                println(editTitle.text)

                val editTitleRes = editTitle.text
                    ?.takeIf { it.isNotEmpty() }
                    ?: run {
                        titleContainer.error = "Text is empty"
                        return@run null
                    }
                val editGenreRes = editGenre.text
                    ?.takeIf { it.isNotEmpty() }
                    ?: run {
                        genreContainer.error = "Text is empty"
                        return@run null
                    }
                val editAuthorRes = editAuthor.text
                    ?.takeIf { it.isNotEmpty() }
                    ?: run {
                        authorContainer.error = "Text is empty"
                        return@run null
                    }

                if (editTitleRes != null
                    && editGenreRes != null
                    && editAuthorRes != null){
                    var insertSongs = db.insertSong(
                        Song(
                            title = editTitle.text.toString(),
                            genre = editGenre.text.toString(),
                            author = editAuthor.text.toString()
                        )
                    )
                    println("insertSongs.size ${insertSongs.size}")
                    if (insertSongs.size == 1){
                        println("попали")
                        Toast.makeText(
                            view.context,
                            "Your data has been successfully added to the database",
                            Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}