# FROM openjdk:13-alpine
FROM maven:3-jdk-13-alpine

WORKDIR /usr/src/app

COPY . .

RUN mvn clean install