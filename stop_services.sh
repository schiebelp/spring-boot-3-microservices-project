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

echo "All services have been stopped."