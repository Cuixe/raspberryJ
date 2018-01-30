#!/bin/sh

APP_HOME="/home/pi/raspberryJ"
JMX_PORT="10000"
JMX_IP="192.168.100.150"
DEBUG_PORT="10001"
LIB_PATH="$APP_HOME/libs"

export JMX_PARAMS="-Dcom.sun.management.jmxremote.port=$JMX_PORT -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Djava.rmi.server.hostname=$JMX_IP"
export DEBUG_PARAMS="-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=$DEBUG_PORT"
export MAIN_CLASS="org.cuixe.raspberry.Principal"

CLASSPATH="$APP_HOME/etc"
for i in `ls -ltrh $LIB_PATH | awk -F' ' '{print $9}'`
do
	CLASSPATH=$CLASSPATH:$LIB_PATH/$i;
done
export CLASSPATH=$CLASSPATH