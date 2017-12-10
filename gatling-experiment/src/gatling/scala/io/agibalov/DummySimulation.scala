package io.agibalov

import io.agibalov.Predef._
import io.gatling.core.Predef._

class DummySimulation extends Simulation {
  val dummyProtocol = dummy.something("something here")

  val scn = scenario("Dummy scenario").repeat(10) {
    exec(dummy("omg").doSomething())
  }

  setUp(scn.inject(atOnceUsers(2)))
    .protocols(dummyProtocol)
}
