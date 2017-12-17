#!/bin/bash

HOME="/home/pi/raspberryJ"
LIB_PATH="$HOME/lib"
PROCESS_NAME="GPIO_CONTROLLER"
DEBUG_PARAMS="-Dlog4j.debug -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=10001"
LOG_PATH="$HOME/log"
JMX_PORT="10000"
JMX_IP="192.168.100.150"
JMX_PARAMS="-Dcom.sun.management.jmxremote.port=$JMX_PORT -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Djava.rmi.server.hostname=$JMX_IP"
LIB="-cp lib/raspberry-1.0-SNAPSHOT-all.jar"
MAIN_CLASS="org.cuixe.raspberry.Principal"


cd "$HOME"

if [ ! -z "$1" ]; then
    if [ "$1" == "CLI" ]; then
        echo "STARTING CLI"
        sudo java -DCLI $LIB $MAIN_CLASS CLI
    else
        echo "STARTING GPIO_CONTROLLER"
        sudo java -DGPIO_CONTROLLER $JMX_PARAMS $LIB $MAIN_CLASS $1 $2 > log/raspberry.log &
    fi
else
    echo "STARTING GPIO_CONTROLLER"
    sudo java -DGPIO_CONTROLLER $JMX_PARAMS $LIB $MAIN_CLASS $1 $2 > log/raspberry.log &
fi
