import akka.http.scaladsl.model.ContentTypes.`application/json`
import akka.http.scaladsl.model._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, WordSpec}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._


class RoutesTest extends WordSpec with Matchers with ScalatestRouteTest  {

  implicit val fm = jsonFormat1(Id)

  val routes = Routes.route

  "This service " should {
    "return hello " in {
      Get("/hello") ~> routes ~> check {
        responseAs[String] shouldEqual "hello"
      }
    }
    "return ip " in {
      Get("/ip") ~> routes ~> check {
        responseAs[String] shouldEqual "unknown"
      }
    }
    "return request method " in {
      Get("/method") ~> routes ~> check {
        responseAs[String] shouldEqual "HttpMethod(GET)"
      }
    }
    "return posted string in upper case " in {

      Post("/entity", HttpEntity(`application/json`, """{ "id": "hello"}""")) ~> routes ~> check {
        responseAs[Id] shouldEqual Id("HELLO")
      }
    }
  }
}
