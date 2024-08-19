#!/bin/bash

# Array of service directories
services=("product-service" "order-service" "inventory-service")

# Function to start a service
start_service() {
    echo "Starting $1..."
    cd $1
    docker compose up -d
    cd ..
    echo "$1 started."
    echo
}

# Main script
echo "Starting all services..."
echo

for service in "${services[@]}"
do
    start_service $service
done

echo "All services have been started."
echo "To view logs, use 'docker compose logs' in each service directory."