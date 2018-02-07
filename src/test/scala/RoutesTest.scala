import org.scalatest.{ Matchers, WordSpec }
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.http.scaladsl.server._
import Directives._

class RoutesTest extends WordSpec with Matchers with ScalatestRouteTest  {

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
      val body = "this is a body"
      Post("/entity", body) ~> routes ~> check {
        responseAs[String] shouldEqual body.toUpperCase
      }
    }
  }
}
