#!/bin/sh

PROCESS_NAME="RASPBERRY_J"
APP_HOME="/home/pi/raspberryJ"
SUCCESS_PHRASE="RASPBERRY_J STARTED"
LOG_FILE="$APP_HOME/$PROCESS_NAME.out"

if [ -f $APP_HOME/bin/setenv.sh ]; then
    . $APP_HOME/bin/setenv.sh
fi

START_COMMAND="sudo java -D$PROCESS_NAME $DEBUG_PARAMS $JMX_PARAMS -cp $CLASSPATH $MAIN_CLASS"

echo "*******************************"
echo "Starting $PROCESS_NAME process"
echo "*******************************"

cd $APP_HOME

$START_COMMAND > $LOG_FILE 2>&1 &
echo "$!" > PID.out
sh $APP_HOME/bin/checklog.sh $LOG_FILE $SUCCESS_PHRASE

RETVAL=$?
if [ $RETVAL -eq 0 ]; then
    echo "$PROCESS_NAME is ready"
else
    echo "$PROCESS_NAME is not available"
    rm PID.out
fi

exit $RETVAL