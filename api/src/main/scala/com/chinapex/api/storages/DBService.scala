package com.chinapex.api.storages

import com.chinapex.api.helper.ConfigurationHelper
import scalikejdbc._

/**
  * Created by ning on 16-8-9.
  */
object DBService {
    connect()
    def connect() = {
        Class.forName("com.mysql.jdbc.Driver")
        val dbUrl: String = "jdbc:mysql://" + ConfigurationHelper.dbIpAddress + "/scala?characterEncoding=utf8"
        ConnectionPool.singleton(dbUrl, ConfigurationHelper.dbUser, ConfigurationHelper.dbPassword,
            ConnectionPoolSettings(10, 100))
    }

    def execute(cmd:SQL[Nothing, NoExtractor]) : Int = {
        val returnCode: Int = -1
        tryWith(returnCode){
            using(DB(ConnectionPool.borrow())){
                db => db.localTx{
                    implicit session => {
                        cmd.update().apply()}
                }
            }
        }
    }

    def executeWithAutoIncKey(cmd:SQL[Nothing, NoExtractor]) : Long = {
        val returnCode: Long = -1
        tryWith(returnCode){
            using(DB(ConnectionPool.borrow())){
                db => db.localTx{
                    implicit session => {
                        cmd.updateAndReturnGeneratedKey.apply}
                }
            }
        }
    }

    def readOnly(cmd:SQL[Nothing, NoExtractor]) : List[Map[String, Any]] = {
        val returnCode: List[Map[String, Any]] = List()
        tryWith(returnCode){
            using(DB(ConnectionPool.borrow())){
                db => db.readOnly{
                    implicit session => {
                        cmd.map(_.toMap()).list().apply()}
                }
            }
        }
    }

    def tryWith[A](returnCode: A)(f: =>A):A = {
        try {
            f
        } catch {
            case e: Throwable => {
                println(e.getClass + "  " + e.getMessage + e.getCause)
                returnCode
            }
        }
    }
}
