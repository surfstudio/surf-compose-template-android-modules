package ru.surf._module_name_.navigation.graph

import androidx.compose.material.ScaffoldState
import androidx.navigation.NavGraphBuilder
import ru.surf._module_name_.navigation.actions._module_name_NavActions
import ru.surf._module_name_.navigation.graph.impl._module_name_ScreenGraph

/**
 * Base block with graphs for module
 */
fun NavGraphBuilder._module_name_NavGraph(
    scaffoldState: ScaffoldState,
    navActions: _Module_name_NavActions,
) {
    _module_name_ScreenGraph(scaffoldState, navActions)
}
