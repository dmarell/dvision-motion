#!/bin/bash
apiuser=$1
apipassword=$2

set -ex
docker build -t dvision-motion target
docker stop dvision-motion 2> /dev/null || true
docker rm -f dvision-motion || true
docker run -d --name dvision-motion \
    -e DVISION_APIUSER=$apiuser \
    -e DVISION_APIPASSWORD=$apipassword \
    -p 10111:8080 \
    dvision-motion
