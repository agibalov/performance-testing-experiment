# jmeter-experiment

Attempts to use [jmeter-gradle-plugin](https://github.com/jmeter-gradle-plugin/jmeter-gradle-plugin):

* `./gradlew jmGui` to run JMeter GUI.
* `./gradlew jmRun` to run JMeter tests in headless mode.
* `./gradlew jmReport` is supposed to generate the [report dashboard](http://jmeter.apache.org/usermanual/generating-dashboard.html), but this appears to be broken: jmeter-gradle-plugin/jmeter-gradle-plugin#103.

Direct JMeter CLI usage:

* `./run.sh` will run JMeter in headless mode. This will create the `./output` directory with test run results (both the CSV with sample data and the fancy report).
