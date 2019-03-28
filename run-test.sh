#!/bin/bash

echo "Building src.."
mvn clean install > /dev/null

echo "Extracting test files.."
tar -xf test-files.tar.gz > /dev/null

for i in $(find . -name \*.log); do
   echo "Parsing file: $i"
   cat $i | sys2log4j > /dev/null
done
if [ $? == 0 ]; then
	echo "All tests succeeded!"
else
	echo "FAILED! Syslog parser exited with non-zero exit code."
fi
rm -rf ./test-files
