package ru.surf._module_name_.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.surf.core.data.preferences.CorePreferences
import ru.surf._module_name_.base._module_name_Database
import ru.surf._module_name_.data.preferences._module_name_Preferences
import ru.surf._module_name_.services.dataService._module_name_DataService

/**
 * Dagger Module base for module (_module_name_)
 */
@Module
@InstallIn(SingletonComponent::class)
object ModuleDefault {

    /**
     * A database that doesn't need migrations and encryption
     */
    @Provides
    fun provide_Module_name_Database(application: Application): _Module_name_Database {
        return Room.databaseBuilder(application, _Module_name_Database::class.java, "${ModuleDefault::class.qualifiedName}.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    /**
     * Database management service
     */
    @Provides
    fun provide_module_name_DataService(db: _Module_name_Database) = _Module_name_DataService(db)

    /**
     * Shared preferences service
     */
    @Provides
    fun provide_Module_name_Preferences(corePreferences: CorePreferences) = _Module_name_Preferences(corePreferences.p)
}
