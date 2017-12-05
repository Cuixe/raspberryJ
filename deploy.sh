#!/usr/bin/env bash
BASE_PATH="/home/pi/raspberryJ"
BIN_PATH="$BASE_PATH/raspberryJ-bin"
CODE_PATH="$BASE_PATH/raspberryJ-code"
PACKAGE_NAME="raspberry-1.0-SNAPSHOT.tgz"

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
    fi

    if [ ! -d "$BIN_PATH/log" ]; then
        echo "Creating log path"
        mkdir "$BIN_PATH/log"
    fi
}

cleanBinaryPath() {
    echo "Cleaning binary path"
    rm -rf "$BIN_PATH"
    sleep 1
    echo "Creating binary path"
    mkdir "$BIN_PATH"

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
    mv /home/pi/"$PACKAGE_NAME" "$BIN_PATH"
    echo "Deploying Binary"
    cd "$BIN_PATH"
    tar -zxvf "$PACKAGE_NAME"
    chmod +x *.sh
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

