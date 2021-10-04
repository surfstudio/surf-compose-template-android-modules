/**
 * Copyright 2021 Surf
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.surf.users.data.preferences

import android.content.SharedPreferences
import ru.surf.core.interfaces.IAppPreferences
import timber.log.Timber

/**
 * Lists service shared preference for module
 *
 * @author Vitaliy Zarubin
 */
interface UsersPreferencesListCache : IAppPreferences {

    /**
     * Store private, primitive data in key-value pairs [SharedPreferences]
     */
    override val p: SharedPreferences

    /**
     * We put the keys in enum
     */
    enum class KEYS {
        LAST_UPDATE_LIST_USERS,
    }

    /**
     * Performed when the user logs out
     */
    override fun clearCacheAfterLogout() {
        Timber.e("Clear cache: UsersPreferencesListCache")
        lastUpdateListUsers = 0L
    }

    /**
     * Saving list update data
     */
    var lastUpdateListUsers: Long
        get() = p.getLong(KEYS.LAST_UPDATE_LIST_USERS.name, 0L)
        set(value) = p.edit().putLong(KEYS.LAST_UPDATE_LIST_USERS.name, value).apply()
}
