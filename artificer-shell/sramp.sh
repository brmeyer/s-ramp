#!/bin/bash

SCRIPTDIR=$(dirname $0)
cd $SCRIPTDIR

mvn -Dexec.mainClass=SrampShell exec:java