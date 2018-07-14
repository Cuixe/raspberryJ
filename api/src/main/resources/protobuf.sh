#!/bin/bash

PROTO_PATH="${1}/src/main/resources"
JAVA_OUT="${1}/src/main/java"

echo "EXECUTING PROTOCOL BUFFERS"
echo "PROTO_PAHT: ${PROTO_PATH}"
echo "JAVA_OUT: ${JAVA_OUT}"

if [ ! -f "${PROTO_PATH}/raspberryJ.proto" ]; then
    echo "FILE NOT FOUND"
    exit 1
fi

cd "${PROTO_PATH}"

protoc --java_out=${1}/src/main/java raspberryJ.proto