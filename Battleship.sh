#!/bin/bash

javac lecha/damien/BattleShip.java 
java lecha.damien.BattleShip 
find . -type f -path "./lecha/damien/*" -name "*.class" -exec rm -f {} \;
find . -type f -path "./lecha/damien/*/*" -name "*.class" -exec rm -f {} \;
find . -type f -path "./lecha/damien/*/*/*" -name "*.class" -exec rm -f {} \;
find . -type f -path "./lecha/damien/*/*/*/*" -name "*.class" -exec rm -f {} \;
