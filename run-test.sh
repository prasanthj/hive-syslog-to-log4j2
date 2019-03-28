#!/bin/bash

mvn clean install

find . -name *.log | xargs cat | sys2log4j
if [ $? == 0 ]; then
	echo "All tests succeeded!"
else
	echo "FAILED! Syslog parser exited with non-zero exit code."
fi
