package com.chinapex.api.router

import com.chinapex.api.testPrepare.FixturesPreparer
import org.scalatest.{FunSpec, Matchers}
import com.chinapex.api.resources.HelloResource
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
/**
  * Created by ning on 16-8-1.
  */
@RunWith(classOf[JUnitRunner])
class HelloTest extends FunSpec with FixturesPreparer with Matchers{

    describe("/hello test") {
        it("should return hello->world"){
            HelloResource(this).getAndValidate()
        }
    }

}
