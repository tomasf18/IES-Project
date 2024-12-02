FROM node:20.18.0
WORKDIR /app
COPY ./react-app/package*.json ./
RUN npm install
COPY ./react-app .

# Environment variables
ARG VITE_BACKEND_IP
ARG VITE_BACKEND_PORT
ARG VITE_BACKEND_API_PREFIX
ARG VITE_WEBSOCKET_ENDPOINT
ENV VITE_BACKEND_IP=$VITE_BACKEND_IP
ENV VITE_BACKEND_PORT=$VITE_BACKEND_PORT
ENV VITE_BACKEND_API_PREFIX=$VITE_BACKEND_API_PREFIX
ENV VITE_WEBSOCKET_ENDPOINT=$VITE_WEBSOCKET_ENDPOINT

RUN npm run build
# EXPOSE 3000 -> Not needed since we are already specifying the port in compose file
CMD ["npm", "run", "dev", "--", "--host"]