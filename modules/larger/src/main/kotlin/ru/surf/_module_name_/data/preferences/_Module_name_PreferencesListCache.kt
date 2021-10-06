package ru.surf._module_name_.data.preferences

import android.content.SharedPreferences
import ru.surf.core.interfaces.IAppPreferences
import timber.log.Timber

/**
 * Lists service shared preference for module
 */
interface _Module_name_PreferencesListCache : IAppPreferences {

    /**
     * Store private, primitive data in key-value pairs [SharedPreferences]
     */
    override val p: SharedPreferences

    /**
     * We put the keys in enum
     */
    enum class KEYS {
        LAST_UPDATE_LIST__module_name_,
    }

    /**
     * Performed when the user logs out
     */
    override fun clearCacheAfterLogout() {
        Timber.e("Clear cache: _module_name_PreferencesListCache")
        lastUpdateList_module_name_ = 0L
    }

    /**
     * Saving list update data
     */
    var lastUpdateList_module_name_: Long
        get() = p.getLong(KEYS.LAST_UPDATE_LIST__module_name_.name, 0L)
        set(value) = p.edit().putLong(KEYS.LAST_UPDATE_LIST__module_name_.name, value).apply()
}
