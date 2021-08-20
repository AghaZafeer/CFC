# Step 1
FROM node:10-alpine as build-step
ENV NODE_ENV production
RUN mkdir /app
WORKDIR /app
COPY package.json .
RUN yarn install --production
COPY . .
RUN yarn build
# Stage 2
FROM nginx:1.21.0-alpine as production
ENV NODE_ENV production
COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=build-step /app/build /usr/share/nginx/html
# Expose port
EXPOSE 80
# Start nginx
CMD ["nginx", "-g", "daemon off;"]

# docker-compose -f docker-compose.prod.yml build
# docker container run --network cfc-mysql --name cfc-frontend -p 80:80 -d cfc-frontend
