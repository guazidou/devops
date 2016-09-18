package com.chinapex.api.services

import com.chinapex.api.helper.JsonMapper
import com.chinapex.api.modules.{User, UserWithToken}
import org.scalatra.{ActionResult, BadRequest, Created}

/**
  * Created by ning on 16-8-9.
  */
object UserService {
    private val userPasswordMap: Map[String, String] = Map(
        "SystemAdmin" -> "Chinapex007"
    )

    private val userTokenMap = new scala.collection.mutable.HashMap[String, String]()
    userTokenMap("SystemAdmin") = ""

    private def generateToken(): Unit ={
        userTokenMap("SystemAdmin") = "alibasdfh78g7asdf8h8asudf9h"
    }

    def createUser(body: String) : ActionResult = {
        val userInfo = JsonMapper.from[User](body)
        userPasswordMap.find(a => {a._1 == "SystemAdmin" && a._2 == userInfo.password}).fold(
            BadRequest(reason = "User should be SystemAdmin and please supply correct password!")
        )(user => {
            generateToken()
            val tokenString = JsonMapper.to(UserWithToken(user._1, userTokenMap(user._1)))
            Created(body = tokenString)
        })
    }



}
