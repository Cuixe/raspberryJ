#!/bin/sh

VERSION=""
APP_HOME="/home/pi/raspberryJ"

APP_MODE_SYNTAX="Where App_Mode is: [ start | stop | restart | smoke | status ]"
#Private functions
validateAppMode() {
	if [ $1 = "start" -o $1 = "stop" -o $1 = "restart" -o $1 = "smoke" -o $1 = "status" ];
	then
		return 0
	fi
	return 1
}

validateAppMode $1

if [ $? = 0 ]
then
	case "$1" in
		start)
			$APP_HOME/$VERSION/bin/start.sh
			RETVAL=$?
			echo "**** Return code = $RETVAL"
			;;
		stop)
			$APP_HOME/$VERSION/bin/stop.sh
			RETVAL=$?
			echo "**** Return code = $RETVAL"
			;;
		restart)
		    $APP_HOME/$VERSION/bin/stop.sh
		    if [ $WAIT_TIME -eq 0 ]
		    then
		        echo "Waiting for CFP cleaning"
                sleep 60
                echo "Cleaning done"
		    fi
			$APP_HOME/$VERSION/bin/start.sh
			RETVAL=$?
			;;
		smoke)
		    $APP_HOME/$VERSION/bin/smoke.sh
			RETVAL=$?
			;;
		status)
		    $APP_HOME/$VERSION/bin/status.sh
			RETVAL=$?
			;;
	esac
	exit $RETVAL
else
	echo "Syntax Command: $0 App_Mode"
	echo "$APP_MODE_SYNTAX"
	exit 1
fi