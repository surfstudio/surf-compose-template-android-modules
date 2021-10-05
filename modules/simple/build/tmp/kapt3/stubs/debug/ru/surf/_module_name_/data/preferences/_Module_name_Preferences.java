package ru.surf._module_name_.data.preferences;

import java.lang.System;

/**
 * Base service shared preference module
 */
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\t"}, d2 = {"Lru/surf/_module_name_/data/preferences/_Module_name_Preferences;", "Lru/surf/core/interfaces/IAppPreferences;", "p", "Landroid/content/SharedPreferences;", "(Landroid/content/SharedPreferences;)V", "getP", "()Landroid/content/SharedPreferences;", "clearCacheAfterLogout", "", "simple_debug"})
public final class _Module_name_Preferences implements ru.surf.core.interfaces.IAppPreferences {
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences p = null;
    
    public _Module_name_Preferences(@org.jetbrains.annotations.NotNull()
    android.content.SharedPreferences p) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.content.SharedPreferences getP() {
        return null;
    }
    
    /**
     * Performed when the user logs out
     */
    @java.lang.Override()
    public void clearCacheAfterLogout() {
    }
}