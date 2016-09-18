package com.chinapex.api

/**
  * Created by ning on 16-8-1.
  */
import java.io.InputStream
import java.security.KeyStore
import javax.net.ssl.{KeyManagerFactory, SSLContext}
import javax.servlet.http.HttpServlet

import com.chinapex.api.actors.ResourcesActor
import org.glassfish.grizzly.http.server._
import org.glassfish.grizzly.servlet.WebappContext
import org.glassfish.grizzly.ssl.SSLEngineConfigurator
import org.glassfish.grizzly.strategies.WorkerThreadIOStrategy

trait ServiceLauncher {
  val enableStaticContentCache = true
  var server: HttpServer = null
  def start(root: String, port: String) {
    ActorsInit()
    startServer(root, Integer.parseInt(port))
    while (true) Thread.sleep(60000)}

  private def ActorsInit() = {
    ResourcesActor.actorOf()
  }

  def startServer(root: String, port: Int = 1945) {
    server = HttpServer.createSimpleServer(null, port)
    addServerHandlers(root)
    deployServers(root)
    if(port == 1943){
      server.addListener(Listener.httpsListener())
    }
    server.start()
  }

  private def deployServers(root: String) {
    server.getListener("grizzly").getTransport.setIOStrategy(WorkerThreadIOStrategy.getInstance())
    server.getServerConfiguration.setSendFileEnabled(true)
    application(root, "api", "/api", api).deploy(server)
  }

  private def addServerHandlers(root: String) {
    addServerHandler(staticContent(s"$root"),"/")
  }

  private def addServerHandler(handler: HttpHandler, mapping: String) {
    server.getServerConfiguration.addHttpHandler(handler, mapping)
  }

  def shutdown() {
    server.shutdown()
  }

  protected def api: HttpServlet

  private def application(root: String, name: String, path: String, servlet: HttpServlet) = {
    val context = new WebappContext(name,path)
    val reg = context.addServlet(name, servlet)
    reg.addMapping("/*")
    context
  }

  private def staticContent(path: String): StaticHttpHandler = {
    val handler: StaticHttpHandler = new StaticHttpHandler(path)
    handler.setFileCacheEnabled(false)
    handler
  }
}

object Listener {
  def httpsListener(): NetworkListener = {
    val resourceAsStream: InputStream = ClassLoader.getSystemResourceAsStream("resources/SSLKey")
    val keyStore = createKeyStore(resourceAsStream,"12345678".toCharArray())
    val keyManagerFactory = createKeyManagerFactory(keyStore, "12345678".toCharArray())
    createListener(createSSLEngineConfigurator(keyManagerFactory))
  }

  private def createSSLEngineConfigurator(keyManagerFactory: KeyManagerFactory): SSLEngineConfigurator = {
    val sslContext = SSLContext.getInstance("SSLv3")
    sslContext.init(keyManagerFactory.getKeyManagers(), null, null)
    new SSLEngineConfigurator(sslContext, false, false, false)
  }

  private def createKeyManagerFactory(keyStore: KeyStore, keyPassword: Array[Char]): KeyManagerFactory =  {
    val keyManagerFactory = KeyManagerFactory.getInstance("SunX509")
    keyManagerFactory.init(keyStore, keyPassword)
    keyManagerFactory
  }

  private def createKeyStore(inputStream: InputStream, keyStorePass: Array[Char]): KeyStore = {
    val keyStore = KeyStore.getInstance("JKS")
    keyStore.load(inputStream, keyStorePass)
    keyStore
  }

  private def createListener(sslEngineConfigurator: SSLEngineConfigurator): NetworkListener = {
    val listener = new NetworkListener("httpsListener", NetworkListener.DEFAULT_NETWORK_HOST, 1944)
    listener.setSecure(true)
    listener.setSSLEngineConfig(sslEngineConfigurator)
    listener
  }
}

