#!/usr/bin/env bash

cd /home/pi/GPIOController

tar -zxvf raspberry-1.0-SNAPSHOT.tgz

chmod +x *.sh

rm -rf *.tgz

rm -rf deploy.sh

if [ ! -d "log" ]; then
  mkdir log
fi