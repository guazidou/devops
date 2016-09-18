package com.chinapex.api.modules

/**
  * Created by ning on 16-8-9.
  */
case class User(userName: String, password: String)

case class UserWithToken(userName: String, tokenString: String)