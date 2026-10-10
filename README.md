# Smart Wildlife Conservation & Protected Area Management

Folder skeleton for a Java + Spring Boot microservices hackathon project.

## Services
- `wildlife-service` — animal and species records (port 8081)
- `operations-service` — patrols, incidents, rescues, staff, resources (port 8082)
- `conservation-service` — protected areas, habitats, programmes, priority scoring (port 8083)

Each service has its own Maven `pom.xml`, Spring Boot entry point, `application.properties`,
and package folders. Business logic and REST endpoints are intentionally not implemented.

## Prerequisites
- JDK 17 or later (JDK 21 is fine)
- Maven installed
- PostgreSQL installed and running
- VS Code with Extension Pack for Java (recommended)

## Databases
Create these PostgreSQL databases: `wildlife_db`, `operations_db`, `conservation_db`.
Update each service's `src/main/resources/application.properties` with your PostgreSQL username
and password. The password is set to `CHANGE_ME`.

## Run
Open this root folder in VS Code. Run each service in a separate terminal:

```powershell
cd wildlife-service
mvn spring-boot:run
```

In two additional terminals:

```powershell
cd operations-service
mvn spring-boot:run
```

```powershell
cd conservation-service
mvn spring-boot:run
```

These are skeleton applications. No REST endpoints or business logic exist yet. They are configured
to use PostgreSQL, so create the databases and set credentials before starting them.

No API Gateway is included. The frontend will call each service directly.
