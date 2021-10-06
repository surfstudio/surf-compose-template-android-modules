package ru.surf._module_name_.ui.actions

import ru.surf._module_name_.ui.screens.list_module_name_.List_module_name_Screen

/**
 * Actions sealed class for screen [List_module_name_Screen]
 */
sealed class List_module_name_Actions {

    /**
     * Open main menu
     */
    object OpenMenu : List_module_name_Actions()

    /**
     * Find models by name
     *
     * @param text search text for query
     */
    class Search(val text: String?) : List_module_name_Actions()

    /**
     * Open page view user
     *
     * @param userId user identifier
     */
    class ToViewUser(val userId: String) : List_module_name_Actions()
}
