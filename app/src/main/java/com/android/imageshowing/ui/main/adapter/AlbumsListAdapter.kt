package com.android.imageshowing.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.android.imageshowing.R
import com.android.imageshowing.data.model.api.Album
import com.android.imageshowing.databinding.AlbumItemBinding

class AlbumsListAdapter(
    private val context: Context,
    private val albums: List<Album>,
    val onClickDetails: (Int) -> Unit
) :
    RecyclerView.Adapter<AlbumsListAdapter.ListViewHolder>() {

    inner class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = MutableLiveData<String>()

        fun onClickDetails(): Unit =
            this@AlbumsListAdapter.onClickDetails(albums[layoutPosition].id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val lifecycleOwner = context as LifecycleOwner
        val binding = DataBindingUtil.inflate<AlbumItemBinding>(
            LayoutInflater.from(context),
            R.layout.album_item,
            parent,
            false
        )
        val vh = ListViewHolder(binding.root)

        binding.let {
            it.setLifecycleOwner(lifecycleOwner)
            it.vh = vh
        }

        return vh
    }

    override fun getItemCount(): Int = albums.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int): Unit = with(holder) {
        val album = albums[position]
        title.value = album.title
    }
}