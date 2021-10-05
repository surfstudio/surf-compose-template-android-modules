package ru.surf._module_name_.ui.viewModels;

import java.lang.System;

/**
 * [ViewModel] for module
 *
 * @property preferences service shared preference
 */
@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t8F\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lru/surf/_module_name_/ui/viewModels/_Module_name_ViewModel;", "Landroidx/lifecycle/ViewModel;", "preferences", "Lru/surf/_module_name_/data/preferences/_Module_name_Preferences;", "(Lru/surf/_module_name_/data/preferences/_Module_name_Preferences;)V", "_loading", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "loading", "Lkotlinx/coroutines/flow/StateFlow;", "getLoading", "()Lkotlinx/coroutines/flow/StateFlow;", "simple_debug"})
public final class _Module_name_ViewModel extends androidx.lifecycle.ViewModel {
    private final ru.surf._module_name_.data.preferences._Module_name_Preferences preferences = null;
    
    /**
     * [MutableStateFlow] for loading state
     */
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _loading = null;
    
    @javax.inject.Inject()
    public _Module_name_ViewModel(@org.jetbrains.annotations.NotNull()
    ru.surf._module_name_.data.preferences._Module_name_Preferences preferences) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getLoading() {
        return null;
    }
}