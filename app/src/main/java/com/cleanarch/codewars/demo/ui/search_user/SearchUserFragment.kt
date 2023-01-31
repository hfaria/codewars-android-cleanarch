package com.cleanarch.codewars.demo.ui.search_user

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.cleanarch.codewars.demo.databinding.FragmentSearchUserBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class SearchUserFragment : Fragment() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    protected val viewModel: SearchUserViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentSearchUserBinding

    override fun onAttach(activity: Activity) {
        AndroidSupportInjection.inject(this)
        super.onAttach(activity)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchUserBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.vm = viewModel
        binding.state = viewModel.state
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.routes.userProfileRoute.observe(viewLifecycleOwner) { user ->
            Toast
                .makeText(requireActivity(), "NAME: ${user.name.orEmpty()}", Toast.LENGTH_LONG)
                .show()
        }

        viewModel.state.errorMessage.observe(viewLifecycleOwner) { message ->
            Toast
                .makeText(requireActivity(), message, Toast.LENGTH_LONG)
                .show()
        }
    }
}