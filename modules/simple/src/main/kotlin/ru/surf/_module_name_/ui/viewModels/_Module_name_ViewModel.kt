package ru.surf._module_name_.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.keygenqt.response.extensions.done
import com.keygenqt.response.extensions.error
import com.keygenqt.response.extensions.success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.surf.core.extension.withTransaction
import ru.surf.core.utils.ConstantsPaging
import ru.surf.users.data.models.UserModel
import ru.surf.users.data.preferences.UsersPreferences
import ru.surf.users.paging.UsersPageSource
import ru.surf.users.paging.UsersRemoteMediator
import ru.surf.users.services.apiService.UsersApiService
import ru.surf.users.services.dataService.UsersDataService
import timber.log.Timber
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
