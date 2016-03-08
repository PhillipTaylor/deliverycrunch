package com.example

import akka.actor.{Actor, ActorLogging, Props}
import java.io.File

class FileDiscoveryActor extends Actor with ActorLogging {
  import FileDiscoveryActor._

  val csvFileReaderActor = context.actorOf(CSVFileReaderActor.props, "csvFileActor")

  def receive = {
    case Initialise =>
      log.info("File Discovery Actor doing its thing!")
      // send message to CSVFileReader message...
      for (filename :String <- new File("deliveryfiles").list())
        csvFileReaderActor ! FileDiscoveredMsg(filename)
    case _ =>
      log.info("FileDiscoveryActor: bad routing or something")
  }

}

object FileDiscoveryActor {
  val props = Props[FileDiscoveryActor]
  case object Initialise
  case class FileDiscoveredMsg(filename :String)
}