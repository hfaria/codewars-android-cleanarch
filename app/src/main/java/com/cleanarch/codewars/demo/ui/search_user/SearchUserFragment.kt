package com.cleanarch.codewars.demo.ui.search_user

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.cleanarch.codewars.demo.databinding.FragmentSearchUserBinding
import com.cleanarch.codewars.demo.ui.base.BaseFragment

class SearchUserFragment : BaseFragment<SearchUserViewModel>() {

    private lateinit var binding: FragmentSearchUserBinding

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