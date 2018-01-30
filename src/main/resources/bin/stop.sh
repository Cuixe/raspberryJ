#!/bin/sh

PROCESS_NAME="RASPBERRY_J"
APP_HOME="/home/pi/raspberryJ"
LOG_FILE="${PROCESS_NAME}.out"

cd $APP_HOME

PID=""
if [ -e PID.out ]; then
    PID=`cat PID.out`
else
    PID=`ps -fea | grep $PROCESS_NAME | grep -v grep | awk '{print $2}'`
fi
if [ -z "$PID" ]; then
    echo "Process with name [$PROCESS_NAME] was not found."
    exit 1
fi
echo "SHUTING DOWN $PROCESS_NAME" > $LOG_FILE
echo "************************************"
echo "STOPING $PROCESS_NAME WITH ID: $PID"
echo "************************************"
kill -9 $PID > /dev/null 2>&1