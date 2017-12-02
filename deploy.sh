#!/usr/bin/env bash
BASE_PATH="/home/pi/raspberryJ"
BIN_PATH="$BASE_PATH/raspberryJ-bin"
CODE_PATH="$BASE_PATH/raspberryJ-code"

createDirectories() {
    echo "Creating Directories"

    if [ ! -d "$BASE_PATH" ]; then
        echo "Creating base path"
        mkdir "$BASE_PATH"
    fi

    cd "$BASE_PATH"

    if [ ! -d "$BIN_PATH" ]; then
        echo "Creating binary path"
        mkdir "$BIN_PATH"
    else
        rm -rf "$BIN_PATH"
        echo "Creating binary path"
        mkdir "$BIN_PATH"
    fi

    if [ ! -d "$BIN_PATH/log" ]; then
        echo "Creating log path"
        mkdir "$BIN_PATH/log"
    fi
}

createSourceCode() {
    if [ ! -d "$CODE_PATH" ]; then
        echo "Cloning Source Code"
        git clone https://github.com/Cuixe/raspberryJ.git
        mv raspberryJ raspberryJ-code
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
    createSourceCode
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
    echo "DEPLOYING SOURCE CODE"
    codeDeploy
elif [ "$1" == "binary" ]; then
    echo "DEPLOYING BINARY"
    binaryDeploy
else
    echo "INVALID OPTION"
fi

