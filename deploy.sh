#!/usr/bin/env bash

BIN_PATH="/home/pi/raspberryJ-bin"
CODE_PATH="/home/pi/raspberryJ"

createDirectories() {
    echo "Creating Directories"

    if [ ! -d "$BIN_PATH" ]; then
        echo "Creating binary path"
        mkdir "$BIN_PATH"
    fi

    if [ ! -d "$BIN_PATH/log" ]; then
        echo "Creating log path"
        mkdir "$BIN_PATH/log"
    fi

    if [ "$1" == "code" ]; then
        echo "Creating source path"
        if [ ! -d "$CODE_PATH" ]; then
            mkdir "$CODE_PATH"
            cd "$CODE_PATH"
            echo "Cloning Source Code"
            git clone https://github.com/Cuixe/raspberryJ.git
        fi
    fi
}

binaryDeploy() {
    createDirectories
    echo "Deploying Binary"
    cd "$BIN_PATH"
    tar -zxvf raspberry-1.0-SNAPSHOT.tgz
    chmod +x *.sh
    rm -rf *.tgz
    echo "DONE Deploying Binary"
}

codeDeploy() {
    createDirectories
    cd "$CODE_PATH"
    echo "Getting last version"
    git pull
    echo "Compiling Code"
    ./gradlew buildDistribution
    echo "Moving binary"
    cp  build/raspberry-1.0-SNAPSHOT.tgz "$BIN_PATH"
    binaryDeploy
}

if [ "$1" == "code" ]; then
    codeDeploy
fi

if [ "$1" == "binary" ]; then
    binaryDeploy
fi