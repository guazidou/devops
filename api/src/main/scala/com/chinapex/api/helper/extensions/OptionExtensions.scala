package com.chinapex.api.helper.extensions

/**
  * Created by ning on 16-8-1.
  */
object OptionExtensions {

    implicit class OptionExtension[T](option: Option[T]) {
        def getStringOrEmpty(f: T => String): String = {
            getStringOr(f, "")
        }

        def getStringOr(f: T => String, default: String = ""): String = {
            option.map(o => f(o)).getOrElse("")
        }

        def whenExist(f: T => Unit): Unit = {
            option.foreach(o => f(o))
        }
    }
}

object ListExtensions {
    implicit class ListExtension[T](list: List[T]) {
        def whenExist(f: List[T] => Unit): Unit = {
            if (list.nonEmpty) f(list)
        }
    }
}

