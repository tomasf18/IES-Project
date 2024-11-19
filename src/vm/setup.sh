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

# Create Docker secrets if they don't already exist
create_secret() {
  local secret_name=$1
  local secret_value=$2
  echo "$secret_value" | docker secret create "$secret_name" - 2>/dev/null || echo "Secret $secret_name already exists."
}

create_secret postgres_user "$POSTGRES_USER"
create_secret postgres_password "$POSTGRES_PASSWORD"
create_secret postgres_db "$POSTGRES_DB"

# Deploy the Docker stack
docker stack deploy -c compose.yml my_stack

# Confirm deployment
echo "Docker stack deployed successfully with Docker secrets."

# Run the setup.sh script:
# > chmod +x 
# > ./setup.sh