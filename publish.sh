#!/bin/bash

HOST="$1"

sendScript() {
    echo "Sending deploy script"
    scp deploy.sh $HOST:./
    ssh $HOST 'chmod +x deploy.sh'
    echo "DONE Sending deploy script"
}

publishBinary() {
    sendScript
    echo "Compiling binary"
    ./gradlew clean build
    echo "assamble binary"
    ./gradlew buildDistribution
    echo "DONE Compiling binary"
    echo "Sending binary"
    scp build/raspberry-1.0-SNAPSHOT.tgz $HOST:./
    echo "DONE Sending binary"
    echo "Running Deploy"
    ssh $HOST './deploy.sh binary'
    echo "DONE Running Deploy"
}

publishCode () {
    sendScript
    echo "Running Deploy"
    ssh $HOST './deploy.sh code'
    echo "DONE Running Deploy"
}

publishBinary