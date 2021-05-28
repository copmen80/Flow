package ua.devserhii.flow.utils

import androidx.annotation.StringRes
import androidx.navigation.NavDirections

sealed class Event {
    data class Message(@StringRes val idRes: Int) : Event()

    data class Navigation(val directions: NavDirections) : Event()
}