package ru.surf._module_name_.data.preferences

import android.content.SharedPreferences
import ru.surf.core.interfaces.IAppPreferences
import timber.log.Timber

/**
 * Common service shared preference for module
 */
interface _Module_name_PreferencesBase : IAppPreferences {

    /**
     * Store private, primitive data in key-value pairs [SharedPreferences]
     */
    override val p: SharedPreferences

    /**
     * We put the keys in enum
     */
    enum class KEYS {
        IS_START_PAGE,
    }

    /**
     * Performed when the user logs out
     */
    override fun clearCacheAfterLogout() {
        Timber.e("Clear cache: _module_name_PreferencesBase")
    }

    /**
     * An example of a possible variable
     */
    var isStartPage: Boolean
        get() = p.getBoolean(KEYS.IS_START_PAGE.name, true)
        set(value) = p.edit().putBoolean(KEYS.IS_START_PAGE.name, value).apply()
}
