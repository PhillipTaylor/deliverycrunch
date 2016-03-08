package com.example

import akka.actor.{Actor, ActorLogging, Props}
import com.example.DeliveryRecordActor.DeliveryRecord
import com.github.tototoshi.csv._

class CSVFileReaderActor extends Actor with ActorLogging {
  import DeliveryRecordActor._

  val deliveryRecordActor = context.actorOf(DeliveryRecordActor.props, "deliveryRecordActor")

  def receive = {
    case FileDiscoveryActor.FileDiscoveredMsg(filename) => {
      // read file.. send to line processing actor...
      log.info(s"read and sending messages for each line of $filename")
      val reader = CSVReader.open(s"deliveryfiles/$filename")
      reader.foreach(fields => deliveryRecordActor ! DeliveryRecord(fields(0), fields(1), fields(2)) )
    }
    case _ => log.info("CSVFileReaderActor: Bad routing or something")
  }
}

object CSVFileReaderActor {
  val props = Props[CSVFileReaderActor]
  case class FileDiscovered(filename :String)
}
