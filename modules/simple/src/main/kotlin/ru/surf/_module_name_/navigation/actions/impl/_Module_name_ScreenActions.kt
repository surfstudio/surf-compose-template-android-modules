package ru.surf._module_name_.navigation.actions.impl

import androidx.navigation.NavHostController
import ru.surf._module_name_.navigation.nav._Module_name_Nav
import ru.surf._module_name_.ui.screens.viewScreen._Module_name_Screen

/**
 * Custom actions for [_Module_name_Screen]
 */
interface _Module_name_ScreenActions {

    val controller: NavHostController

    fun toUsers() {
        controller.navigate(_Module_name_Nav.MainNav._Module_name_NavScreen.route)
    }
}
