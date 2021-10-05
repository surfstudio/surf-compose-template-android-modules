package ru.surf._module_name_.navigation.graph

import androidx.compose.material.ScaffoldState
import androidx.navigation.NavGraphBuilder
import ru.surf.users.navigation.actions.UsersNavActions
import ru.surf.users.navigation.graph.impl.usersScreenGraph

/**
 * Base block with graphs for module
 */
fun NavGraphBuilder._module_name_NavGraph(
    scaffoldState: ScaffoldState,
    navActions: _Module_name_NavActions,
) {
    _module_name_ScreenGraph(scaffoldState, navActions)
}
