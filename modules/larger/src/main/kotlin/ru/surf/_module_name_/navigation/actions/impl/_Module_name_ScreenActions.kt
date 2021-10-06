package ru.surf._module_name_.navigation.actions.impl

import androidx.navigation.NavHostController
import ru.surf._module_name_.navigation.nav._module_name_Nav
import ru.surf._module_name_.ui.screens.list_module_name_.List_module_name_Screen
import ru.surf._module_name_.ui.screens.viewUser.View_module_name_creen

/**
 * Custom actions for [List_Module_name_Screen], [View_Module_name_creen]
 */
interface _Module_name_ScreenActions {

    val controller: NavHostController

    fun to_Module_name_() {
        controller.navigate(_Module_name_Nav.MainNav.List_Module_name_NavScreen.route)
    }

    fun toUser(userId: String) {
        _Module_name_Nav.MainNav.ViewUserNavScreen.apply {
            controller.navigate(getRoute(userId))
        }
    }
}
