package ru.surf._module_name_.navigation.nav.impl

import com.keygenqt.routing.NavScreen
import com.keygenqt.routing.NavScreenWithArgument
import ru.surf._module_name_.ui.screens.list_module_name_.List_module_name_Screen
import ru.surf._module_name_.ui.screens.viewUser.View_module_name_creen

/**
 * Routing for [List_Module_name_Screen], [View_Module_name_creen]
 */
object _Module_name_NavScreen {
    val List_Module_name_NavScreen = object : NavScreen {
        override val route: String = "List_Module_name_Screen"
    }
    val ViewUserNavScreen = object : NavScreenWithArgument {
        override val argument0: String = "userId"
        override val routeWithArgument: String = "View_Module_name_creen/{$argument0}"
    }
}
