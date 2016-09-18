package com.chinapex.api.api

import com.chinapex.api.helper.JsonMapper
import com.chinapex.api.modules.User
import com.chinapex.api.services.UserService
import org.scalatra.FutureSupport

import scala.concurrent.ExecutionContext

/**
  * Created by ning on 16-8-1.
  */
abstract class ApiService extends ApplicationResource with FutureSupport{
    protected implicit def executor: ExecutionContext = ExecutionContext.global

    get("/hello") {
        Map("hello" -> "world!")
    }

    post("/token") {
        UserService.createUser(request.body)
    }

    protected def clazzName: String =  {
        classOf[ApiService].getName
    }

}
