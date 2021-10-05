package ru.surf._module_name_.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.surf.core.data.preferences.CorePreferences
import ru.surf.users.base.UsersDatabase
import ru.surf.users.data.preferences.UsersPreferences
import ru.surf.users.services.dataService.UsersDataService

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
