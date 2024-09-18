#!/bin/bash

# Array of service directories
services=("product-service" "order-service" "inventory-service")

# Function to start a service
start_service() {
    echo "Starting $1..."
    docker compose up -d
    cd ..
    echo "$1 started."
    echo
}

# Build executable jar file with dependencies
build_jar() {
    echo "Building $1..."
    ./gradlew bootJar
    echo "$1 built."
    echo
}

# Create a Network for container inter-communication
create_network() {
  echo "Creating network 'shared-network'..."
    if ! docker network inspect shared-network &> /dev/null; then
        docker network create shared-network
        echo "Network 'shared-network' created."
    else
        echo "Network 'shared-network' already exists."
    fi
}

# Main script
echo "Starting network for microservices inter-communication..."
create_network
echo

echo "Starting all services..."
echo

for service in "${services[@]}"
do
    cd $service
    build_jar $service
    start_service $service
done

echo "All services have been started."
echo "To view logs, use 'docker compose logs' in each service directory."

echo "Network information:"
docker network inspect shared-network