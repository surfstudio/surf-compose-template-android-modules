package ru.surf._module_name_.services.apiService.impl

import com.keygenqt.response.LocalTryExecuteWithResponse.executeWithResponse
import com.keygenqt.response.ResponseResult
import com.keygenqt.response.extensions.responseCheck
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import ru.surf.core.utils.ConstantsApp
import ru.surf.core.utils.HelperApp
import ru.surf._module_name_.BuildConfig
import ru.surf._module_name_.data.mappers.toModel
import ru.surf._module_name_.data.mappers.toModels
import ru.surf._module_name_.data.models.UserModel
import ru.surf._module_name_.services.api._module_name_Api

/**
 * The GET method requests a representation of the specified resource. Requests using GET should only retrieve data.
 */

interface ApiServiceGet {

    val api: _module_name_Api

    /**
     * Get list models [UserModel]
     *
     * @param offset mysql offset
     * @param search find models by name
     */
    suspend fun getList_module_name_(offset: Int, search: String? = null): ResponseResult<List<UserModel>> {
        return withContext(Dispatchers.IO) {
            executeWithResponse {

                // Simulate slow internet
                if (BuildConfig.DEBUG && HelperApp.isNotRunningTest) delay(ConstantsApp.DEBUG_DELAY)

                api.getList_module_name_(offset = offset, search = search)
                    .responseCheck()
                    .body()
                    ?.toModels()
                    ?: emptyList()
            }
        }
    }

    /**
     * Update view with model [UserModel]
     *
     * @param userId string user identifier
     */
    suspend fun getViewUser(userId: String): ResponseResult<UserModel> {
        return withContext(Dispatchers.IO) {

            // Simulate slow internet
            if (BuildConfig.DEBUG && HelperApp.isNotRunningTest) delay(ConstantsApp.DEBUG_DELAY)

            executeWithResponse {
                api.updateUser(userId)
                    .responseCheck()
                    .body()
                    ?.toModel()!!
            }
        }
    }
}
