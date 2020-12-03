#!/bin/bash

printf "\n\n[up.sh] Tearing down old artifacts\n"
sudo bash down.sh

printf "\n[up.sh] Building gradle\n"
gradle build --stacktrace # sudo snap install gradle --classic

if [ $? -ne 0 ]; then
  exit  # If build was not successful... Exit with the build code
fi

printf "\n[up.sh] Copying the jar generated with gradle build\n"
cp ./build/libs/lab5-soa.jar spring-docker

printf "\n[up.sh] Tearing up containers\n"
sudo docker-compose up --build
