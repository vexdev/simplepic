package com.vexdev.simplepic.core

import androidx.navigation.NavDirections

/**
 * Provides [NavDirections] for the navigation.
 */
interface NavigationProvider {

    val navigationEvent: LiveEvent<NavDirections>

}
