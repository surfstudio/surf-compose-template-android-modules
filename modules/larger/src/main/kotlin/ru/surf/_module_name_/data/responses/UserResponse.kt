package ru.surf._module_name_.data.responses

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable
import ru.surf._module_name_.data.models.UserModel

/**
 * Response for [UserModel]
 *
 * @property code identifier user
 * @property name user name fname + lname
 */
@Immutable
@Serializable
data class UserResponse(
    val code: Int,
    val name: String?,
)
