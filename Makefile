
create-lib:
	mkdir -p libs/$(n)/src/{main/{com/benjamin/$(n),resources},test}
	echo "dependencies {}" > libs/$(n)/build.gradle
	echo "include 'libs:$(n)'" >> settings.gradle


create-app:
	mkdir -p apps/$(n)/src/{main/{com/benjamin/$(n),resources},test}
	echo "dependencies {}" > apps/$(n)/build.gradle
	echo "include 'apps:$(n)'" >> settings.gradle


up-webflux-api:
	./gradlew apps:api-webflux:bootRun


up-blocking-api:
	./gradlew apps:api-blocking:bootRun


enter-db:
	docker exec -it streamdata  psql -U streamdata streamdata
	

curl-webflux:
	curl http://localhost:8080/product | jq


curl-blocking:
	curl http://localhost:8090/product\?limit\=100000\&offset\=10 | jq
