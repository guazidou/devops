package com.chinapex.api.modules

/**
  * Created by ning on 16-8-10.
  */
case class DockerNode(name: String, address: String, info: NodeInfo)

case class NodeInfo(Containers: Int,
                    ContainersRunning: Int,
                    ContainersPaused: Int,
                    ContainersStopped: Int,
                    Images:Int,
                    NCPU:Int,
                    MemTotal:Int
                   )
