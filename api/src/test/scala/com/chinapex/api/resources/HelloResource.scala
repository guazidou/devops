package com.chinapex.api.resources

import com.chinapex.api.helper.JsonMapper
import org.scalatra.test.scalatest.ScalatraSuite

/**
  * Created by ning on 16-8-1.
  */
class HelloResource(protected var createFileName: String, protected val suite: ScalatraSuite) extends ResourceObject[String] {
    override protected val resourceUrl: String = "/hello"
    override protected def validateGetResponse(body: String): Unit = {
        val responseBody = JsonMapper.from[Map[String,String]](body)
        responseBody.exists(_._1 == "hello") should be(true)
        responseBody("hello") should be("world!")
    }
}

object HelloResource{
    def apply(suite: ScalatraSuite): HelloResource = new HelloResource("", suite)
}
