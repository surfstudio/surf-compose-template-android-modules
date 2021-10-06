package ru.surf._module_name_.services.dataService.impl

import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow
import ru.surf.core.interfaces.IAppDatabase
import ru.surf._module_name_.base._module_name_Database
import ru.surf._module_name_.data.dao.UserModelDao
import ru.surf._module_name_.data.models.UserModel
import timber.log.Timber

/**
 * Service part for work with model [UserModel]
 */
interface UserModelDataService : IAppDatabase {

    /**
     * Base room db
     */
    override val db: _module_name_Database

    /**
     * Doa model [UserModel]
     */
    private val dao: UserModelDao get() = db.daoUserModel()

    /**
     * Performed when the user logs out
     */
    override fun clearCacheAfterLogout() {
        Timber.e("Clear cache: UserModelDataService")
    }

    /**
     * Get [Flow] model
     */
    fun getUserModel(userId: String): Flow<UserModel> {
        return dao.getModel(userId)
    }

    /**
     * Get [PagingSource] for paging list
     */
    fun pagingListUserModel(): PagingSource<Int, UserModel> {
        return dao.pagingSource()
    }

    /**
     * Fun for insert models
     */
    suspend fun insertUserModel(vararg models: UserModel) {
        dao.insertModels(*models)
    }

    /**
     * Remove all models
     */
    suspend fun clearUserModel() {
        dao.clear()
    }

    /**
     * Get count models
     */
    suspend fun countUserModel(): Int {
        return dao.count()
    }
}
