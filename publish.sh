#!/bin/bash

USER="$1"
HOST="$2"
SSH="$1@$2"

echo $SSH

sendScript() {
    echo "Sending deploy script"
    scp deploy.sh $SSH:./
    ssh $SSH 'chmod +x deploy.sh'
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
    scp build/raspberry-1.0-SNAPSHOT.tgz $SSH:./
    echo "DONE Sending binary"
    echo "Running Deploy"
    ssh $SSH "./deploy.sh binary $HOST"
    echo "DONE Running Deploy"
}

publishCode () {
    sendScript
    echo "Running Deploy"
    ssh $SSH './deploy.sh code'
    echo "DONE Running Deploy"
}

publishBinary
