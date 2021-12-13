package com.android.imageshowing.ui.image

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.imageshowing.data.model.api.Image
import com.android.imageshowing.net.APIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.java.KoinJavaComponent

class ImagesViewModel : ViewModel() {
    private val apiService: APIService by KoinJavaComponent.inject(APIService::class.java)
    val images = MutableLiveData<List<Image>>()

    @SuppressLint("CheckResult")
    fun loadImages(albumId: Int) {
        apiService.getImages(albumId).subscribeOn(Schedulers.io())
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

    private fun handleResponse(result: List<Image>) {
        images.postValue(result)
    }
}