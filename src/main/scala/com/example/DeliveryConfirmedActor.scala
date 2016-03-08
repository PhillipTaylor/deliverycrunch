package com.example

import akka.actor.ActorLogging

import akka.actor.{Actor, ActorLogging, Props}

class DeliveryConfirmedActor extends Actor with ActorLogging {
  import DeliveryRecordActor._

  def receive = {
    case x :DeliveryRecord => println(s"** CONFIRMATION ON ${x.reference}")
    case _ => log.info("something done gone wrong")
  }
}

object DeliveryConfirmedActor {
  val props = Props[DeliveryConfirmedActor]
}
