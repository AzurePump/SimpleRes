package com.github.coaixy

import taboolib.common.platform.Plugin
import taboolib.common.platform.function.info
import taboolib.common.platform.function.releaseResourceFile

object SimpleRes : Plugin() {

    override fun onEnable() {
        info("Successfully running SimpleRes!")

        releaseResourceFile("config.yml",false)
    }
}