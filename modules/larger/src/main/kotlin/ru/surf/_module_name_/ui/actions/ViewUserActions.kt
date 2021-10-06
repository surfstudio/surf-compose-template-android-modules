package ru.surf._module_name_.ui.actions

import ru.surf._module_name_.ui.screens.viewUser.View_module_name_creen

/**
 * Actions sealed class for screen [View_module_name_creen]
 */
sealed class ViewUserActions {

    /**
     * Update model data on page and db
     *
     * @param userId user identifier
     */
    data class RefreshView(val userId: String) : ViewUserActions()
}
