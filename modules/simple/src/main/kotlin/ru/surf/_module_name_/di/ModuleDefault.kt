package ru.surf._module_name_.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.surf._module_name_.data.preferences._Module_name_Preferences
import ru.surf.core.data.preferences.CorePreferences

/**
 * Dagger Module base for module (_module_name_)
 */
@Module
@InstallIn(SingletonComponent::class)
object ModuleDefault {

    /**
     * Shared preferences service
     */
    @Provides
    fun provide_Module_name_Preferences(corePreferences: CorePreferences) = _Module_name_Preferences(corePreferences.p)
}
