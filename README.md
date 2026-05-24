# Servicio Técnico - Backend API 🛠️

Este repositorio contiene la API REST para el sistema de gestión de un taller de **Servicio Técnico**, diseñado para automatizar y controlar el flujo completo desde el ingreso de equipos tecnológicos, el diagnóstico de fallas, la asignación de técnicos, hasta la facturación y entrega al cliente.

Desarrollado bajo una arquitectura limpia y modular utilizando **Java** con **Spring Boot**, priorizando la escalabilidad, la mantenibilidad del código y el mapeo óptimo de datos.

## 🚀 Características Principales

- **Gestión de Órdenes de Servicio:** Registro de ingresos, estados de reparación (Pendiente, En Diagnóstico, Reparado, Entregado).
- **Control de Clientes y Equipos:** Historial detallado de dispositivos por cliente.
- **Asignación de Personal:** Módulo para delegar tareas a técnicos según su especialidad.
- **Arquitectura DTO (Data Transfer Object):** Desacoplamiento completo entre las entidades de la base de datos y las respuestas de la API utilizando **MapStruct**.
- **Documentación Interactiva:** Implementación de **Swagger / OpenAPI** para pruebas rápidas de los endpoints.

## 🛠️ Tecnologías y Herramientas Utilizadas

- **Backend:** Java 17 / Spring Boot 3.x
- **Persistencia y Acceso a Datos:** Spring Data JPA / Hibernate
- **Mapeo de Capas:** MapStruct (Mapeo bilingüe: Entidades en Español ↔ DTOs en Inglés)
- **Base de Datos:** MySQL / SQL Server
- **Gestión de Dependencias:** Maven
- **Documentación de API:** Swagger UI / OpenAPI 3

---

## 📐 Arquitectura del Proyecto

El backend está estructurado siguiendo las mejores prácticas del patrón arquitectónico multicapa (Controller-Service-Repository), asegurando una separación clara de responsabilidades:

```text
src/main/java/com/tuusuario/serviciotecnico
│
├── controllers/     # Capa de exposición de Endpoints REST (Request/Response)
├── services/        # Capa de Lógica de Negocio (Interfaces e Implementaciones)
├── repositories/    # Capa de Acceso a Datos (Interfaces que extienden JpaRepository)
├── models/          # Entidades de la Base de Datos (Mapeadas en Español)
├── dtos/            # Objetos de Transferencia de Datos (Estructurados en Inglés)
├── mappers/         # Interfaces de MapStruct para conversión Entidad ↔ DTO
└── exceptions/      # Manejo global de excepciones de la aplicación
```
---

## 📋 Requisitos Previos

Antes de ejecutar el proyecto localmente, asegúrate de tener instalado:

**º Java Development Kit (JDK 17 o superior)**

**º Maven 3.x**

**º Gestor de Base de Datos (MySQL o SQL Server)**

## 🔧 Configuración e Instalación Local

1. Clonar el repositorio

```
git clone [https://github.com/TuUsuarioGithub/servicio_tecnico_backend.git](https://github.com/TuUsuarioGithub/servicio_tecnico_backend.git)
cd servicio_tecnico_backend
```


2. Configurar el entorno (Variables de conexión)
Este proyecto utiliza un archivo application.properties para la configuración, el cual está excluido en el control de versiones por seguridad.

Crea un archivo local en src/main/resources/application.properties tomando como base el siguiente ejemplo:


```
Properties
```

```
# Configuración de la Base de Datos (Ejemplo para MySQL)
spring.datasource.url=jdbc:mysql://localhost:3306/db_servicio_tecnico?useSSL=false&serverTimezone=UTC
spring.datasource.username=tu_usuario_db
spring.datasource.password=tu_contraseña_db
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Configuración de Hibernate / JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

```

3. Compilar y ejecutar el proyecto
Usa Maven desde la terminal o corre la clase principal desde tu IDE preferido (IntelliJ IDEA, Eclipse o VS Code):

```
Bash
```

```
mvn clean install
mvn spring-boot:run
```

El servidor iniciará por defecto en el puerto 8080 (http://localhost:8080).

## 📖 Documentación de la API (Swagger UI)
Una vez que el proyecto esté corriendo de manera local, puedes acceder a la interfaz interactiva de Swagger para explorar y probar todos los endpoints disponibles (GET, POST, PUT, DELETE):

## 🔗 URL de acceso: http://localhost:8080/swagger-ui/index.html

(Opcional: Si deseas, puedes añadir aquí una captura de pantalla de tu interfaz de Swagger cuando ejecutes el proyecto).

## 🧑‍💻 Autor
Jhon Ludeña Reyes - Ingeniero en Sistemas / Full Stack Developer

LinkedIn: [Jhon Jefferson Ludeña Reyes](www.linkedin.com/in/jhon-jefferson-ludeña-reyes-847307195)

GitHub: [@JhonLudenaReyes](https://github.com/JhonLudenaReyes)