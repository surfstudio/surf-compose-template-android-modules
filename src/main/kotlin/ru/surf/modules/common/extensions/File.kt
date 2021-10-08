package ru.surf.modules.common.extensions

import java.io.File

fun File.getPackageFromAndroidManifest(): List<String> {
    var packet: List<String>? = null
    this.let { manifest ->
        manifest.forEachLine {
            packet?.let { return@forEachLine } ?: run {
                if (it.contains("package=\"")) {
                    packet = it.substringAfter("\"").substringBefore("\"").split(".")
                }
            }
        }
    }
    return packet ?: throw RuntimeException("Not found package in ${this.path}!")
}