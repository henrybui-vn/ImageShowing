package com.android.imageshowing.ui.image.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.android.imageshowing.R
import com.android.imageshowing.data.model.api.Image
import com.android.imageshowing.databinding.ImageItemBinding
import com.bumptech.glide.Glide

class ImagesListAdapter(
private val context: Context,
private val images: List<Image>,
val onClickDetails: (String, String) -> Unit
) :
RecyclerView.Adapter<ImagesListAdapter.ListViewHolder>() {

    inner class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = MutableLiveData<String>()
        val thumbnailUrl = MutableLiveData<String>()
        val url = MutableLiveData<String>()

        fun onClickDetails(): Unit =
            this@ImagesListAdapter.onClickDetails(images[layoutPosition].title, images[layoutPosition].url)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val lifecycleOwner = context as LifecycleOwner
        val binding = DataBindingUtil.inflate<ImageItemBinding>(
            LayoutInflater.from(context),
            R.layout.image_item,
            parent,
            false
        )
        val vh = ListViewHolder(binding.root)

        binding.let {
            it.lifecycleOwner = lifecycleOwner
            it.vh = vh
        }

        vh.thumbnailUrl.observe(lifecycleOwner, {
            println(it)
            Glide.with(context).load(it).error(R.drawable.ic_broken_image).into(binding.imgThumbnail)
        })

        return vh
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int): Unit = with(holder) {
        val image = images[position]
        title.value = image.title
        url.value = image.url
        thumbnailUrl.value = image.thumbnailUrl
    }
}