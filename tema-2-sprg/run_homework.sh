#!/bin/bash

docker-compose -f docker-compose.yml up -d && 
docker exec -it tema-2-sprg_client_1 /bin/bash -c "java -jar /var/lib/client/client.jar"
