package com.chinapex.api.helper

/**
  * Created by ning on 16-8-1.
  */
import com.fasterxml.jackson.databind.{SerializationFeature, DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.datatype.joda.JodaModule
import com.fasterxml.jackson.core.`type`.TypeReference
import java.lang.reflect.{ParameterizedType, Type}

object JsonMapper {
    private val mapper: ObjectMapper =
        (new ObjectMapper() with ScalaObjectMapper)
            .registerModule(DefaultScalaModule)
            .registerModule(new JodaModule())

    def to[T](value: T): String ={
        mapper.configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS,true)
        mapper.writeValueAsString(value)
    }

    def from[T](value: String)(implicit m: Manifest[T]): T = {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false)
        mapper.readValue[T](value,typeReference[T])
    }

    private def typeReference[T: Manifest] = new TypeReference[T] {
        override def getType = typeFromManifest(manifest[T])
    }

    private def typeFromManifest(m: Manifest[_]): Type = {
        if (m.typeArguments.isEmpty) { m.runtimeClass }
        else new ParameterizedType {
            def getRawType = m.runtimeClass
            def getActualTypeArguments = m.typeArguments.map(typeFromManifest).toArray
            def getOwnerType = null
        }
    }
}
