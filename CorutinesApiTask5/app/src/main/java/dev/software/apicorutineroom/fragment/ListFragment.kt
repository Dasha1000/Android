package dev.software.apicorutineroom.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dev.software.apicorutineroom.addSpaceDecoration
import dev.software.apicorutineroom.database.AppDatabase
import dev.software.apicorutineroom.databinding.FragmentListBinding
import dev.software.apicorutineroom.model.PagingData
import dev.software.apicorutineroom.retrofit.ApiService
import dev.software.apicorutineroom.adapter.ListCharactersAdapter
import kotlinx.coroutines.launch


class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding
        get() = requireNotNull(_binding)

    private val characterRepo by lazy() {
        ApiService.provideRepo()
    }

    private val db by lazy {
        AppDatabase.getDatabase(requireContext()).charactersDao()
    }

    private val adapter by lazy() {
        ListCharactersAdapter(
            context = requireContext(),
            onCharacterClicked = {
                findNavController()
                    .navigate(ListFragmentDirections.toDetails(it.id))
            }
        )
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

        with(binding) {
            adapter.submitList(emptyList())

            val layoutManager = LinearLayoutManager(view.context)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = layoutManager
            //var offset: Int = 0
            viewLifecycleOwner.lifecycleScope.launch {
                    // сделано без pagination пока, просто эмитация работы)
                    adapter.submitList(db.getAllCharacters().map { PagingData.Content(it) })
                    val charactersList = characterRepo.getCharacters(LIMIT, 0)
                    db.insertCharacters(charactersList)
                    adapter.submitList(charactersList.map { PagingData.Content(it) } + PagingData.Loading)
                    //offset += LIMIT*PAGE
            }

            recyclerView.addSpaceDecoration(SPACE_SIZE)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val SPACE_SIZE = 50
        private const val LIMIT = 6
        //private const val  PAGE = 0
    }
}