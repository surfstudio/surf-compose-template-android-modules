package ru.surf._module_name_.ui.viewModels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.surf._module_name_.data.preferences._Module_name_Preferences
import javax.inject.Inject

/**
 * [ViewModel] for module
 *
 * @property preferences service shared preference
 */
@HiltViewModel
class _Module_name_ViewModel @Inject constructor(
    private val preferences: _Module_name_Preferences,
) : ViewModel() {

    /**
     * [MutableStateFlow] for loading state
     */
    private val _loading: MutableStateFlow<Boolean> = MutableStateFlow(false)

    /**
     * [StateFlow] for [_loading]
     */
    val loading: StateFlow<Boolean> get() = _loading.asStateFlow()

}
