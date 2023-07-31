## API REST Spring Boot

El siguiente archivo README es para la API REST con Spring Boot la cual proporciona endponts para con la base de datos en SQL. Esta API
permite realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) los registros de la base de datos a través de peticiones HTTP.

## Configuración Inicial
Los requisitos para poder ejecutar la API son los siguientes:

1. Java 11 o superior
2. Servidor de base de datos SQL (MySQL, PostgresSQL, SQLServer, etc.)
3. Herramienta para ejecutar las peticiones HTTP (Navegador, Postman, Insomnia, etc.)

## Configuración de Base de Datos
1. Crear base de datos en el servidor SQL de tu preferencia
2. Tener las credenciales de conexion a tu servidor de base de datos (Usuario y Contraseña)
3. Modificar el archivo application.yml ubicado en el directorio src/main/resources. Modificar las variables url, username y password con tu respectiva configuración de base de datos, como se puede apreciar  continuación.

    datasource:
        - url: jdbc:mysql://localhost:3306/nombre_de_tu_base_de_datos
        - username: usuario
        - password: contraseña

## Ejecutar Aplicación
1. Clona el repositorio o descarga los archivos en tu maquina
2. Abre el proyecto en el ID de tu preferencia
3. Compila y ejecuta la aplicación

## EndPoints
La API proporciona los siguientes endpoints para la interacción con la base de datos
- GET /Entidad: Obtiene todos los elementos almacenados en la base de datos.
- GET /Entidad/Filtrar/{estatus}: Obtiene todos los elementos de la base de datos filtrados por activos pasamos como parámetro 1 o inactivos pasamos como parámetro 0.
- GET /Entidad/{id}: Obtiene un elemento específico por su ID.
- POST /Entidad: Crea un nuevo elemento en la base de datos.
- PUT /Entidad/{id}: Actualiza un elemento existente por su ID.
- DELETE /Entidad/{id}: Elimina un elemento existente por su ID.

## Formato de datos
La API espera recibir y enviar datos en formato JSON. Asegúrate de configurar correctamente los encabezados de tus peticiones para que la API pueda entender el formato de datos que envías y también responderte con datos en formato JSON.

A continuación puedes encontrar un ejemplo de cómo crear un nuevo elemento utilizando la API con cURL:
**curl -X POST -H "Content-Type: application/json" -d '{"nombre": "Ejemplo", "activo": 1}' http://localhost:8080/Materia**

## Respuestas
La API responderá con códigos de estado HTTP apropiados y mensajes de respuesta en JSON. Asegúrate de revisar los códigos de estado y el contenido de las respuestas para comprender el resultado de tus peticiones.

## Contribuciones
Si encuentras errores o tienes sugerencias para mejorar esta API, no dudes en hacer una solicitud de extracción o abrir un problema en el repositorio.

¡Gracias por contribuir!