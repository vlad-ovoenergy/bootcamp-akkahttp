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
  }
}
