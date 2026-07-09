# PMAP - Sistema de Gestion Academica

Este proyecto implementa una version funcional y acotada de PMAP, enfocada en tres modulos principales:

- Autenticacion y usuarios
- Gestion de materias
- Gestion de sesiones de estudio

El repositorio esta dividido en dos partes:

- Backend: API REST con Spring Boot
- Frontend: aplicacion web con React y Vite

## Funcionalidad principal

### Autenticacion y usuarios

- Registro de usuarios
- Inicio de sesion
- Generacion de token JWT
- Listado, creacion, edicion, activacion, desactivacion y eliminacion de usuarios

### Gestion de materias

- Creacion de materias
- Consulta por id y listado general
- Edicion de materias
- Activacion y desactivacion
- Eliminacion logica o fisica segun la logica del servicio

### Gestion de sesiones de estudio

- Creacion de sesiones
- Consulta por id y listado general
- Edicion de sesiones
- Eliminacion de sesiones

## Tecnologias utilizadas

### Backend

- Java 17
- Spring Boot 3.3.5
- Spring Web
- Spring Data JPA
- Spring Security
- Validation
- PostgreSQL
- JWT con jjwt
- Lombok
- springdoc-openapi para Swagger

### Frontend

- React 18
- Vite
- React Router DOM
- Axios
- React Hook Form
- SweetAlert2
- Lucide React
- Tailwind CSS 3

## Requisitos previos

Antes de ejecutar el proyecto necesitas tener instalado:

- Java 17
- Maven 3.9 o superior, o usar el wrapper incluido en Backend
- Node.js 18 o superior
- npm
- PostgreSQL
- Un editor como VS Code

## Estructura del proyecto

```text
Backend/
Frontend/
.gitignore
README.md
```

### Backend

El backend expone una API REST organizada por capas:

- config
- controller
- dto
- entity
- exception
- mapper
- repository
- security
- service
- service.impl
- util

### Frontend

El frontend consume la API del backend y organiza la interfaz en:

- routes
- layouts
- components
- pages
- services
- hooks
- utils

## Base de datos

El proyecto usa PostgreSQL con la base de datos:

- Nombre: pmap_db
- Usuario: postgres
- Password: 12345

Tambien existe un script SQL de apoyo en:

- Backend/sql/pmap_db.sql

Ese script crea la base de datos, tablas, restricciones y datos de ejemplo.

## Configuracion del backend

La configuracion principal esta en:

- Backend/src/main/resources/application.properties

Valores importantes:

- Puerto: 8080
- Base de datos: jdbc:postgresql://localhost:5432/pmap_db
- Swagger: /swagger-ui.html
- Hibernate ddl-auto: update


## Como ejecutar el proyecto

### 1. Levantar PostgreSQL

Asegurate de que PostgreSQL este activo y que la base de datos pmap_db exista.

Si vas a crearla manualmente, puedes usar el script SQL incluido en Backend/sql/pmap_db.sql.

### 2. Ejecutar el backend

Desde la carpeta Backend:

```powershell
cd Backend
.\mvnw spring-boot:run
```

Si prefieres compilar primero:

```powershell
cd Backend
.\mvnw clean compile
.\mvnw spring-boot:run
```

La API quedara disponible en:

- http://localhost:8080

Swagger UI:

- http://localhost:8080/swagger-ui.html

### 3. Ejecutar el frontend

Desde la carpeta Frontend:

```powershell
cd Frontend
npm install
npm run dev
```

La aplicacion web quedara disponible en:

- http://localhost:5173

### 4. Flujo normal de uso

1. Abre el frontend en el navegador.
2. Inicia sesion con un usuario valido.
3. Usa el panel para gestionar usuarios, materias y sesiones de estudio.
4. Si necesitas revisar la API directamente, usa Swagger.

## Endpoints principales

### Autenticacion

- POST /api/auth/register
- POST /api/auth/login

### Usuarios

- POST /api/usuarios
- GET /api/usuarios
- GET /api/usuarios/{id}
- PUT /api/usuarios/{id}
- PUT /api/usuarios/{id}/activate
- PUT /api/usuarios/{id}/deactivate
- DELETE /api/usuarios/{id}

### Materias

- POST /api/materias
- GET /api/materias
- GET /api/materias/{id}
- PUT /api/materias/{id}
- PUT /api/materias/{id}/activate
- PUT /api/materias/{id}/deactivate
- DELETE /api/materias/{id}

### Sesiones de estudio

- POST /api/sesiones
- GET /api/sesiones
- GET /api/sesiones/{id}
- PUT /api/sesiones/{id}
- DELETE /api/sesiones/{id}

## Notas de desarrollo

- El backend esta compilado para Java 17.
- El frontend usa JSX y archivos JavaScript estandar.
- El proyecto tiene configurado un archivo .gitignore en la raiz para evitar subir dependencias, builds y archivos temporales.
- Si cambias la conexion a la base de datos, revisa Backend/src/main/resources/application.properties.

## Solucion de problemas

### El backend no inicia

Revisa estos puntos:

- PostgreSQL esta ejecutandose
- La base de datos pmap_db existe
- El usuario postgres tiene la clave correcta
- El puerto 8080 no esta ocupado por otra aplicacion

### Error de Java incompatible

Si aparece un error de version de clase o LinkageError, confirma que estas ejecutando el proyecto con Java 17.

### El frontend no carga

Revisa estos puntos:

- npm install se ejecuto correctamente
- Vite esta corriendo en el puerto 5173
- El backend esta disponible en http://localhost:8080

## Comandos utiles

### Backend

```powershell
cd Backend
.\mvnw clean compile
.\mvnw test
.\mvnw spring-boot:run
```

### Frontend

```powershell
cd Frontend
npm install
npm run dev
npm run build
npm run preview
```

## Autor

Proyecto del sena de la actividad GA7-220501096-AA3-EV01Codificación de módulos del software stand-alone, web y movil de Diego Tique Ramirez.
