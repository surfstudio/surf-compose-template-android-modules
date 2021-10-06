package ru.surf._module_name_.base

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.surf._module_name_.data.dao.UserModelDao
import ru.surf._module_name_.data.models.UserModel

/**
 * Database configuration [RoomDatabase]
 */
@Database(
    entities = [
        UserModel::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class _Module_name_Database : RoomDatabase() {
    /**
     * Dao for model [UserModel]
     */
    abstract fun daoUserModel(): UserModelDao
}
