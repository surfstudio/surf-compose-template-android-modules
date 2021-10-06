package ru.surf._module_name_.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.keygenqt.response.extensions.error
import com.keygenqt.response.extensions.isEmpty
import com.keygenqt.response.extensions.isError
import com.keygenqt.response.extensions.success
import ru.surf.core.extension.withTransaction
import ru.surf.core.utils.ConstantsPaging.CACHE_TIMEOUT
import ru.surf.core.utils.ConstantsPaging.PAGE_LIMIT
import ru.surf._module_name_.data.models.UserModel
import ru.surf._module_name_.data.preferences._module_name_Preferences
import ru.surf._module_name_.services.apiService._module_name_ApiService
import ru.surf._module_name_.services.dataService._module_name_DataService
import timber.log.Timber

/**
 * Paging list with room cache [RemoteMediator]
 *
 * @param apiService query service module
 * @param dataService service room db
 * @param preferences service shared preferences
 *
 * @see <a href="https://developer.android.com/reference/kotlin/androidx/paging/RemoteMediator">RemoteMediator</a>
 */
@OptIn(ExperimentalPagingApi::class)
class UsersRemoteMediator(
    private val apiService: _Module_name_ApiService,
    private val dataService: _Module_name_DataService,
    private val preferences: _Module_name_Preferences,
) : RemoteMediator<Int, UserModel>() {

    /**
     * Static key for offset-limit paging
     */
    companion object {
        var key: Int? = null
    }

    /**
     * Timeout cache for refresh list at start
     */
    override suspend fun initialize(): InitializeAction {
        return if (System.currentTimeMillis() - preferences.lastUpdateListUsers >= CACHE_TIMEOUT) {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        } else {
            InitializeAction.SKIP_INITIAL_REFRESH
        }
    }

    /**
     * Base logic
     */
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserModel>,
    ): MediatorResult {
        return try {

            val offset = when (loadType) {
                LoadType.REFRESH -> {
                    key = null
                    key
                }
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    key = (key ?: 0).plus(1)
                    key
                }
            }

            val response = apiService.getList_module_name_(
                offset = (offset ?: 0) * PAGE_LIMIT
            )

            response.success { models ->
                dataService.withTransaction<_module_name_DataService> {
                    if (loadType == LoadType.REFRESH) {
                        preferences.lastUpdateList_module_name_ = System.currentTimeMillis()
                        clearUserModel()
                    }
                    insertUserModel(*models.toTypedArray())
                }
            }.error {
                Timber.e(it)
            }

            MediatorResult.Success(
                endOfPaginationReached = response.isError || response.isEmpty
            )
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
