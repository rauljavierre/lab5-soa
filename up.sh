#!/bin/bash

printf "[up.sh] Tearing down old artifacts\n"
sudo bash down.sh

printf "\n[up.sh] Building gradle\n"
gradle build --stacktrace # sudo snap install gradle --classic

if [ $? -ne 0 ]; then
  printf "\n [up.sh] Build was not successful, exiting with the build code..."
  exit
fi

printf "\n[up.sh] Copying the jar generated with gradle build\n"
cp ./build/libs/lab5-soa.jar spring-docker

printf "\n[up.sh] Tearing up containers\n"
sudo docker-compose -f docker-compose.yml up --build
