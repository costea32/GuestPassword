#!/bin/sh
set -x

setup(){
	echo "Starting setup"
	mkdir -p images
	echo "server.port=${PORT}" > application.properties
	echo "ssid=${SSID}" >> application.properties
	echo "authKey=${SERVICE_KEY}" >> application.properties
}

startHttp(){
	java -jar guestPassword.jar
}

setup
startHttp
