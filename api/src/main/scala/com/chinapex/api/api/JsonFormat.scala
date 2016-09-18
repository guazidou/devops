package com.chinapex.api.api

import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json.NativeJsonSupport

/**
  * Created by ning on 16-8-1.
  */
trait JsonFormat extends NativeJsonSupport{
    protected implicit val jsonFormats: Formats = DefaultFormats

    before() {
        contentType = formats("json")
    }

}
