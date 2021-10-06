package ru.surf._module_name_.ui.screens.viewUser

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import ru.surf.core.base.LocalBackPressedDispatcher
import ru.surf._module_name_.ui.actions.ViewUserActions
import ru.surf._module_name_.ui.viewModels._module_name_ViewModel

/**
 * Base page fun for initialization
 *
 * @param viewModel page model
 * @param onActions actions
 */
@Composable
fun View_module_name_creen(
    userId: String,
    viewModel: _module_name_ViewModel,
    onActions: (ViewUserActions) -> Unit = {},
) {

    val user by viewModel.getUser(userId).collectAsState(null)
    val loading: Boolean by viewModel.loading.collectAsState()
    val error404: Boolean by viewModel.error404.collectAsState()

    ViewUserBody(
        id = userId,
        model = user,
        loading = loading,
        error404 = error404,
        onActions = onActions,
        backDispatcher = LocalBackPressedDispatcher.current,
    )
}
