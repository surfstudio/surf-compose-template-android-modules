package ru.surf._module_name_.data.preferences

import android.content.SharedPreferences
import ru.surf.core.interfaces.IAppPreferences

/**
 * Base service shared preference module
 */
class _Module_name_Preferences(override val p: SharedPreferences) : IAppPreferences {

    /**
     * Performed when the user logs out
     */
    override fun clearCacheAfterLogout() {
    }
}
