FROM node:20.18.0
WORKDIR /app
COPY ./react-app/package*.json ./
RUN npm install
COPY ./react-app .
RUN npm run build
# EXPOSE 3000 -> Not needed since we are already specifying the port in compose file
CMD ["npm", "run", "dev", "--", "--host"]