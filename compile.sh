#! /bin/bash

javac --source-path src -d classes -cp lib/junit-4.13.jar -Xlint:deprecation tests/PayrollTest.java
