package com.vexdev.simplepic.screen.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test


class MainViewModelTest {

    @Rule
    fun instantExecutorRule(): InstantTaskExecutorRule = InstantTaskExecutorRule()


    @Test
    fun sendsNavigationEventOnClickCalled() {
        val tested = MainViewModel()
        tested.onOpenGalleryClicked()
        assertEquals(
            MainFragmentDirections.actionMainFragmentToGalleryFragment(),
            tested.navigationEvent.value
        )
    }
}
