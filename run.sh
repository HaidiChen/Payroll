#! /bin/bash

java -cp classes/:lib/junit-4.13.jar:lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore PayrollTestSuite
