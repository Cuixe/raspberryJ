#!/usr/bin/env bash
USER_HOME="/home/pi"
APP_HOME="$USER_HOME/raspberryJ"
BIN_PATH="$APP_HOME/bin"
CODE_PATH="$USER_HOME/raspberryJ-code"
PACKAGE_NAME="raspberry-1.0-SNAPSHOT.tgz"

createDirectories() {
    echo "Creating Directories"

    if [ ! -d "$USER_HOME" ]; then
        echo "Creating base path"
        mkdir "$USER_HOME"
    fi

    cd "$USER_HOME"

    if [ ! -d "$APP_HOME" ]; then
        echo "Creating binary path"
        mkdir "$APP_HOME"
    fi

    if [ ! -d "$APP_HOME/log" ]; then
        echo "Creating log path"
        mkdir "$APP_HOME/log"
    fi
}

cleanBinaryPath() {
    echo "Cleaning binary path"
    rm -rf "$APP_HOME/bin"
    rm -rf "$APP_HOME/*.sh"
    rm -rf "$APP_HOME/lib"
    rm -rf "$APP_HOME/log"
    sleep 1
}

createSourceCode() {
    if [ ! -d "$CODE_PATH" ]; then
        echo "Cloning Source Code"
        git clone https://github.com/Cuixe/raspberryJ.git
        mv raspberryJ raspberryJ-code
    fi
}
binaryDeploy() {
    cleanBinaryPath
    createDirectories
    mv /home/pi/"$PACKAGE_NAME" "$APP_HOME"
    echo "Deploying Binary"
    cd "$APP_HOME"
    tar -zxvf "$PACKAGE_NAME"
    chmod +x *.sh
    chmod +x bin/*.sh
    rm -rf *.tgz
    echo "DONE Deploying Binary"
}

codeDeploy() {
    cleanBinaryPath
    createDirectories
    createSourceCode
    cd "$CODE_PATH"
    echo "Getting last version"
    git pull
    echo "Compiling Code"
    ./gradlew buildDistribution
    echo "Moving binary"
    cp  build/"$PACKAGE_NAME" /home/pi
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

