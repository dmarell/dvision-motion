#!/usr/bin/env bash
set -e
version=1.0.4
mvn versions:set -DnewVersion=${version}
mvn clean package
bash deploy-k8s.sh ${version}

