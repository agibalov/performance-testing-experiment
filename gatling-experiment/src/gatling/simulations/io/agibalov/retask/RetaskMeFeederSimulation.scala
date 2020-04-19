package io.agibalov.retask

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class RetaskMeFeederSimulation extends Simulation {
  val records = csv("retask-urls.csv").readRecords

  val scn = scenario("Try All URLs")
    .foreach(records, "record") { // for each record loaded from csv
      exec(flattenMapIntoAttributes("${record}"))
        .repeat(10) { // number of times each request is tested
          exec(http("GET ${url}")
            .get("${url}"))
        }
    }

  setUp(scn.inject(atOnceUsers(3))) // number of concurrent users
    .protocols(http.baseUrl("http://retask.me"))
}
