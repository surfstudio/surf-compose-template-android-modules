package ru.surf._module_name_.di;

import java.lang.System;

/**
 * Dagger Module base for module (_module_name_)
 */
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006\u0007"}, d2 = {"Lru/surf/_module_name_/di/ModuleDefault;", "", "()V", "provide_Module_name_Preferences", "Lru/surf/_module_name_/data/preferences/_Module_name_Preferences;", "corePreferences", "Lru/surf/core/data/preferences/CorePreferences;", "simple_debug"})
@dagger.Module()
public final class ModuleDefault {
    @org.jetbrains.annotations.NotNull()
    public static final ru.surf._module_name_.di.ModuleDefault INSTANCE = null;
    
    private ModuleDefault() {
        super();
    }
    
    /**
     * Shared preferences service
     */
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    public final ru.surf._module_name_.data.preferences._Module_name_Preferences provide_Module_name_Preferences(@org.jetbrains.annotations.NotNull()
    ru.surf.core.data.preferences.CorePreferences corePreferences) {
        return null;
    }
}