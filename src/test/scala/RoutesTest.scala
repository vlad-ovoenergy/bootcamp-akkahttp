import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, WordSpec}

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
