package ru.surf._module_name_.services.apiService

import ru.surf._module_name_.services.api._module_name_Api
import ru.surf._module_name_.services.apiService.impl.*

/**
 * Base services for query separate by HTTP methods
 */
class _Module_name_ApiService(
    override val api: _Module_name_Api,
) :
    ApiServiceDelete,
    ApiServiceGet,
    ApiServicePatch,
    ApiServicePost,
    ApiServicePut
