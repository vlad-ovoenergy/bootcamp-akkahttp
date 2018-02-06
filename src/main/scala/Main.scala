import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpMethods, HttpRequest}
import akka.stream.ActorMaterializer

import scala.concurrent.{Await, Promise}
import scala.util.{Failure, Success}
import scala.concurrent.duration._

import akka.http.scaladsl.unmarshalling.Unmarshal

object Main extends App {

  implicit val system = ActorSystem("vlad")
  implicit val ec = system.dispatcher
  implicit val mat = ActorMaterializer()

  val site = Http().singleRequest(HttpRequest(HttpMethods.GET, "https://stackoverflow.com/questions/32315789/akka-httpresponse-read-body-as-string-scala"))


//  site onComplete {
//    case Success(html) => {
//      html.entity.toStrict(20 seconds)
//        .map(_.data)
//        .map(_.utf8String)
//        .map(s => s.split(" ").filter(_.contains("href=\"https:")))
//        .filter(!_.isEmpty)
//        .map(x => x.mkString("\n"))
//        .map( x => println(x))
//    }
//    case Failure(f) => println(f)
//  }


  site onComplete {
    case Success(html) => {
      Unmarshal(html.entity).to[String]
        .map(s => s.split(" ").filter(_.contains("href=\"https:")))
        .filter(!_.isEmpty)
        .map(x => x.mkString("\n"))
        .map( x => println(x))
    }
    case Failure(f) => println(f)
  }



}
