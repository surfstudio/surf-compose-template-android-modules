package ru.surf._module_name_.navigation.graph.impl

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.coroutines.launch
import ru.surf._module_name_.navigation.actions._Module_name_NavActions
import ru.surf._module_name_.navigation.nav._Module_name_Nav
import ru.surf._module_name_.ui.actions._Module_name_Actions
import ru.surf._module_name_.ui.screens.viewScreen._Module_name_Screen
import ru.surf._module_name_.ui.viewModels._Module_name_ViewModel

/**
 * NavGraph for [_Module_name_Screen]
 */
fun NavGraphBuilder._module_name_ScreenGraph(
    scaffoldState: ScaffoldState,
    navActions: _Module_name_NavActions,
) {
    composable(_Module_name_Nav.MainNav._Module_name_NavScreen.route) {
        val coroutineScope = rememberCoroutineScope()
        val viewModel: _Module_name_ViewModel = hiltViewModel()
        _Module_name_Screen(viewModel = viewModel) { event ->
            when (event) {
                is _Module_name_Actions.OpenMenu -> coroutineScope.launch { scaffoldState.drawerState.open() }
            }
        }
    }
}
