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
import ru.surf._module_name_.data.models.UserModel
import ru.surf._module_name_.data.preferences._module_name_Preferences
import ru.surf._module_name_.paging._module_name_PageSource
import ru.surf._module_name_.paging._module_name_RemoteMediator
import ru.surf._module_name_.services.apiService._module_name_ApiService
import ru.surf._module_name_.services.dataService._module_name_DataService
import timber.log.Timber
import javax.inject.Inject

/**
 * [ViewModel] for module
 *
 * @property apiService service http query
 * @property dataService service db room
 * @property preferences service shared preference
 */
@HiltViewModel
class _Module_name_ViewModel @Inject constructor(
    private val apiService: _module_name_ApiService,
    private val dataService: _module_name_DataService,
    private val preferences: _module_name_Preferences,
) : ViewModel() {

    /**
     * [MutableStateFlow] state 404 response
     */
    private val _error404: MutableStateFlow<Boolean> = MutableStateFlow(false)

    /**
     * [StateFlow] for [_error404]
     */
    val error404: StateFlow<Boolean> get() = _error404.asStateFlow()

    /**
     * [MutableStateFlow] for loading state
     */
    private val _loading: MutableStateFlow<Boolean> = MutableStateFlow(false)

    /**
     * [StateFlow] for [_loading]
     */
    val loading: StateFlow<Boolean> get() = _loading.asStateFlow()

    /**
     * [MutableStateFlow] for search query
     */
    private val _search: MutableStateFlow<String?> = MutableStateFlow(null)

    /**
     * [StateFlow] for [_search]
     */
    val search = _search.asStateFlow()

    /**
     * List with paging [RemoteMediator]
     */
    @OptIn(ExperimentalPagingApi::class)
    val list_module_name_: Flow<PagingData<UserModel>> = Pager(
        config = PagingConfig(pageSize = ConstantsPaging.PAGE_LIMIT),
        remoteMediator = _module_name_RemoteMediator(apiService, dataService, preferences)
    ) {
        dataService.pagingListUserModel()
    }.flow

    /**
     * List with paging [PagingSource]
     */
    val searchList_module_name_: Flow<PagingData<UserModel>> = Pager(PagingConfig(pageSize = ConstantsPaging.PAGE_LIMIT)) {
        _module_name_PageSource(_search.value, apiService)
    }.flow.cachedIn(viewModelScope)

    fun search(search: String?) {
        _search.value = search
    }

    /**
     * [Flow] room for view page
     */
    fun getUser(userId: String) = dataService.getUserModel(userId).distinctUntilChanged()

    /**
     * Update view with model [UserModel]
     *
     * @param userId string user identifier
     */
    fun getViewUser(userId: String) {
        _loading.value = true
        _error404.value = false
        viewModelScope.launch {
            // update settings
            apiService.getViewUser(userId)
                .success { model ->
                    dataService.withTransaction<_module_name_DataService> {
                        insertUserModel(model)
                    }
                }.error {
                    _error404.value = true
                    Timber.e(it)
                }.done {
                    _loading.value = false
                }
        }
    }
}
