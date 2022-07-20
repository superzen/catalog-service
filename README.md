## 5.1.2 Running PostgreSQL as a container

```
docker run -d --name polar-postgres -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -e POSTGRES_DB=polardb_catalog -p 5432:5432 postgres:14.3
```

## Contenedores con Dockerfile o Buildpacks

### 6.2.1 Create network

```
docker network create catalog-network
```

### Running PostgreSQL container joining the network

```
docker run -d --name polar-postgres --net catalog-network -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -e POSTGRES_DB=polardb_catalog -p 5432:5432 postgres:14.3
```

Si falla es que está creado y usar `docker rm -fv polar-postgres`

### opcion 1 Create image with Dockerfile

```
gradlew clean bootJar

docker build -t catalog-service .

docker run -d --name catalog-service --net catalog-network -p 9001:9001 -e SPRING_DATASOURCE_URL=jdbc:postgresql://polar-postgres:5432/polardb_catalog -e SPRING_PROFILES_ACTIVE=testdata catalog-service
```

### opcion 2 Create image with Cloud Native Buildpacks

Se modifica el gradle para poner ahi la info de como se tiene que llamar la imagen y version de java (hecho)

```
gradlew bootBuildImage

docker run -d --name catalog-service --net catalog-network -p 9001:9001 -e SPRING_DATASOURCE_URL=jdbc:postgresql://polar-postgres:5432/polardb_catalog -e SPRING_PROFILES_ACTIVE=testdata catalog-service
```

Al finalizar

```
docker rm -f catalog-service polar-postgres

```

Para eliminar la red

```
docker network rm catalog-network
```

## Crear imagen y publicarla en repositorio

```
gradlew bootBuildImage --imageName ghcr.io/superzen/catalog-service --publishImage -PregistryUrl=ghcr.io -PregistryUsername=superzen -PregistryToken=<your_github_token>
```
