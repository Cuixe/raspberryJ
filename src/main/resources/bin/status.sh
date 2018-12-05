#!/usr/bin/env bash

PROCESS_NAME="GPIO_CONTROLLER"
HOME='/home/pi/raspberryJ'
LOG_PATH="$HOME/log"
KILLED=0

PID=`ps -fea | grep $PROCESS_NAME | grep -v grep | awk '{print $2}'`
if [ -z "$PID" ]
then
    echo "$PROCESS_NAME is not running."
    exit 1
else
    echo "$PROCESS_NAME is running with PID: $PID"
    exit 0
fi
