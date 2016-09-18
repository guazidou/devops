package com.chinapex.api.actors

import akka.actor.ActorSystem

/**
  * Created by ning on 16-8-1.
  */
object ResourcesActor {
    var system: ActorSystem = null

    def actorOf(){
        system = ActorSystem("ResourceSystem")
    }

}
