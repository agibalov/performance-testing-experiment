import io.gatling.core.Predef._
import io.gatling.http.Predef._

class DummySimulation extends Simulation {

  val conf = http.baseURL("http://retask.me")

  val scn = scenario("My Scenario").exec {
    repeat(100, "i") {
      exec(http("My Request #1").get("/"))
    }
  }

  setUp(scn.inject(atOnceUsers(100)))
    .protocols(conf)

}
