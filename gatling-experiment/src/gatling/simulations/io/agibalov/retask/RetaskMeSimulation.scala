package io.agibalov.retask

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class RetaskMeSimulation extends Simulation {
  val conf = http.baseUrl("http://retask.me")

  val scn = scenario("Dummy scenario").repeat(100) {
    exec(
      exec(http("GET Home").get("/")),
      exec(http("HEAD Home").head("/")),
      exec(http("GET SignUp").get("/Account/SignUp")),
      exec(http("GET SignIn").get("/Account/SignIn")),
      exec(http("GET ForgotPassword").get("/Account/ForgotPassword")),
      exec(http("GET Disclaimer").get("/Home/Disclaimer")))
  }

  setUp(scn.inject(atOnceUsers(10)))
    .protocols(conf)
    .assertions(
      forAll.successfulRequests.percent.is(100),
      forAll.responseTime.percentile4.lt(300) // 300ms makes it fail for some endpoints, 1000ms is always OK
    )
}
