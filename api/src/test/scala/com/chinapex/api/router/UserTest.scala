package com.chinapex.api.router

import com.chinapex.api.resources.{HelloResource, TokenResource}
import com.chinapex.api.testPrepare.FixturesPreparer
import org.junit.runner.RunWith
import org.scalatest.{FunSpec, Matchers}
import org.scalatest.junit.JUnitRunner

/**
  * Created by ning on 16-8-9.
  */
@RunWith(classOf[JUnitRunner])
class UserTest extends FunSpec with FixturesPreparer with Matchers{

    describe("/user test") {
        it("should return user and token"){
            TokenResource(this).createAccept()
        }

        it("should return bad request when password is wrong"){
            TokenResource(this, "user_wrong_password.json").createBadRequest()
        }
    }

}
