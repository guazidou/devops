package com.chinapex.api.helper

/**
  * Created by ning on 16-8-1.
  */
object UrlHelper {
    def idByUrl(ref: String): String = {
        ref.split("/").last
    }
    var toUrl: String => String = s => s
}
