package com.vexdev.simplepic.screen.gallery

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GalleryViewModelFactory(val context: () -> Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        GalleryViewModel::class.java -> GalleryViewModel(GalleryRepository(context())) as T
        else -> throw RuntimeException("Unable to create instance of $modelClass")
    }

}
