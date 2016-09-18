package com.chinapex.api

import java.io.File

import com.chinapex.api.api.{ApiService, JsonFormat}
import org.slf4j.LoggerFactory

/**
  * Created by ning on 16-8-1.
  */

object Launcher extends App with ServiceLauncher {
    override val enableStaticContentCache = false
    val root = new File(new File("."), "api/src/main/webapp").getAbsolutePath


    protected def api = new ApiService with JsonFormat {
    }

    override def start(path: String, port: String) {
        val logger = LoggerFactory.getLogger("Launcher")
        logger.info("%s %s".format("Launcher:", "Starting..."))
        println("path is ......" + path)
        super.start(path, port)
    }
}
