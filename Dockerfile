FROM node:13-alpine

ENV MONGO_DB_USERNAME=admin \
    MONGO_DB_PWD=password

COPY ./app /var/jenkins/home/workspace/DEPI\Final\Project/app

WORKDIR /var/jenkins_home/workspace/DEPI\Final\Project/

# will execute npm install in /home/app because of WORKDIR
RUN npm install

# no need for /home/app/server.js because of WORKDIR
CMD ["node", "server.js"]
