## 5.1.2 Running PostgreSQL as a container

```
docker run -d --name polar-postgres -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -e POSTGRES_DB=polardb_catalog -p 5432:5432 postgres:14.3
```

## 6.2.1 Create network

```
docker network create catalog-network
```

### Running PostgreSQL container joining the network

```
docker run -d --name polar-postgres --net catalog-network -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -e POSTGRES_DB=polardb_catalog -p 5432:5432 postgres:14.3
```

Si falla es que está creado y usar `docker rm -fv polar-postgres`

### Create image with Dockerfile

```
gradlew clean bootJar

docker build -t catalog-service .

docker run -d --name catalog-service --net catalog-network -p 9001:9001 -e SPRING_DATASOURCE_URL=jdbc:postgresql://polar-postgres:5432/polardb_catalog -e SPRING_PROFILES_ACTIVE=testdata catalog-service
```

Al finalizar

```
docker rm -f catalog-service polar-postgres

```
