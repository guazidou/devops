package com.chinapex.api.resources

import com.chinapex.api.helper.{JsonMapper, UrlHelper}
import org.scalatest.Matchers
import org.scalatra.test.scalatest.ScalatraSuite
import com.chinapex.api.helper.extensions.WaitingExtensions._

import scala.io.Source

/**
  * Created by ning on 16-8-1.
  */
trait ResourceObject[REQUEST] extends Matchers{
    implicit val implicitConversion = language.implicitConversions
    implicit def stringToBytesArray(str: String): Array[Byte] = str.getBytes("UTF-8")

    protected val suite: ScalatraSuite
    protected val resourceUrl: String
    protected var createFileName: String
    protected val ok = 200
    protected val created = 201
    protected val accepted = 202
    protected val updated = 204
    protected val badRequest = 400
    protected val notFound = 404

    var location = ""
    protected var userName = "SysAdmin"

    def id = UrlHelper.idByUrl(location)


    def getAndValidate() {
        (() => suite.get(resourceUrl) {suite.status}).nullUntil(notFound) {}
        suite.get(resourceUrl, Seq.empty){
            suite.status should be(ok)
            validateGetResponse(suite.body)
        }
    }

    def createAccept(): Unit = {
        suite.post(resourceUrl, createRequest(createFileName)){
            (()=>suite.status).until(created, 200) {
                suite.status should be(created)
                validateCreateResponse(suite.body)
            }
        }
    }

    def createBadRequest(): Unit ={
        suite.post(resourceUrl, createRequest(createFileName)) {
            (()=>suite.status).until(badRequest, 200) {
                suite.status should be(badRequest)
            }
        }
    }


    protected def customize(json: String): String = json
    protected def createRequest(datafile:String) : String = {
        val json = Source.fromURL(getClass.getResource("/" + datafile)).getLines().map(_.trim()).mkString("")
        customize(json)
    }

    protected def validateGetResponse(body: String){}

    protected def validateCreateResponse(body: String){}

}
