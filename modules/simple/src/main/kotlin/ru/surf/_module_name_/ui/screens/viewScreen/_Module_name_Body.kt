package ru.surf._module_name_.ui.screens.viewScreen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.insets.systemBarsPadding
import com.keygenqt.accompanist.MainScaffoldSearch
import ru.surf._module_name_.ui.actions._Module_name_Actions
import ru.surf.core.R
import ru.surf.core.compose.TopBarContentTitle
import ru.surf.core.theme.MainAppTheme

/**
 * Body page [_Module_name_Screen]
 *
 * @param loading state call query to api
 * @param onActions actions page
 */
@Composable
fun _Module_name_Body(
    loading: Boolean = false,
    onActions: (_Module_name_Actions) -> Unit = {},
) {
    MainScaffoldSearch(
        modifier = Modifier.systemBarsPadding(),
        navigationIcon = Icons.Default.Menu,
        navigationIconOnClick = { onActions(_Module_name_Actions.OpenMenu) },
        contentTitle = {
            TopBarContentTitle("_Module_name_")
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = "_Module_name_"
            )
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
