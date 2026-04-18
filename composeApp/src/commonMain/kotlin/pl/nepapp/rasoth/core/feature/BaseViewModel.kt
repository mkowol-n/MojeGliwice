package pl.nepapp.rasoth.core.feature

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

abstract class BaseViewModel<STATE: Any, SIDE_EFFECT: Any>(
    state: STATE
): ViewModel(), ContainerHost<STATE, SIDE_EFFECT> {
    override val container: Container<STATE, SIDE_EFFECT> = container(state)
}
