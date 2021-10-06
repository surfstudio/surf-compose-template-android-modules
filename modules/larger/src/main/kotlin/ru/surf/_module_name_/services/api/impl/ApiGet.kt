package ru.surf._module_name_.services.api.impl

import androidx.annotation.IntRange
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.surf.core.utils.ConstantsPaging
import ru.surf._module_name_.data.responses.UserResponse

/**
 * The GET method requests a representation of the specified resource. Requests using GET should only retrieve data.
 */
interface ApiGet {

    /**
     * Get list _module_name_
     *
     * @param search for find _module_name_ by name
     * @param offset mysql offset
     * @param offset mysql limit
     *
     * @see <a href="https://dev.mysql.com/doc/refman/8.0/en/select.html">SELECT Statement</a>
     */
    @GET("_module_name_")
    suspend fun getList_module_name_(
        @Query("search")
        search: String?,
        @Query("offset")
        offset: Int = 1,
        @IntRange(from = 1, to = ConstantsPaging.MAX_PAGE_SIZE.toLong())
        @Query("limit")
        limit: Int = ConstantsPaging.PAGE_LIMIT,
    ): Response<List<UserResponse>>

    /**
     * Update user model
     *
     * @param id user identifier
     */
    @GET("_module_name_/{id}")
    suspend fun updateUser(
        @Path("id") id: String?,
    ): Response<UserResponse>
}
