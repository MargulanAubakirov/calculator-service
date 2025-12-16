# CalculatorLimiter Service

A minimal **Spring Boot microservice** implementing a calculator with REST and GraphQL endpoints, a custom rate limiter, and Swagger UI documentation.

---

## Features

- **REST API** `/api/calculator`
  - Query parameters: `a`, `b`, `operator`
  - Supported operators:
    - `p` → addition (`a + b`)
    - `m` → subtraction (`a - b`)
    - `x` → multiplication (`a * b`)
    - `d` → division (`a / b`)
  - Returns JSON with fields: `a`, `b`, `operator`, `result`, `error`
  - Handles unsupported operators and division by zero
  - Rate limiting per IP address

- **GraphQL endpoint** `/graphql`
  - Query: `calculator(a: Int, b: Int, operator: String)`
  - Returns same structure as REST

- **Swagger UI**
  - Accessible at `/swagger-ui/index.html`
  - Allows interactive REST API testing

---

## REST API cURL Examples

- Addition:
```bash
curl -X GET "http://localhost:8080/api/calculator?a=1&b=2&operator=p"
```

- Subtraction:
```bash
curl -X GET "http://localhost:8080/api/calculator?a=5&b=3&operator=m"
```

- Multiplication:
```bash
curl -X GET "http://localhost:8080/api/calculator?a=4&b=3&operator=x"
```

- Division:
```bash
curl -X GET "http://localhost:8080/api/calculator?a=6&b=3&operator=d"
```

- Division by zero:
```bash
curl -X GET "http://localhost:8080/api/calculator?a=6&b=0&operator=d"
```

- Unsupported operator:
```bash
curl -X GET "http://localhost:8080/api/calculator?a=1&b=2&operator=v"
```

- Rate limit exceeded:
```bash
curl -X GET "http://localhost:8080/api/calculator?a=1&b=2&operator=p"
# repeat > 10 times within 1 minute
```

---

## GraphQL Endpoint

- Endpoint: `http://localhost:8080/graphiql`
- Example Query:
```graphql
{
  calculator(a:3, b:5, operator:"x") {
    result
    error
  }
}
```
- Example Response:
```json
{
  "data": {
    "calculator": {
      "result": 15,
      "error": ""
    }
  }
}
```

---

## Swagger UI

- Access Swagger UI at:
```
http://localhost:8080/swagger-ui/index.html
```
- Provides interactive REST API documentation and testing
- Allows entering query parameters and calling endpoints directly from the browser

---

## Project Structure

```
CalculatorLimiter
├── src
│   └── main
│       ├── java/com/example/calculatorlimiter
│       │   ├── controller
│       │   ├── service
│       │   ├── dto
│       │   ├── ratelimit
│       │   └── graphql
│       └── resources
│           └── application.yml
└── pom.xml
```

---

## How to Run

1. **Build project**
```bash
mvn clean install
```

2. **Run project**
```bash
mvn spring-boot:run
```

3. **Test REST API**
```
http://localhost:8080/api/calculator?a=1&b=2&operator=p
```

4. **Test GraphQL**
```
http://localhost:8080/graphiql
```

5. **Test Swagger UI**
```
http://localhost:8080/swagger-ui/index.html
```

---

## Example Responses

### Unsupported operator
```json
{
  "a":1,
  "b":2,
  "operator":"v",
  "result":0,
  "error":"Supported operators: +=p, -=m, /=d, x=x"
}
```

### Division by zero
```json
{
  "a":1,
  "b":0,
  "operator":"d",
  "result":0,
  "error":"/ by zero"
}
```

### Rate limit exceeded
```json
{
  "a":0,
  "b":0,
  "operator":null,
  "result":0,
  "error":"Please try after some time."
}
```

---

## Author
Margulan Aubakirov