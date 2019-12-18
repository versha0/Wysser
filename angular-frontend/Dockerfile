FROM nginx:alpine
COPY default.conf /etc/nginx/conf.d/default.conf
COPY /dist/Wysser/ /usr/share/nginx/html

# # FROM node:11

# # WORKDIR /app

# # COPY . .

# # RUN npm install

# # RUN npm run build --prod

# # RUN npm install http-server -g

# # WORKDIR /app/dist/Wysser/

# # CMD ["http-server", "-c1", "-p", "80"]
# # base image
# FROM node:13.1.0
# # install chrome for protractor tests
# RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add -
# RUN sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list'
# RUN apt-get update && apt-get install -yq google-chrome-stable
# # set working directory
# WORKDIR /app
# # add `/app/node_modules/.bin` to $PATH
# ENV PATH /app/node_modules/.bin:$PATH
# # install and cache app dependencies
# COPY package.json /app/package.json
# RUN npm install
# RUN npm install -g @angular/cli@latest
# # add app
# COPY . /app
# EXPOSE 4200
# # start app
# CMD ng serve --host 0.0.0.0 --publicHost wysser.stackroute.io\

# FROM nginx:alpine

# COPY dist/Wysser/ /usr/share/nginx/html

