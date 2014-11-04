#!/bin/bash

# clean
rm -rf build/

# compile
mkdir -p build/classes
javac src/*.java -d build/classes
