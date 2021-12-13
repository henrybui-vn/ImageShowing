package com.android.imageshowing.ui.image

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.imageshowing.R
import com.android.imageshowing.utils.EXTRA
import com.android.imageshowing.utils.EXTRA_TWO
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.full_image_fragment.*
import kotlinx.android.synthetic.main.image_item.*

class FullImageFragment : Fragment() {
    private lateinit var viewModel: FullImageViewModel
    private val title by lazy { arguments?.getString(EXTRA, "") }
    private val imageUrl by lazy { arguments?.getString(EXTRA_TWO, "") }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.full_image_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FullImageViewModel::class.java)

        initUI()
        observeChanges()
    }

    private fun initUI() {
        //Init UI
        tvImageTitle.text = title
        imageUrl?.let {
            Glide.with(requireActivity()).load(imageUrl).error(R.drawable.ic_broken_image)
                .into(imgFull)
        }
    }

    private fun observeChanges() {

    }
}