import akka.http.scaladsl.server.Directives._

object Routes {

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
        entity(as[String]) { str =>
          complete {
            str.toUpperCase
          }

        }

      }
    }
  }
}
