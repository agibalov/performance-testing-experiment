JMETER_PATH=~/apache-jmeter-3.3
rm -rf report
mkdir output
mkdir output/report
${JMETER_PATH}/bin/jmeter -n -t src/test/jmeter/RetaskMeTestPlan.jmx -JnumberOfRepetitions=30 -l output/RetaskMeTestPlan.csv -e -o output/report
