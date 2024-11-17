# Dockerfile for the React+Vite application explained

- Imagining a Dockerfile as a recipe, we can think of it as a set of instructions that tell Docker how to build an image. 
- In this case, we are building an image for a React+Vite application.
- So, thinking of a container as a mini virtual machine, a new machine where nothing is installed, we need to install everything that the application needs to run.
- So, for that, we'll:

1. use the node image as the base image, so then we can have the node installed in the container, being able to run its services (as `npm` command);
```dockerfile
FROM node:20.18.0
```

2. set the working directory inside the container to /app;
```dockerfile
WORKDIR /app
```

3. copy the package.json and package-lock.json files to the working directory, so then we can install the dependencies inside our "mini virtual machine";
```dockerfile
COPY ./react-app/package*.json ./
```

4. install the dependencies specified in the package.json file;
```dockerfile
RUN npm install
```

5. copy the source code to the working directory, just like when we first run the `npm install` to start building our app, but here it is already built and we only need to copy the files;
```dockerfile
COPY ./react-app .
```

6. build the application;
```dockerfile
RUN npm run build
```

7. expose the port 3000: this means that the container will listen on port 3000 (the port where the application will be available/accessible on the host machine);
- In this case, it's normal that the https://localhost:5173 is not working, because the application is running inside the container, and the container is not exposing the port 3000 to the host machine (there is no tunnel between the container and the host machine).
- Here, it is not necessary, since we are already creating the port translation in the `compose.yml` file.

```dockerfile
EXPOSE 3000 
```

8. inside the container, start the application:
```dockerfile
CMD ["npm", "run", "dev", "--", "--host"]
```