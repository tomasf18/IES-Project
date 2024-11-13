#!/bin/bash

# Define environment variables
export DATABASE_LOCAL_PORT=5432
export DATABASE_DOCKER_PORT=5432

export POSTGRES_USER=production_user
export POSTGRES_PASSWORD=production_password
export POSTGRES_DB=production_db

export BACKEND_LOCAL_PORT=8080
export BACKEND_DOCKER_PORT=8080

export FRONTEND_LOCAL_PORT=5173
export FRONTEND_DOCKER_PORT=3000

# Create Docker secrets for sensitive information
echo "$POSTGRES_USER" > postgres_user.txt
echo "$POSTGRES_PASSWORD" > postgres_password.txt
echo "$POSTGRES_DB" > postgres_db.txt

# Initialize Docker Swarm if not already initialized
docker swarm init 2>/dev/null || echo "Swarm already initialized"

# Create Docker secrets
docker secret create postgres_user postgres_user.txt
docker secret create postgres_password postgres_password.txt
docker secret create postgres_db postgres_db.txt

# Deploy the Docker stack
docker stack deploy -c docker-compose.yml my_stack

# Clean up local files used for secrets
rm postgres_user.txt postgres_password.txt postgres_db.txt

echo "Docker stack deployed successfully with Docker secrets."


# Run the setup.sh script:
# > chmod +x 
# > setup.sh