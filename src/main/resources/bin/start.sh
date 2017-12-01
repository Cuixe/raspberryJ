#!/bin/bash

HOME="/home/pi/raspberryJ/raspberryJ-bin"
LIB_PATH="$HOME/lib"
PROCESS_NAME="GPIO_CONTROLLER"
DEBUG_PARAMS="-Dlog4j.debug -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=10001"
JMX_PARAMS="-Dcom.sun.management.jmxremote.port=10002 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Djava.rmi.server.hostname=localhost"
CLASS_PATH="lib/raspberry-1.0-SNAPSHOT-all.jar"
LOG_PATH="$HOME/log"

echo "MENSAJE: $CLASS_PATH"

cd "$HOME"

java -cp lib/raspberry-1.0-SNAPSHOT-all.jar org.cuixe.raspberry.Main