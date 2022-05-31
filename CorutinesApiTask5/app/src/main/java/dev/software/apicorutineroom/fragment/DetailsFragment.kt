package dev.software.apicorutineroom.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import coil.load
import dev.software.apicorutineroom.databinding.FragmentDetailsBinding
import dev.software.apicorutineroom.model.CharacterDescription
import dev.software.apicorutineroom.retrofit.ApiService

import kotlinx.coroutines.launch

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() = requireNotNull(_binding)

    private var currentlyRequest: List<CharacterDescription>? = null

    private val args by navArgs<DetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentDetailsBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val lifecycleScope = viewLifecycleOwner.lifecycleScope
        lifecycleScope.launch {
            loadDetailCharacter()
        }

        with(binding) {
            toolbar.setupWithNavController(findNavController())
        }
    }

    private suspend fun loadDetailCharacter() {

        currentlyRequest = ApiService.api.characterDescription(args.id)
        println(currentlyRequest)

        with(binding){
            currentlyRequest ?: return

            textName.text = "Name: ${currentlyRequest?.get(0)?.name}"
            picture.load(currentlyRequest?.get(0)?.url)
            textBirthday.text = "Birthday: ${currentlyRequest?.get(0)?.birthday}"
            textNick.text = "Nick: ${currentlyRequest?.get(0)?.nickname}"

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}