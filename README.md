# Java high Performance API

Este proyecto está destinado a ejemplos de aplicaciones de alto rendimiento con Java

## Artículos sobre desarrollo de APIs de alto rendimiento con Java

* [Trabajando con 1 millón de registros de forma eficiente](https://nullpointer-excelsior.github.io/posts/tranajando-con-1millon-de-registros-con-java-stream/)

* [Aplicaciones Reactivas de Alto Rendimiento con Spring WebFlux](https://nullpointer-excelsior.github.io/posts/high-performance-reactive-applications-with-spring-webflux)


## Levantar base de datos

```bash
#!/bin/bash

# exec the script
./up-database.sh

```

## Requerimientos

* Java 17 en adelante
* Docker

## Ejecutar aplicaciones

Podemos hace uso del archivo Makefile 

```bash
#!/bin/bash

# run webflux server example on port 8080 
make up-webflux-api # ./gradlew apps:api-webflux:bootRun

# run web server example on port 8090 
make up-blocking-api # ./gradlew apps:api-blocking:bootRun

# make a request to webflux example server
make curl-webflux # curl http://localhost:8080/product | jq

# make a request to web example server
make curl-blocking # curl http://localhost:8090/product\?limit\=100000\&offset\=10 | jq

```


### About the Developer

`Author` Benjamín Software Engineer
