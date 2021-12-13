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
import com.android.imageshowing.data.model.api.User
import com.android.imageshowing.databinding.UserItemBinding


class UsersListAdapter(
    private val context: Context,
    private val users: List<User>,
    val onClickDetails: (Int) -> Unit
) :
    RecyclerView.Adapter<UsersListAdapter.ListViewHolder>() {

    inner class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val username = MutableLiveData<String>()
        val name = MutableLiveData<String>()
        val email = MutableLiveData<String>()
        val phone = MutableLiveData<String>()

        fun onClickDetails(): Unit =
            this@UsersListAdapter.onClickDetails(users[layoutPosition].id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val lifecycleOwner = context as LifecycleOwner
        val binding = DataBindingUtil.inflate<UserItemBinding>(
            LayoutInflater.from(context),
            R.layout.user_item,
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

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int): Unit = with(holder) {
        val user = users[position]
        username.value = user.username
        name.value = user.name
        phone.value = user.phone
        email.value = user.email
    }
}