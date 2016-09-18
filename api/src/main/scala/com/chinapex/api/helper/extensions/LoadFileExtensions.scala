package com.chinapex.api.helper.extensions

/**
  * Created by ning on 16-8-1.
  */
import scala.io.Source

object LoadFileExtensions {
    def loadDataFromFile(datafile:String) : String = {
        Source.fromURL(getClass.getResource("/" + datafile)).getLines().mkString("")
    }
}
