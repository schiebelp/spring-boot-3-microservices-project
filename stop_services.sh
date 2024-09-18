#!/bin/bash

services=("product-service" "order-service" "inventory-service")

for service in "${services[@]}"
do
    echo "Stopping $service..."
    cd $service
    docker compose down
    cd ..
    echo "$service stopped."
    echo
done

echo "Removing volumes for all services..."
docker volume rm $(docker volume ls -q)
echo "removed."
echo

echo "Removing network 'shared-network'..."
docker network rm shared-network
echo "removed."

echo "All services have been stopped."