package com.chinapex.api.helper

import java.util.Properties

/**
  * Created by ning on 16-8-10.
  */
object ConfigurationHelper {
    var dbUser = ""
    var dbPassword = ""
    var dbIpAddress = ""

    private def loadConfigs(): Unit ={
        val properties: Properties = new Properties()
        properties.load(getClass.getClassLoader.getResourceAsStream("config.properties"))
        dbUser = properties.getProperty("dbUser")
        dbPassword = properties.getProperty("dbPassword")
        dbIpAddress = properties.getProperty("dbIpAddress")
    }

}
