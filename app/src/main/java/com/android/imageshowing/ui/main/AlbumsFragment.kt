package com.android.imageshowing.ui.main

import android.content.Intent
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
import com.android.imageshowing.ui.image.ImagesActivity
import com.android.imageshowing.ui.main.adapter.AlbumsListAdapter
import com.android.imageshowing.ui.main.adapter.UsersListAdapter
import com.android.imageshowing.utils.EXTRA
import kotlinx.android.synthetic.main.albums_fragment.*
import kotlinx.android.synthetic.main.users_fragment.*
import kotlinx.android.synthetic.main.users_fragment.rvUsers

class AlbumsFragment : Fragment() {
    private lateinit var viewModel: AlbumsViewModel
    private val userId by lazy { arguments?.getInt(EXTRA, 0) }
    private lateinit var albumsAdapter: AlbumsListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.albums_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AlbumsViewModel::class.java)

        initUI()
        observeChanges()
    }

    private fun initUI() {
        //Init UI
        userId?.let { viewModel.loadUsers(it) }
    }

    private fun observeChanges() {
        viewModel.albums.observe(viewLifecycleOwner, { albums ->
            pbLoadingAlbums.isVisible = false
            albumsAdapter = AlbumsListAdapter(requireContext(), albums) { id ->
                navigateToAlbum(id)
            }
            rvAlbums.adapter = albumsAdapter
            rvAlbums.layoutManager = LinearLayoutManager(requireContext())
        })
    }

    private fun navigateToAlbum(id: Int) {
        val bundle = bundleOf(EXTRA to id)
        val intent = Intent(
            activity,
            ImagesActivity::class.java
        ).apply { bundle.let { putExtras(it) } }
        requireActivity().startActivity(intent)
    }
}