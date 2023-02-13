
database_name := reactive-db

create-lib:
	mkdir -p libs/$(n)/src/{main/{com/benjamin/$(n),resources},test}
	echo "dependencies {}" > libs/$(n)/build.gradle
	echo "include 'libs:$(n)'" >> settings.gradle


create-app:
	mkdir -p apps/$(n)/src/{main/{com/benjamin/$(n),resources},test}
	echo "dependencies {}" > apps/$(n)/build.gradle
	echo "include 'apps:$(n)'" >> settings.gradle


up-database:
	-docker rm -f $(database_name) 2> /dev/null
	unzip -o database.zip
	docker run --name $(database_name) -e POSTGRES_USER=$(database_name) -e POSTGRES_PASSWORD=$(database_name) -e POSTGRES_DATABASE=$(database_name) -p 5432:5432 -v "${PWD}/product.sql:/docker-entrypoint-initdb.d/product.sql" -d postgres
	docker ps


enter-db:
	docker exec -it $(database_name)  psql -U $(database_name) $(database_name)
	

up-webflux-api:
	./gradlew apps:api-webflux:bootRun


up-blocking-api:
	./gradlew apps:api-blocking:bootRun


up-client-api:
	./gradlew apps:client-reactive:bootRun


curl-webflux:
	curl -v http://localhost:8080/product | jq


curl-blocking:
	curl -v http://localhost:8090/product\?limit\=$(l)\&offset\=0 | jq


curl-client-reactive:
	curl -v http://localhost:9000/product
