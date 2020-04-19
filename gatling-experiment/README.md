# gatling-experiment

* `./gradlew clean gatlingRun` to run all Gatling simulations.
* `./gradlew clean gatlingRun-io.agibalov.retask.RetaskMeSimulation` to run `RetaskMeSimulation`. This one illustrates how to test HTTP.
* `./gradlew clean gatlingRun-io.agibalov.retask.RetaskMeFeederSimulation` to run `RetaskMeFeederSimulation`. This one loads the target URLs from `retask-urls.csv`.
* `./gradlew clean gatlingRun-io.agibalov.DummySimulation` to run `DummySimulation`. This one illustrates how to use custom protocol (how to test arbitrary code).
