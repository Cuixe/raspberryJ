#!/bin/bash

MODULE_PATH="${1}/api"
PROTO_PATH="${1}/api/src/main/resources"
JAVA_OUT="${1}/api/src/main/java"
FILE='message.proto'

echo "BUILDING ${FILE} FILE"
cd "${PROTO_PATH}"

if [ ! -f "${FILE}" ]; then
    echo "FILE ${FILE} NOT FOUND"
    exit 1
fi

protoc --java_out=${JAVA_OUT} ${FILE}
echo "BUILD ${FILE} DONE"
exit 0