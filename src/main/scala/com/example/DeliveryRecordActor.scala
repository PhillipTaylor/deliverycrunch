package com.example

import akka.actor.{Actor, ActorLogging, Props}

class DeliveryRecordActor extends Actor with ActorLogging {
  import DeliveryRecordActor._

  val deliveryConfirmedActor = context.actorOf(DeliveryConfirmedActor.props, "deliveryConfirmedActor")

  def receive = {
    //case CSVLine
    case record :DeliveryRecord => {
      println(s"Event ${record.event} at ${record.timestamp} for ${record.reference}")
      if (record.event == "DELIVERED")
        deliveryConfirmedActor ! record
    }
    case _ => log.info("something has gone wrong...")
  }
}

object DeliveryRecordActor {
  val props = Props[DeliveryRecordActor]
  case class DeliveryRecord(timestamp :String, reference :String, event :String)
}