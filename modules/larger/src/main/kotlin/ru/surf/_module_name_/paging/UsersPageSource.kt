package ru.surf._module_name_.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.keygenqt.response.ResponseResult
import ru.surf.core.utils.ConstantsPaging
import ru.surf._module_name_.data.models.UserModel
import ru.surf._module_name_.services.apiService._module_name_ApiService

/**
 * Paging list without room cache [PagingSource]
 *
 * @param search string query to back-end
 * @param apiService query service module
 *
 * @see <a href="https://developer.android.com/reference/kotlin/androidx/paging/PagingSource">PagingSource</a>
 */
class _module_name_PageSource(
    private val search: String?,
    private val apiService: _module_name_ApiService,
) : PagingSource<Int, UserModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserModel> {
        val offset = params.key ?: 0
        if (search == null) {
            return LoadResult.Page(
                data = listOf(),
                prevKey = null,
                nextKey = null,
            )
        }

        // not work with mock - 400 error... should be empty
        // return apiService.getList_module_name_(search = search, offset = offset).pagingSucceeded { data ->
        //     LoadResult.Page(
        //         data = data,
        //         prevKey = if (offset == 0) null else offset,
        //         nextKey = if (data.isEmpty()) null else offset + ConstantsPaging.PAGE_LIMIT
        //     )
        // }

        // fix for mock
        return apiService.getList_module_name_(search = search, offset = offset).let {
            if (it is ResponseResult.Success) {
                LoadResult.Page(
                    data = it.data,
                    prevKey = if (offset == 0) null else offset,
                    nextKey = if (it.data.isEmpty()) null else offset + ConstantsPaging.PAGE_LIMIT
                )
            } else {
                LoadResult.Page(
                    data = listOf(),
                    prevKey = null,
                    nextKey = null
                )
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UserModel>): Int {
        return 0
    }
}
