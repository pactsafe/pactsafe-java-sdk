# docker build -t pactsafe .
# docker run --rm -t pactsafe
FROM maven:3-jdk-13-alpine

WORKDIR /usr/src/app

COPY . .

RUN mvn clean install

CMD mvn test