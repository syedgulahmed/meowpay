# MeowPay

A small service for sending treats between cat accounts.

## Prerequisites

- Docker
- Java 17+
- Maven

## Running the project

1. Start Postgres (available on `localhost:5432`):

   ```bash
   docker compose up -d
   ```

2. Create the tables and seed cat data:

   ```bash
   docker exec -i meowpay-db psql -U meowpay -d meowpay < src\main\resources\db\schema.sql
   ```

3. Start the Spring Boot application (serves the frontend on `localhost:8080`):

   ```bash
   mvn spring-boot:run
   ```

## Decisions and trade-offs

- Kept the API thin — only two endpoints: `GET /cats` and `POST /transfers`.
- Used pessimistic row locking on cat rows to avoid concurrent writes.
- Used idempotency on `POST /transfers` to avoid duplicate/repeated requests.
- Used `ProblemDetail` with a global exception handler to return real status codes.
- Kept the frontend simple (vanilla JS), focusing effort on a correct backend.
- No authentication — cat balances are visible to everyone.
- No cat creation endpoint — the scope here is the transfer flow, not account management.
- No automated tests — idempotency and locking were tested manually with repeated/simultaneous requests via curl and the UI.