package com.android.imageshowing.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.imageshowing.R
import com.android.imageshowing.ui.main.adapter.UsersListAdapter
import com.android.imageshowing.utils.EXTRA
import kotlinx.android.synthetic.main.users_fragment.*

class UsersFragment : Fragment() {
    private lateinit var viewModel: UsersViewModel

    private lateinit var usersAdapter: UsersListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.users_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UsersViewModel::class.java)

        initUI()
        observeChanges()
    }

    private fun initUI() {
        //Init UI
    }

    private fun observeChanges() {
        viewModel.users.observe(viewLifecycleOwner, { users ->
            pbLoadingUsers.isVisible = false
            usersAdapter = UsersListAdapter(requireContext(), users) { id ->
                navigateToAlbums(id)
            }
            rvUsers.adapter = usersAdapter
            rvUsers.layoutManager = LinearLayoutManager(requireContext())
        })
    }

    private fun navigateToAlbums(id: Int) {
        val bundle = bundleOf(EXTRA to id)
        findNavController().navigate(R.id.albumsFragment, bundle)
    }
}