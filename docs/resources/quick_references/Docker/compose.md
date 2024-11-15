# Docker Compose file for the project application explained

- The compose file is used to define the services that make up the application.
- The services are defined in the `services` section.

```yaml
services:
```

- The database service is defined as follows:

```yaml
  database:                                                   # The name of the service
    image: timescale/timescaledb:latest-pg16                  # The image to be used for the service
    environment:                                              # The environment variables to be passed to the container
      POSTGRES_USER: ${POSTGRES_USER}                         # The username for the database
      POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}                 # The password for the database
      POSTGRES_DB: ${POSTGRES_DB}                             # The name of the database
    ports:                                                    # The ports to be exposed by the container: host_port:container_port, which tells that the service running on the container port will be accessible from the host machine on the host port
      - "${DATABASE_LOCAL_PORT}:${DATABASE_CONTAINER_PORT}"
    volumes:                                                  # The volumes to be mounted to the container: host_path:container_path
      - db_data:${DATABASE_VOLUME}
    restart: unless-stopped                                   # The restart policy for the container, which tells that the container should be restarted unless it is stopped
```

- The backend service is defined as follows:

```yaml
  backend:                                                    # The name of the service
    depends_on:                                               # The services that this service depends on, which tells that the backend service should start only after the database service has started
      - database
    build:                                                    # The build configuration for the service
      context: ./backend                                      # The path to the directory containing the Dockerfile
      dockerfile: Dockerfile                                  # The name of the Dockerfile to be used for building the image
    environment:                                              # The environment variables to be passed to the container
      SPRING_APPLICATION_JSON:                                # The JSON string to be passed as an environment variable
        '{
          "spring.datasource.url" : "jdbc:postgresql://database:${DATABASE_CONTAINER_PORT}/${POSTGRES_DB}",
          "spring.datasource.username" : "${POSTGRES_USER}",
          "spring.datasource.password" : "${POSTGRES_PASSWORD}",
          "spring.jpa.show-sql" : "true",
          "spring.jpa.properties.hibernate.dialect" : "org.hibernate.dialect.PostgreSQLDialect",
          "spring.jpa.hibernate.ddl-auto" : "update"
        }'
    ports:                                                    # The ports to be exposed by the container: host_port:container_port
      - "${BACKEND_LOCAL_PORT}:${BACKEND_CONTAINER_PORT}"
    volumes:                                                  # The volumes to be mounted to the container: host_path:container_path
      - .m2:/root/.m2                                         # volume for maven dependencies caching, so that we don't have to download them every time we build the image
    restart: unless-stopped                                   # The restart policy for the container, which tells that the container should be restarted unless it is stopped
```

- The frontend service is defined as follows:

```yaml
  frontend:                                                   # The name of the service
    depends_on:                                               # The services that this service depends on, which tells that the frontend service should start only after the backend service has started
      - backend 
    build:                                                    # The build configuration for the service
      context: ./frontend                                     # The path to the directory containing the Dockerfile
      dockerfile: Dockerfile                                  # The name of the Dockerfile to be used for building the image
    ports:                                                    # The ports to be exposed by the container: host_port:container_port
      - "${FRONTEND_LOCAL_PORT}:${FRONTEND_CONTAINER_PORT}"   # The port "{FRONTEND_CONTAINER_PORT}" inside the container will be accessible from the port "{FRONTEND_LOCAL_PORT}" on the host machine
    restart: unless-stopped                                   # The restart policy for the container, which tells that the container should be restarted unless it is stopped
```

- The volumes section is used to define the volumes that are used by the services, at the top level of the file, indicating that the volumes are shared between the services.

```yaml
volumes:
  db_data:
```
