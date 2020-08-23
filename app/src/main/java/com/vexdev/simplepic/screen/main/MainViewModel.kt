package com.vexdev.simplepic.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.vexdev.simplepic.core.LiveEvent
import com.vexdev.simplepic.core.NavigationProvider
import java.io.File

class MainViewModel : ViewModel(), NavigationProvider {
    override val navigationEvent: LiveEvent<NavDirections> = LiveEvent()
    var cacheFile: File? = null

    fun onOpenGalleryClicked() {
        navigationEvent.value = MainFragmentDirections.actionMainFragmentToGalleryFragment()
    }

}
