import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._

import scala.concurrent.Future

object Server extends App {

  implicit val sys = ActorSystem()
  implicit val mat = ActorMaterializer()
  implicit val ec = sys.dispatcher

  val serverSource: Source[Http.IncomingConnection, Future[Http.ServerBinding]] =
    Http().bind(interface = "localhost", port = 8080)

  val requestHandler: HttpRequest => HttpResponse = {
    case HttpRequest(HttpMethods.GET, Uri.Path("/hello"), _, _, _) =>
      HttpResponse(entity = HttpEntity(
        ContentTypes.`text/html(UTF-8)`,
        "<html><body>Hello world!</body></html>"))
  }

  val bindingFuture: Future[Http.ServerBinding] =
    Http().bindAndHandle(Routes.route, "localhost", 8080)


//  val bindingFuture: Future[Http.ServerBinding] =
//    serverSource.to(Sink.foreach { connection =>
//      println("Accepted new connection from " + connection.remoteAddress)
//
//      connection handleWithAsyncHandler requestHandler
//
//    }).run()

}
