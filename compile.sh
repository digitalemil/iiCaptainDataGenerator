#!/bin/bash

cd src
javac -cp "../lib/*" -target 1.6 -source 1.6 -d ../bin de/digitalemil/iicaptain/dg/*
javac -d ../bin -target 1.6 -source 1.6 de/digitalemil/iicaptain/appannie/*
cd ..
