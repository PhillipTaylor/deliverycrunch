package com.example

import akka.actor.ActorSystem

object ApplicationMain extends App {
  val system = ActorSystem("MyActorSystem")
  //val pingActor = system.actorOf(PingActor.props, "pingActor")
  //pingActor ! PingActor.Initialize

  val fileDiscoveryActor = system.actorOf(FileDiscoveryActor.props, "fileDiscoveryActor")
  fileDiscoveryActor ! FileDiscoveryActor.Initialise

  // This example app will ping pong 3 times and thereafter terminate the ActorSystem - 
  // see counter logic in PingActor
  system.awaitTermination()
}