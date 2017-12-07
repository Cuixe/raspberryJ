#!/usr/bin/env bash


PROCESS_NAME="GPIO_CONTROLLER"
HOME='/home/pi/raspberryJ'
LOG_PATH="$HOME/log"
KILLED=0

echo "Shutting down $PROCESS_NAME"

PID=`ps -fea | grep $PROCESS_NAME | grep -v grep | awk '{print $2}'`
if [ -z "$PID" ]
then
    echo "Process with name [$PROCESS_NAME] was not found."
    exit 1
fi

echo "STOPING" >> $LOG_PATH/raspberry.log
echo "Finishing process with id: [$PID]"
sudo kill -9 $PID > /dev/null 2>&1

