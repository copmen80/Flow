package ua.devserhii.flow.app.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ua.devserhii.flow.app.data.exeption.ClientNotAuthorizedException
import ua.devserhii.flow.utils.Event


abstract class BaseViewModel : ViewModel() {

    protected val exceptionHandler = CoroutineExceptionHandler { context, throwable ->
        if(throwable is ClientNotAuthorizedException) {
            handleNotAuthorizedException()
        } else {
            handleError(throwable, context[CoroutineName.Key])
        }
    }

    protected val eventChannel = Channel<Event>(Channel.BUFFERED)
    val eventFlow = eventChannel.receiveAsFlow()

    private fun handleNotAuthorizedException() {
        navigateToLogin()
    }

    private fun navigateToLogin() {
        viewModelScope.launch {
            eventChannel.send(Event.Navigation(MainContentDirections.actionGlobalToLogin()))
        }
    }

    open fun handleError(throwable: Throwable, coroutineName: CoroutineName?) {
        handleError(throwable)
    }
    open fun handleError(throwable: Throwable) {/**/}
}