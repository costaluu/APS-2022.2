FROM node:18-alpine3.17 AS BUILD
WORKDIR /app
COPY . /app
RUN npm install
RUN npm run build

FROM nginx:stable-alpine
COPY --from=BUILD /app/dist /usr/share/nginx/html
COPY ./nginx.conf /etc/nginx/nginx.conf
EXPOSE 8080
RUN chmod -R u=rwx,go=rwx /usr/share/nginx/html \ 
  && chgrp -R root /var/cache/nginx /var/run /var/log/nginx \
  && chmod -R 770 /var/cache/nginx /var/run /var/log/nginx
CMD ["nginx", "-g", "daemon off;"]
