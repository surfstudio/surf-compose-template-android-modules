package ru.surf._module_name_.ui.screens.list_module_name_

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.flow
import ru.surf._module_name_.data.models.UserModel
import ru.surf._module_name_.ui.actions.List_module_name_Actions
import ru.surf._module_name_.ui.viewModels._module_name_ViewModel

/**
 * Base page fun for initialization
 *
 * @param viewModel page model
 * @param onActions actions
 */
@Composable
fun List_module_name_Screen(
    viewModel: _module_name_ViewModel,
    onActions: (List_module_name_Actions) -> Unit = {},
) {
    val search: String? by viewModel.search.collectAsState()
    val items: LazyPagingItems<UserModel> = viewModel.list_module_name_.collectAsLazyPagingItems()
    val searchItems: LazyPagingItems<UserModel> = viewModel.searchList_module_name_.collectAsLazyPagingItems()

    List_module_name_Body(
        search = search,
        items = items,
        searchItems = searchItems,
        onActions = onActions,
    )
}
