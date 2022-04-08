#!/bin/sh
# Compiling
kotlinc ./ -include-runtime -d compiled.jar

# Executing
java -jar compiled.jar

# Cleaning up
rm compiled.jar