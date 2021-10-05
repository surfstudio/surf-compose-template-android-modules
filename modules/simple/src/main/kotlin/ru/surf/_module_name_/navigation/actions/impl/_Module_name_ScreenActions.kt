package ru.surf._module_name_.navigation.actions.impl

import androidx.navigation.NavHostController
import ru.surf.users.navigation.nav.UsersNav
import ru.surf.users.ui.screens.listUsers.ListUsersScreen
import ru.surf.users.ui.screens.viewUser.ViewUserScreen

/**
 * Custom actions for [_Module_name_Screen]
 */
interface _Module_name_ScreenActions {

    val controller: NavHostController

    fun toUsers() {
        controller.navigate(_Module_name_Nav.MainNav._Module_name_NavScreen.route)
    }
}
