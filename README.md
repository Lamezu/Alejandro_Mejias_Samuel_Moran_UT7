# Alejandro_Mejias_Samuel_Moran_UT7

Biblioteca API

Este proyecto es una API RESTful para la gestión de una biblioteca, desarrollada con Spring Boot. Permite gestionar autores y libros con sus respectivas relaciones.
Tecnologías utilizadas

Java 17
Spring Boot 3.4.5
Spring Data JPA
MySQL
Maven

Configuración del proyecto
Requisitos previos

JDK 17 o superior
MySQL 8.0
Maven 3.6.3 o superior (incluido como wrapper en el proyecto)

Base de datos
La aplicación está configurada para conectarse a una base de datos MySQL:

Crear una base de datos llamada biblioteca:

sqlCREATE DATABASE biblioteca;

Configurar usuario y contraseña en application.properties según tus credenciales locales:

propertiesspring.datasource.username=root
spring.datasource.password=tu_contraseña

Ejecutar la aplicación

La aplicación se ejecutará en http://localhost:8080

Estructura del proyecto
com.alejandro_samuel.biblioteca
├── controller        # Controladores REST
├── model             # Entidades
├── repository        # Interfaces de acceso a datos
└── service           # Lógica de negocio
Modelos
Autor

id: Long
nombre: String
nacionalidad: String
libros: List<Libro> (relación One-to-Many)

Libro

id: Long
titulo: String
isbn: String
anioPublicacion: int
autor: Autor (relación Many-to-One)

API Endpoints

Autores

Método HTTPURLDescripciónGET/api/v1/autoresObtener todos los autoresGET/api/v1/autores/{id}Obtener autor por IDPOST/api/v1/autoresCrear un nuevo autorPUT/api/v1/autores/{id}Actualizar un autor existenteDELETE/api/v1/autores/{id}Eliminar un autorGET/api/v1/autores/{id}/librosObtener libros de un autor

Libros

Método HTTPURLDescripciónGET/api/v1/librosObtener todos los librosGET/api/v1/libros/{id}Obtener libro por IDPOST/api/v1/librosCrear un nuevo libroPUT/api/v1/libros/{id}Actualizar un libro existenteDELETE/api/v1/libros/{id}Eliminar un libroGET/api/v1/libros/buscarBuscar libros con filtros

Ejemplos de uso (Postman)

1. Crear autor
Endpoint: POST /api/v1/autores
Request Body:
json{
  "nombre": "Gabriel García Márquez",
  "nacionalidad": "Colombia"
}
Response (201 Created):
json{
  "id": 1,
  "nombre": "Gabriel García Márquez",
  "nacionalidad": "Colombia",
  "libros": []
}
2. Crear libro
Endpoint: POST /api/v1/libros
Request Body:
json{
  "titulo": "Cien años de soledad",
  "isbn": "978-0307474728",
  "anioPublicacion": 1967
}
Response (201 Created):
json{
    "id": 2,
    "titulo": "Cien años de soledad",
    "isbn": "978-0307474728",
    "anioPublicacion": 1967,
    "autor": null
}
3. Buscar libros con filtros
Endpoint: GET /api/v1/libros/buscar?titulo=Cien&anio=1967&sortBy=titulo&order=asc
Response (200 OK):
json[
  {
    "id": 1,
    "titulo": "Cien años de soledad",
    "isbn": "978-0307474728",
    "anioPublicacion": 1967,
    "autor": {
      "id": 1,
      "nombre": "Gabriel García Márquez",
      "nacionalidad": "Colombia"
    }
  }
]
Consideraciones adicionales

La API implementa relaciones bidireccionales entre Autor y Libro, manteniendo la integridad referencial.
Al eliminar un autor, también se eliminan todos sus libros asociados (cascade delete).
Los endpoints de búsqueda soportan filtros y ordenación por diferentes campos.
