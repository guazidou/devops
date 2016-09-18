package com.chinapex.api.testPrepare

import com.chinapex.api.Launcher
import com.chinapex.api.api.{ApiService, JsonFormat}
import org.apache.http.impl.client.{CloseableHttpClient, HttpClientBuilder}
import org.scalatest.BeforeAndAfterEach
import org.scalatra.test.scalatest.ScalatraSuite

/**
  * Created by ning on 16-8-1.
  */
trait FixturesPreparer extends ScalatraSuite with BeforeAndAfterEach{
    var client: CloseableHttpClient = null

    override protected def beforeAll(){
        super.beforeAll()
        Launcher.startServer(Launcher.root, 1947)
    }

    override protected def afterAll(): Unit = {
        super.afterAll()
        Launcher.shutdown()
    }


    override protected def beforeEach(): Unit = {
        super.beforeEach()
        client = HttpClientBuilder.create().build()
        addServlet(new ApiService with JsonFormat {}, "/*")
    }

    override protected def afterEach(): Unit = {
        super.afterEach()
        client.close()
    }


}
