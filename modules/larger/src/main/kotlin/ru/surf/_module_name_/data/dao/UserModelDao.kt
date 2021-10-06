package ru.surf._module_name_.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.surf._module_name_.data.models.UserModel

/**
 * Dao for model [UserModel]
 */
@Dao
interface UserModelDao {
    @Query("SELECT * FROM UserModel ORDER BY CAST(id AS INT)")
    fun pagingSource(): PagingSource<Int, UserModel>

    @Query("SELECT * FROM UserModel WHERE id = :id")
    fun getModel(id: String): Flow<UserModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertModels(vararg models: UserModel)

    @Query("DELETE FROM UserModel")
    suspend fun clear()

    @Query("SELECT COUNT(*) FROM UserModel")
    suspend fun count(): Int
}
