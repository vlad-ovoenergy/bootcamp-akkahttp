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
    path("bye") {
      get {
        complete {
          "bye"
        }
      }
    }
  }
}
