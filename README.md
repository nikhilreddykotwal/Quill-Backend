# QuillQueue — Priority Job Queue & Scheduler

Author: **Nikhil Reddy Kotwal** <nikhilreddyk095@gmail.com>

## Run locally
```bash
docker compose up -d
./mvnw spring-boot:run
```
OpenAPI: `http://localhost:8080/swagger-ui.html`

## Sample curl
```bash
curl -X POST http://localhost:8080/api/jobs -H 'Content-Type: application/json' -d '{"queueName":"emails","payload":"{\"email\":\"test@example.com\"}","priority":10}'
```
