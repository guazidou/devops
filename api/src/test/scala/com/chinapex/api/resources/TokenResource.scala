package com.chinapex.api.resources

import com.chinapex.api.helper.JsonMapper
import com.chinapex.api.modules.UserWithToken
import org.scalatra.test.scalatest.ScalatraSuite

/**
  * Created by ning on 16-8-9.
  */
class TokenResource (protected var createFileName: String, protected val suite: ScalatraSuite)
    extends ResourceObject[String] {
    override protected val resourceUrl: String = "/token"

    override protected def validateCreateResponse(body: String): Unit ={
        val tokenResponse = JsonMapper.from[UserWithToken](body)
        tokenResponse.userName should be("SystemAdmin")
        tokenResponse.tokenString should be ("alibasdfh78g7asdf8h8asudf9h")
    }
}

object TokenResource{
    def apply(suite: ScalatraSuite, filename: String = "user.json"): TokenResource = new TokenResource(filename, suite)
}
