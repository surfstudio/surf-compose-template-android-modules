package ru.surf._module_name_.ui.screens.viewScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import ru.surf._module_name_.ui.actions._Module_name_Actions
import ru.surf._module_name_.ui.viewModels._Module_name_ViewModel

/**
 * Base page fun for initialization
 *
 * @param viewModel page model
 * @param onActions actions
 */
@Composable
fun _Module_name_Screen(
    viewModel: _Module_name_ViewModel,
    onActions: (_Module_name_Actions) -> Unit = {},
) {

    val loading: Boolean by viewModel.loading.collectAsState()

    _Module_name_Body(
        loading = loading,
        onActions = onActions,
    )
}
