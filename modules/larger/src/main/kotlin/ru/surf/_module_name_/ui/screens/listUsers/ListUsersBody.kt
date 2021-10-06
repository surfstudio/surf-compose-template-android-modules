package ru.surf._module_name_.ui.screens.list_module_name_

import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.insets.systemBarsPadding
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.keygenqt.accompanist.MainScaffoldSearch
import com.keygenqt.accompanist.SwipeRefreshList
import com.keygenqt.modifier.paddingSmall
import kotlinx.coroutines.flow.flow
import ru.surf.core.base.LocalMainViewModel
import ru.surf.core.base.MainViewModel
import ru.surf.core.compose.*
import ru.surf.core.utils.HelperApp
import ru.surf._module_name_.data.models.UserModel
import ru.surf._module_name_.ui.actions.List_module_name_Actions
import ru.surf.core.R as RCore

/**
 * Body page [List_module_name_Screen]
 *
 * @param search state call query to api
 * @param items list paging list [LazyPagingItems]
 * @param searchItems searching list paging list [LazyPagingItems]
 * @param onActions actions page
 */
@Composable
fun List_module_name_Body(
    search: String?,
    items: LazyPagingItems<UserModel>,
    searchItems: LazyPagingItems<UserModel>,
    onActions: (List_module_name_Actions) -> Unit = {},
    localMainViewModel: MainViewModel? = LocalMainViewModel.current
) {
    var expanded by remember { mutableStateOf(false) }
    val contentLoading = @Composable { LoaderPage() }
    val contentEmpty = @Composable {
        EmptyPage()
    }
    val contentLoadState = @Composable { loadState: LoadState ->
        if (loadState is LoadState.Loading) {
            Loader(Modifier.paddingSmall())
            Spacer(modifier = Modifier.paddingSmall())
        }
    }

    MainScaffoldSearch(
        modifier = Modifier.systemBarsPadding(),
        navigationIcon = Icons.Default.Menu,
        navigationIconOnClick = { onActions(List_module_name_Actions.OpenMenu) },
        contentTitle = {
            TopBarContentTitle(stringResource(id = RCore.string.menu_members))
        },
        searchListener = { value ->
            onActions(List_module_name_Actions.Search(value))
            searchItems.refresh()
        },
        actions = {
            IconButton(onClick = { expanded = true }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Options menu"
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(onClick = {
                    localMainViewModel?.logout()
                }) {
                    Text("Logout")
                }
            }
        }
    ) {
        search?.let {
            SwipeRefreshList(
                modifier = Modifier,
                items = searchItems,
                state = rememberSwipeRefreshState(searchItems.loadState.refresh is LoadState.Loading),
                indicator = { st, tr ->
                    AppSwipeRefreshIndicator(st, tr)
                },
                contentEmpty = contentEmpty,
                contentLoadState = contentLoadState,
                contentLoading = contentLoading,
            ) { _, model ->
                ListUserItem(model = model, onActions = onActions)
            }
        } ?: run {
            SwipeRefreshList(
                modifier = Modifier,
                items = items,
                state = rememberSwipeRefreshState(items.loadState.refresh is LoadState.Loading),
                indicator = { st, tr ->
                    AppSwipeRefreshIndicator(st, tr)
                },
                contentEmpty = contentEmpty,
                contentLoadState = contentLoadState,
                contentLoading = contentLoading,
            ) { _, model ->
                ListUserItem(model = model, onActions = onActions)
            }
        }
    }
}
