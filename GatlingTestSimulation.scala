package gatling

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class RecordedSimulation extends Simulation {
  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl("http://localhost:8081")
    .inferHtmlResources()
    .acceptHeader("*/*")
    .acceptEncodingHeader("gzip, deflate")

  val scn: ScenarioBuilder = scenario("pos-test")
    .exec(http("request_0")
      .get("/api/products"))
    .exec(http("request_1")
      .get("/api/products/0073525758"))
    .exec(http("request_2")
      .post("/api/cart/add/007739688X"))
    .exec(http("request_3")
      .get("/api/cart"))
    .exec(http("request_4")
      .post("/api/cart/checkout"))

  setUp(scn.inject(rampUsers(500).during(30 seconds)).protocols(httpProtocol))
}



