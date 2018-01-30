#!/bin/bash

PROCESS_NAME="RASPBERRY_J"
APP_HOME="/home/pi/raspberryJ"

cd $APP_HOME

PID=""

if [ -e PID.out ]; then
    PID=`cat PID.out`
else
    echo "PID.out file not found"
    PID=`ps -fea | grep $PROCESS_NAME | grep -v grep | awk '{print $2}'`
fi

if [ -z "$PID" ]; then
    echo "Process $PROCESS_NAME[$PID] was not found."
    exit 1
else
    echo "Process $PROCESS_NAME[$PID] is running"
    exit 0