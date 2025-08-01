# api-meli

# Item Detail API (Spring Boot)

## Descripción
Esta API simula el backend de detalle de productos al estilo MercadoLibre.

## Stack
- Java 21
- Spring Boot
- Maven
- Jackson para leer JSON

## Endpoints
- `GET /api/products` → Lista todos los productos
- `GET /api/products/{id}` → Trae detalle de un producto
- `GET /api/products/search?query=` → Trae productos según filtro

## Instrucciones
1. Clonar el repositorio:

```bash
git clone git@github.com:gomezleo35/news-service.git
cd api-meli
```

2. Ejecutar 
 ```bash
.mvn clean install
.mvn spring-boot:run
```
La aplicacón correrá en `http://localhost:8080`

3. Probar con Postman, curl o SWAGGER
Swagger: `http://localhost:8080/swagger-ui.html`

## Test
 ```bash
./mvnw spring-boot:run
```



