#!/bin/sh
export MAVEN_OPTS="-Xms128m -Xmx512m -XX:PermSize=128M -XX:MaxPermSize=256M -XX:PermSize=256m -XX:MaxPermSize=256m -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=4002,server=y,suspend=n"
mvn -e clean jetty:run $@
