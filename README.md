# BULL tutorial

Simple Spring Boot application containing a service that uses BULL for the object transformation

[![Build Status](https://travis-ci.com/fborriello/bull-tutorial.svg?branch=master)](https://travis-ci.com/fborriello/bull-tutorial)
[![Dependabot Status](https://api.dependabot.com/badges/status?host=github&repo=fborriello/bull-tutorial)](https://dependabot.com)
[![Bull enabled](https://img.shields.io/badge/bull-enabled-red)](https://github.com/ExpediaGroup/bull)

## Requirements

* JDK 15 or above
* Maven
* IDE

## Maven build

```shell script
mvn clean install
```

## Run project

From the `web` project folder, execute:

```shell script
mvn spring-boot:run
```

## Test API

* Swagger page: [Swagger-ui](http://localhost:8080/swagger-ui/)
* [API Docs](http://localhost:8080/v2/api-docs)
