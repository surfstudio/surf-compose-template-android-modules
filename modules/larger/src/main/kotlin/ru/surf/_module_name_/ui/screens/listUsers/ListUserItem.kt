package ru.surf._module_name_.ui.screens.list_module_name_

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.surf._module_name_.data.models.UserModel
import ru.surf._module_name_.ui.actions.List_module_name_Actions

/**
 * Item list for page [List_module_name_Body]
 *
 * @param model room data model
 * @param onActions actions page
 */
@Composable
fun ListUserItem(
    model: UserModel,
    onActions: (List_module_name_Actions) -> Unit = {},
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .clickable {
                onActions(List_module_name_Actions.ToViewUser(model.id))
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 9.dp, bottom = 12.dp, start = 12.dp, end = 6.dp)
        ) {
            Text(
                text = model.name.uppercase(),
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}
