# gatling-experiment

Learning to use Gatling.

* `./gradlew clean gatlingRun` to run all Gatling simulations.
* `./gradlew clean gatlingRun-io.agibalov.retask.RetaskMeFeederSimulation` to run `RetaskMeFeederSimulation`. This one loads the target URLs from `retask-urls.csv`.
* `./gradlew clean gatlingRun-io.agibalov.DummySimulation` to run `DummySimulation`. This one illustrates how to use custom protocol (how to test arbitrary code).

# Assertions

`./gradlew clean gatlingRun-io.agibalov.retask.RetaskMeSimulation` to run `RetaskMeSimulation`. 

This one illustrates how to test HTTP. It also illustrates how [Gatling assertions](https://gatling.io/docs/current/general/assertions) work. 

* There's an "Assertions" section in the Gatling HTML report. 
* Gatling produces the JUnit XML (`build/reports/gatling/**/js/assertions.xml`) per-endpoint-per-assertion ok/nok results.
* Gradle `gatlingRun` task fails if any of the assertions fail.
