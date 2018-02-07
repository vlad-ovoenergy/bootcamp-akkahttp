import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._


case class Id(id: String)

object Routes {

  implicit val fm = jsonFormat1(Id)

  val route = {
    path("hello") {
      get {
        complete {
          "hello"
        }
      }
    } ~
    path("ip") {
      get {
        extractClientIP { ip =>
          complete {
            ip.toString
          }

        }
      }
    } ~
    path("method") {
      get {
        extractMethod { meth =>
          complete {
            meth.toString
          }

        }
      }
    } ~
    path("entity") {
      post {
        entity(as[Id]) { ent =>
          complete {
            Id(ent.id.toUpperCase)
          }
        }
      }
    }
  }
}
