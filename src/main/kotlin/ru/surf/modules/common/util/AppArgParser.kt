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
package ru.surf.modules.common.util

import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default
import kotlinx.cli.required
import ru.surf.modules.creator.CreatorModuleType

object AppArgParser {

    private val argParser = ArgParser("Automation create modules")

    val name by argParser.option(
        ArgType.String,
        fullName = "name",
        description = "Name module"
    ).required()

    val path by argParser.option(
        ArgType.String,
        fullName = "path",
        description = "Path to android project"
    ).required()

    val isDebug by argParser.option(
        ArgType.Boolean,
        fullName = "debug",
        description = "Debug mode"
    ).default(false)

    val type by argParser.option(
        ArgType.Choice<CreatorModuleType>(),
        fullName = "type",
        description = "Type module"
    ).default(CreatorModuleType.SIMPLE)

    fun parse(args: Array<String>) {
        argParser.parse(args)
    }
}