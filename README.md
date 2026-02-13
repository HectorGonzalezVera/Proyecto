## Proyecto Final DAM – App Moodle Android

## Estudiantes

Héctor González Vera  
Walter Bernardi Correa

## Materia
Desarrollo de Aplicaciones Móviles  

---

## Descripción del Proyecto

Este proyecto consiste en el desarrollo de una aplicación móvil Android que se conecta a un servidor Moodle instalado en Ubuntu Server.

## La aplicación permite:

- Iniciar sesión con credenciales de Moodle
- Obtener información del usuario autenticado
- Listar los cursos en los que está matriculado
- Visualizar el detalle de cada curso
- Mostrar el contenido del curso dentro de la app mediante WebView
- Cerrar sesión

---

## Arquitectura del Proyecto

## Aplicación Android
- Lenguaje: Kotlin
- IDE: Android Studio
- Arquitectura: MVC simplificado
- Librerías:
  - Retrofit
  - Gson
  - RecyclerView
  - WebView

## Servidor
- Sistema Operativo: Ubuntu Server
- Servidor Web: Apache
- Base de datos: MySQL
- Plataforma: Moodle
- Comunicación mediante Web Services REST

---

## Funcionalidades Implementadas

Autenticación con token  
Consumo de API REST de Moodle  
Manejo de RecyclerView  
Navegación entre Activities  
Visualización WebView  
Gestión de sesión  

---

## Configuración del Servidor

El servidor Moodle fue instalado en Ubuntu Server y configurado con:

- Habilitación de Web Services
- Activación del protocolo REST
- Creación de token para usuarios
- Configuración de IP local

## Ejemplo de URL utilizada:
http://172.17.57.46/moodle/

## Respaldo del Servidor

El sistema permite realizar respaldo completo mediante:

- mysqldump para la base de datos
- Compresión de la carpeta moodledata
- Copia del código fuente de Moodle

Los comandos utilizados están documentados en este proyecto.


## Capturas

## Maquina virtual con ubuntu server, moodle y base de datos
<img width="637" height="467" alt="image" src="https://github.com/user-attachments/assets/a57a384b-400d-495b-a4c4-c5f4c0786c9b" />


<img width="979" height="727" alt="image" src="https://github.com/user-attachments/assets/4a39e3ed-3c82-465b-9b04-69804f960bcf" />

## Moodle funcionando en el navegador web de la PC
<img width="1083" height="608" alt="image" src="https://github.com/user-attachments/assets/0d464dd1-da88-4d61-9971-0ca7f511a8c7" />

## Creando el token para conectar la app movil
<img width="921" height="518" alt="image" src="https://github.com/user-attachments/assets/e4033716-a782-486b-92a1-985afbdd2235" />

## Habilitando el Protocolo REST
<img width="1110" height="623" alt="image" src="https://github.com/user-attachments/assets/0a884543-afa3-4ff7-b3b2-e37178714e51" />

## Ejecucion de la app movil mostrando que funciona el login y mostranto los cursos disponibles
<img width="474" height="498" alt="image" src="https://github.com/user-attachments/assets/56df93a5-93c2-4d7d-a7ba-10c4db0e325c" />

## Muestra el contenido del curso al momento de seleccionarlo
<img width="604" height="647" alt="image" src="https://github.com/user-attachments/assets/a7320e7b-eec6-480d-96a4-bd66f084ed01" />




