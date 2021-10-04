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
package ru.surf.modules

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import org.koin.core.context.startKoin
import org.slf4j.LoggerFactory
import ru.surf.modules.common.base.toBlue
import ru.surf.modules.common.base.toGreen
import ru.surf.modules.common.base.toYellow
import ru.surf.modules.common.di.moduleServicesDI
import ru.surf.modules.common.util.AppArgParser
import ru.surf.modules.creator.Creator

fun main(args: Array<String>) {

    AppArgParser.parse(args)

    startKoin {
        // logger
        modules(
            moduleServicesDI,
        )
    }

    // logger
    (LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as Logger).apply {
        level = if (AppArgParser.isDebug) Level.DEBUG else Level.OFF
    }

    println(
        """| Welcome to ${"Automation".toGreen()} ${"create".toYellow()} modules!
           | """
            .trimMargin("|")
    )

    Creator.run(
        path = AppArgParser.path,
        name = AppArgParser.name,
        type = AppArgParser.type,
    )
}