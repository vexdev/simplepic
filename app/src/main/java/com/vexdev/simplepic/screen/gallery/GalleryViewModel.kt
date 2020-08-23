package com.vexdev.simplepic.screen.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import androidx.paging.PagedList
import com.vexdev.simplepic.core.LiveEvent
import com.vexdev.simplepic.core.NavigationProvider
import java.io.File

class GalleryViewModel(repository: GalleryRepository) : ViewModel(), NavigationProvider {
    val pictures: LiveData<PagedList<File>> = repository.getPictures()
    override val navigationEvent: LiveEvent<NavDirections> = LiveEvent()

    fun onPictureClick(picture: File?) {
        navigationEvent.value =
            GalleryFragmentDirections.actionGalleryFragmentToPictureFragment(
                picture?.absolutePath ?: ""
            )
    }
}
