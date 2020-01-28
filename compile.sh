#! /bin/bash

if [ $1 ]
then
  javac --source-path src -d classes $1
else
  rm classes/* -rf
  javac --source-path src -d classes -cp lib/junit-4.13.jar -Xlint:deprecation tests/*.java
fi
