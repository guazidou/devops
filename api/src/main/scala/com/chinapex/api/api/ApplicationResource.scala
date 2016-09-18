package com.chinapex.api.api

import javax.servlet.http.{HttpServletRequest, HttpServletResponse}

import com.chinapex.api.helper.UrlHelper
import com.fasterxml.jackson.core.JsonProcessingException
import org.scalatra.ScalatraServlet
import org.slf4j.{Logger, LoggerFactory}

import scala.compat.Platform.EOL

/**
  * Created by ning on 16-8-1.
  */
abstract class ApplicationResource extends ScalatraServlet with JsonFormat{
    private val logger: Logger = LoggerFactory.getLogger(clazzName)
    private final val LOG_INFO_FORMAT: String = "%s %d %s %d %d"

    protected def clazzName: String

    override def service(request: HttpServletRequest, response: HttpServletResponse) {
        UrlHelper.toUrl = toUrl
        tryWith(request,response) {
            super.service(request, response)
        }
    }

    private def tryWith(req: HttpServletRequest, rsp: HttpServletResponse)(f: => Unit) {
        val begin: Long = System.currentTimeMillis()
        try{
            f
        }catch{
            case ex: JsonProcessingException => logError(ex){ rsp.setStatus(400)}
            case ex: Exception => logError(ex){rsp.setStatus(500)}
        }finally{
            writeLog(req, rsp.getStatus, begin)
        }
    }

    private def writeLog(req: HttpServletRequest, status: Int, beginTime: Long) = {
        val cost: Long = System.currentTimeMillis() - beginTime
        logger.info(LOG_INFO_FORMAT.format(req.getMethod, beginTime, req.getRequestURI, status, cost))
    }

    protected def toUrl(uri: String) : String = {
        url(uri)
    }

    private def logError(ex: Exception)(result: => Unit){
        logger.error(ex.getStackTrace.mkString(EOL, "\n\tat ", ""))
        result
    }

}
