package me.retask;

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class RetaskMeGetIndexSimulation extends Simulation {
  val conf = http.baseURL("http://retask.me")

  val scn = scenario("Get /").exec {
    repeat(100, "i") {
      exec(http("Get /").get("/"))
    }
  }

  setUp(scn.inject(atOnceUsers(10)))
    .protocols(conf)
}
