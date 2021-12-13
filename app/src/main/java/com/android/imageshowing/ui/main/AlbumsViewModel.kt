package com.android.imageshowing.ui.main

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.imageshowing.data.model.api.Album
import com.android.imageshowing.data.model.api.User
import com.android.imageshowing.net.APIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.java.KoinJavaComponent

class AlbumsViewModel : ViewModel() {
    private val apiService: APIService by KoinJavaComponent.inject(APIService::class.java)
    val albums = MutableLiveData<List<Album>>()

    @SuppressLint("CheckResult")
    fun loadUsers(userId: Int) {
        apiService.getAlbums(userId).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    handleResponse(result)
                },
                { error ->
                    println(error.message)
                }
            )
    }

    private fun handleResponse(result: List<Album>) {
        albums.postValue(result)
    }
}