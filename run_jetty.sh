#!/bin/bash
export GRADLE_OPTS="-Xms2048m -Xmx2048m -XX:NewSize=1024m -XX:+PrintGCDetails -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=17002"
export root_dir=$0/..
gradle -p $root_dir jettyRun -Dprj=$(basename $(pwd))