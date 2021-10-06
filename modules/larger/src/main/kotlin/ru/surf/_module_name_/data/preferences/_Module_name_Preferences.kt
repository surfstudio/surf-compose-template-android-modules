package ru.surf._module_name_.data.preferences

import android.content.SharedPreferences

/**
 * Base service shared preference module
 */
class _Module_name_Preferences(override val p: SharedPreferences) :
    _Module_name_PreferencesBase,
    _Module_name_PreferencesListCache {

    /**
     * Performed when the user logs out
     */
    override fun clearCacheAfterLogout() {
        super<_Module_name_PreferencesBase>.clearCacheAfterLogout()
        super<_Module_name_PreferencesListCache>.clearCacheAfterLogout()
    }
}
