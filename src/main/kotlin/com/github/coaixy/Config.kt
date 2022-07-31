package com.github.coaixy

import org.bukkit.Location
import org.bukkit.configuration.file.YamlConfiguration
import taboolib.common.io.newFile
import taboolib.common.platform.function.getDataFolder

val selectMap = mutableMapOf<String, Location>()

val Config_Object:YamlConfiguration
get(){
    val path: String = getDataFolder().path + "\\config.yml"
    return YamlConfiguration.loadConfiguration(newFile(path, create = false, folder = false))
}
val HELP_INFO:List<String>
get() {
    return Config_Object.getStringList("help-info")
}

val SUCCESS_INFO: String?
    get() = Config_Object.getString("success-info")
val Fail_INFO: String?
    get() = Config_Object.getString("fail-info")

val PRICE:Double
    get() = Config_Object.getDouble("price")

val MAX_HEIGHT:Int
    get() = Config_Object.getInt("max-height")
val MAX_WIDTH:Int
    get() = Config_Object.getInt("max-width")
val MAX_LONG:Int
    get() = Config_Object.getInt("max-long")

val MIN_HEIGHT:Int
    get() = Config_Object.getInt("min-height")
val MIN_WIDTH:Int
    get() = Config_Object.getInt("min-width")
val MIN_LONG:Int
    get() = Config_Object.getInt("min-long")

val SELECT_INFO:List<String>
    get() = Config_Object.getStringList("select-info")