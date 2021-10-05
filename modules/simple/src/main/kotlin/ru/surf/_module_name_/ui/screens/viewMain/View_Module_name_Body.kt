package ru.surf._module_name_.ui.screens.viewUser

import android.content.res.Configuration
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.insets.systemBarsPadding
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.keygenqt.accompanist.MainScaffoldSearch
import com.keygenqt.modifier.paddingLarge
import com.keygenqt.modifier.sizeLarge
import ru.surf.core.compose.AppSwipeRefreshIndicator
import ru.surf.core.compose.LoaderPage
import ru.surf.core.compose.PageNotFound
import ru.surf.core.compose.TopBarContentTitle
import ru.surf.core.theme.MainAppTheme
import ru.surf.users.data.mock.userModelMock
import ru.surf.users.data.models.UserModel
import ru.surf.users.ui.actions.ViewUserActions

/**
 * Body page [_Module_name_Screen]
 *
 * @param loading state call query to api
 * @param onActions actions page
 */
@Composable
fun View_Module_name_Body(
    loading: Boolean = false,
    onActions: (_Module_name_Actions) -> Unit = {},
) {
    MainScaffoldSearch(
        modifier = Modifier.systemBarsPadding(),
        contentTitle = {
            TopBarContentTitle("_Module_name_")
        },
    ) {
        SwipeRefresh(
            state = rememberSwipeRefreshState(loading),
            onRefresh = {
                onActions(ViewUserActions.RefreshView(id))
            },
            indicator = { st, tr ->
                AppSwipeRefreshIndicator(st, tr)
            },
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {

            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PIXEL_4)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.NEXUS_6)
@Composable
private fun Preview() {
    MainAppTheme {
        Scaffold {
            _Module_name_Body()
        }
    }
}
