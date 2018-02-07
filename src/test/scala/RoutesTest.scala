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
    "return bye " in {
      Get("/bye") ~> routes ~> check {
        responseAs[String] shouldEqual "bye"
      }
    }
  }
}
