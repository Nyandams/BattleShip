#!/bin/bash

javac lecha/damien/TestIA.java 
java lecha.damien.TestIA
find . -type f -path "./lecha/damien/*" -name "*.class" -exec rm -f {} \;
find . -type f -path "./lecha/damien/*/*" -name "*.class" -exec rm -f {} \;
find . -type f -path "./lecha/damien/*/*/*" -name "*.class" -exec rm -f {} \;
find . -type f -path "./lecha/damien/*/*/*/*" -name "*.class" -exec rm -f {} \;
