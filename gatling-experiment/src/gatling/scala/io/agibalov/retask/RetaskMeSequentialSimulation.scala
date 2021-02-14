package io.agibalov.retask

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class RetaskMeSequentialSimulation extends Simulation {
  val conf = http.baseUrl("http://retask.me")

  val getHomeScenario = scenario("GET Home").repeat(100) {
    exec(http("GET Home").get("/"))
  }

  val headHomeScenario = scenario("HEAD Home").repeat(100) {
    exec(http("HEAD Home").get("/"))
  }

  val getSignUp = scenario("GET SignUp").repeat(100) {
    exec(http("GET SignUp").get("/Account/SignUp"))
  }

  val getSignIn = scenario("GET SignIn").repeat(100) {
    exec(http("GET SignIn").get("/Account/SignIn"))
  }

  setUp(
    List(
      getHomeScenario.inject(atOnceUsers(10)),
      headHomeScenario.inject(atOnceUsers(10)),
      getSignUp.inject(atOnceUsers(10)),
      getSignIn.inject(atOnceUsers(10))
    ).reduce((a, b) => a.andThen(b))
  )
    .protocols(conf)
    .assertions(
      forAll.successfulRequests.percent.is(100),
      forAll.responseTime.percentile4.lt(300) // 300ms makes it fail for some endpoints, 1000ms is always OK
    )
}
