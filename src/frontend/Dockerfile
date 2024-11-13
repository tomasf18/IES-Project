FROM node:20.18.0
WORKDIR /app
COPY ./react-app/package*.json ./
RUN npm install
COPY ./react-app .
RUN npm run build
EXPOSE 5173
CMD ["npm", "run", "dev", "--", "--host"]