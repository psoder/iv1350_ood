#!/bin/sh
kotlinc -cp $(ls -d */) *.kt -include-runtime -d compiled.jar
java -jar compiled.jar
rm compiled.jar