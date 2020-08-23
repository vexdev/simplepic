package com.vexdev.simplepic.screen.main

import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.vexdev.simplepic.core.LiveEvent
import com.vexdev.simplepic.core.NavigationProvider

class MainViewModel : ViewModel(), NavigationProvider {
    override val navigationEvent: LiveEvent<NavDirections> = LiveEvent()

    fun onOpenGalleryClicked() {
        navigationEvent.value = MainFragmentDirections.actionMainFragmentToGalleryFragment()
    }

}
