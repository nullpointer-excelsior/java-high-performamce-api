#!/bin/bash

unzip database.zip
docker run --name streamdata -e POSTGRES_USER=streamdata -e POSTGRES_PASSWORD=streamdata -e POSTGRES_DATABASE=streamdata -p 5432:5432 -v "$PWD/product.sql:/docker-entrypoint-initdb.d/product.sql" -d postgres