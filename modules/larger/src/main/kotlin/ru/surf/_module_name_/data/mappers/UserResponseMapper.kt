package ru.surf._module_name_.data.mappers

import ru.surf._module_name_.data.models.UserModel
import ru.surf._module_name_.data.responses.UserResponse

/**
 * Extension for response [UserResponse]
 */
fun UserResponse.toModel(): UserModel {
    return UserModel(
        id = code.toString(),
        name = name ?: "",
    )
}

/**
 * Extension for list response [UserModel]
 *
 * @author Vitaliy Zarubin
 */
fun List<UserResponse>.toModels(): List<UserModel> {
    return map { it.toModel() }
}
