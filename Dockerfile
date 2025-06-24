# Stage 1: Build Angular
FROM node:20 as build
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build --prod

# Stage 2: Serve with NGINX
FROM nginx:alpine
COPY --from=build /app/dist/career-crafter-frontend /usr/share/nginx/html
EXPOSE 80