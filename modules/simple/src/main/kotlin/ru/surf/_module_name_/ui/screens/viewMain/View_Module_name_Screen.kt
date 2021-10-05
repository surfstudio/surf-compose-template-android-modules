package ru.surf._module_name_.ui.screens.viewUser

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import ru.surf.core.base.LocalBackPressedDispatcher
import ru.surf.users.ui.actions.ViewUserActions
import ru.surf.users.ui.viewModels.UsersViewModel

/**
 * Base page fun for initialization
 *
 * @param viewModel page model
 * @param onActions actions
 */
@Composable
fun ViewMainScreen(
    userId: String,
    viewModel: _Module_name_ViewModel,
    onActions: (View_Module_name_Actions) -> Unit = {},
) {

    val loading: Boolean by viewModel.loading.collectAsState()

    View_Module_name_Body(
        loading = loading,
        onActions = onActions,
    )
}
