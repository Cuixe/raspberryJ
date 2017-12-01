#!/bin/bash

sendScript() {
    echo "Sending deploy script"
    scp deploy.sh raspberry1:./
    ssh raspberry1 'chmod +x /home/pi/deploy.sh'
    echo "DONE Sending deploy script"
}

publishBinary() {
    sendScript
    echo "Compiling binary"
    ./gradlew buildDistribution
    echo "DONE Compiling binary"
    echo "Sending binary"
    scp build/raspberry-1.0-SNAPSHOT.tgz raspberry1:./raspberryJ-bin
    echo "DONE Sending binary"
    echo "Running Deploy"
    ssh raspberry1 './home/pi/deploy.sh binary'
    echo "DONE Running Deploy"
}

publishCode () {
    sendScript
    echo "Running Deploy"
    ssh raspberry1 './home/pi/deploy.sh code'
    echo "DONE Running Deploy"
}

if [ "$1" == "code" ]; then
    publishCode
elif [ "$1" == "binary" ]; then
    publishBinary
else
    echo "INVALID OPTION"
fi