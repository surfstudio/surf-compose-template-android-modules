package ru.surf._module_name_.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import ru.surf._module_name_.services.api._module_name_Api
import ru.surf._module_name_.services.apiService._module_name_ApiService

/**
 * Module Dagger responsible for api services
 */
@Module
@InstallIn(ViewModelComponent::class)
object ModuleNetwork {

    /**
     * HTTP API into a interface
     */
    @Provides
    fun provide_Module_name_Api(retrofit: Retrofit): _Module_name_Api = retrofit.create(_Module_name_Api::class.java)

    /**
     * HTTP query service
     */
    @Provides
    fun provide_Module_name_ApiService(api: _Module_name_Api) = _Module_name_ApiService(api)
}
