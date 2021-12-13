package com.android.imageshowing.ui.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.imageshowing.R
import com.android.imageshowing.ui.image.adapter.ImagesListAdapter
import com.android.imageshowing.utils.EXTRA
import com.android.imageshowing.utils.EXTRA_TWO
import kotlinx.android.synthetic.main.albums_fragment.*
import kotlinx.android.synthetic.main.images_fragment.*

class ImagesFragment : Fragment() {
    private lateinit var viewModel: ImagesViewModel
    private val albumId by lazy { arguments?.getInt(EXTRA, 0) }
    private lateinit var albumsAdapter: ImagesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.images_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ImagesViewModel::class.java)

        initUI()
        observeChanges()
    }

    private fun initUI() {
        //Init UI
        albumId?.let { viewModel.loadImages(it) }
    }

    private fun observeChanges() {
        viewModel.images.observe(viewLifecycleOwner, { images ->
            pbLoadingImages.isVisible = false
            albumsAdapter = ImagesListAdapter(requireContext(), images) { title, url ->
                navigateToFullImage(title, url)
            }
            rvImages.adapter = albumsAdapter

            val lm = GridLayoutManager(context, 2).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (albumsAdapter.itemCount % 2 == 0 || position < albumsAdapter.itemCount - 1) 1 else 2
                    }
                }
            }
            rvImages.layoutManager = lm
        })
    }

    private fun navigateToFullImage(title: String, url: String) {
        val bundle = bundleOf(EXTRA to title, EXTRA_TWO to url)
        findNavController().navigate(R.id.fullImageFragment, bundle)
    }
}