package dev.software.db.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dev.software.db.R
import dev.software.db.databinding.FragmentContentBinding

class ContentFragment : Fragment(){

    private var _binding: FragmentContentBinding? = null
    private val binding: FragmentContentBinding
        get() = requireNotNull(_binding)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentContentBinding.inflate(layoutInflater, container, false)
            .also { _binding = it }
            .root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            val contentController =
                (childFragmentManager.findFragmentById(R.id.pages_container) as NavHostFragment)
                    .navController
            bottomNav.setupWithNavController(contentController)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}