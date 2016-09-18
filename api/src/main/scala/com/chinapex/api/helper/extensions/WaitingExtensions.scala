package com.chinapex.api.helper.extensions

/**
  * Created by ning on 16-8-1.
  */
object WaitingExtensions {

    implicit class Wait[T](getter: () => T) {
        val defaultTimer = 40

        def until(value: T, timeoutSeconds: Int = defaultTimer)(next: => Unit) {
            waitFor(value, timeoutSeconds, value.equals(getter()))(next)
        }

        def nullUntil(value: T, timeoutSeconds: Int = defaultTimer)(next: => Unit) {
            waitFor(value, timeoutSeconds, value != getter())(next)
        }

        private def waitFor(value: T, timeoutSeconds: Int, condition: => Boolean)(next: => Unit) {
            for (i <- 0 to timeoutSeconds) {
                if (condition) {
                    next
                    return
                }
                Thread.sleep(50)
            }
            next
        }
    }

    implicit class GetterWait[T](getter: () => Option[T]) {
        def getUntil(default: T, timeoutSeconds: Int = 20): T = {
            for (i <- 0 to timeoutSeconds) {
                val value = getter()
                if (value.isDefined) {
                    return value.get
                }
                Thread.sleep(100)
            }
            default
        }
    }

}

