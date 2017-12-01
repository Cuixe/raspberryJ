#!/usr/bin/env bash

scp deploy.sh raspberry1:./GPIOController

./gradlew buildDistribution

scp build/raspberry-1.0-SNAPSHOT.tgz raspberry1:./GPIOController

ssh raspberry1 'sh /home/pi/GPIOController/deploy.sh'


