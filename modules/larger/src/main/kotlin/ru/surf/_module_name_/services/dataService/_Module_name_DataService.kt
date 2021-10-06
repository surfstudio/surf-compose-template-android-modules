package ru.surf._module_name_.services.dataService

import ru.surf._module_name_.base._module_name_Database
import ru.surf._module_name_.services.dataService.impl.UserModelDataService

/**
 * Base service module for work with db room
 */
class _module_name_DataService(
    override val db: _module_name_Database,
) : UserModelDataService
