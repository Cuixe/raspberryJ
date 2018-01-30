#!/bin/sh

if [ "x$1" = "x" ]; then
	echo "Se debe de seleccionar el archivo a inspeccionar ..."
	exit -1
fi

if [ "x$2" = "x" ]; then
	echo "Se debe insertar la palabra de éxito ..."
	exit -1
fi

LOG_FILE=$1
SUCCESS_PHRASE=$2
ERROR_PHRASE="Exception"
END_PHRASE="Killed"
TIME_WAIT=5
ATTEMPTS=12

existPhrase() {
    RESULT=`grep "$1" $LOG_FILE | cat`
    if [ "x$RESULT" = "x" ]; then
		return 0
	else
        echo "Se encontro la frase $1"
		return 1
	fi

}

checkLogFile() {
    existPhrase $END_PHRASE
    if [ $? -eq 1 ]; then
        exit -1
    fi
    existPhrase $ERROR_PHRASE
    if [ $? -eq 1 ]; then
        exit -1
    fi
    existPhrase $SUCCESS_PHRASE
    if [ $? -eq 1 ]; then
        exit 0
    fi
}

times=0
while [ 1 ]; do
	if [ $times -gt $ATTEMPTS ]; then
		echo "Se sobrepaso el numero limite de intentos:.  $times"
		tail -10 $LOG_FILE
		exit -1
	fi

	checkLogFile

    times=$((times+1))
    echo "esperando $TIME_WAIT segundos, para el intento $times"
    sleep $TIME_WAIT
done