package ru.surf._module_name_.navigation.graph.impl

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import kotlinx.coroutines.launch
import ru.surf._module_name_.navigation.actions._Module_name_NavActions
import ru.surf._module_name_.navigation.nav._Module_name_Nav
import ru.surf._module_name_.ui.actions.List_Module_name_Actions
import ru.surf._module_name_.ui.actions.ViewUserActions
import ru.surf._module_name_.ui.screens.list_Module_name_.List_Module_name_Screen
import ru.surf._module_name_.ui.screens.viewUser.View_Module_name_creen
import ru.surf._module_name_.ui.viewModels._Module_name_ViewModel

/**
 * NavGraph for [List_Module_name_Screen], [View_Module_name_creen]
 */
fun NavGraphBuilder._module_name_ScreenGraph(
    scaffoldState: ScaffoldState,
    navActions: _Module_name_NavActions,
) {
    composable(_Module_name_Nav.MainNav.List_Module_name_NavScreen.route) {
        val coroutineScope = rememberCoroutineScope()
        val viewModel: _Module_name_ViewModel = hiltViewModel()
        List_Module_name_Screen(viewModel = viewModel) { event ->
            when (event) {
                is List_Module_name_Actions.OpenMenu -> coroutineScope.launch { scaffoldState.drawerState.open() }
                is List_Module_name_Actions.Search -> viewModel.search(event.text)
                is List_Module_name_Actions.ToViewUser -> navActions.toUser(event.userId)
            }
        }
    }
    composable(
        route = _Module_name_Nav.MainNav.ViewUserNavScreen.routeWithArgument,
        arguments = listOf(
            navArgument(_Module_name_Nav.MainNav.ViewUserNavScreen.argument0) {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        backStackEntry.arguments?.let {
            val viewModel: _Module_name_ViewModel = hiltViewModel()
            val userId = it.getString(_Module_name_Nav.MainNav.ViewUserNavScreen.argument0)!!
            View_Module_name_creen(
                userId = userId,
                viewModel = viewModel,
            ) { event ->
                when (event) {
                    is ViewUserActions.RefreshView -> viewModel.getViewUser(event.userId)
                }
            }
        }
    }
}
