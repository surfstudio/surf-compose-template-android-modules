package ru.surf._module_name_.services.api

import ru.surf.core.services.api.impl.ApiDelete
import ru.surf._module_name_.services.api.impl.ApiGet
import ru.surf._module_name_.services.api.impl.ApiPatch
import ru.surf._module_name_.services.api.impl.ApiPost
import ru.surf._module_name_.services.api.impl.ApiPut

/**
 * Base interfaces for retrofit separate by HTTP methods
 */
interface _Module_name_Api : ApiDelete, ApiGet, ApiPatch, ApiPost, ApiPut
