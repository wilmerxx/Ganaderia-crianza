# Proyecto de sistema de crianza de ganaderia
<description>
Integrantes
- Wilmer Shagñay
- Mercy Arrobo
- Johanna Salazar
<p>
El proyecto consiste en un sistema de crianza de ganado, el cual permite llevar un control de los animales, medicinas, alimentacion, reproduccion, control de enfermedades y el area de crianza.
</p>
</description>

### Tecnologias
<a href="https://www.typescriptlang.org/" title="Typescript"><img src="https://github.com/get-icon/geticon/raw/master/icons/typescript-icon.svg" alt="Typescript" width="21px" height="21px"></a>
<a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript" title="JavaScript"><img src="https://github.com/get-icon/geticon/raw/master/icons/javascript.svg" alt="JavaScript" width="21px" height="21px"></a>
<a href="https://angular.io/" title="Angular"><img src="https://github.com/get-icon/geticon/raw/master/icons/angular-icon.svg" alt="Angular" width="21px" height="21px"></a>
<a href="https://www.java.com/" title="Java"><img src="https://github.com/get-icon/geticon/raw/master/icons/java.svg" alt="Java" width="21px" height="21px"></a>
<a href="https://nodejs.org/" title="Node.js"><img src="https://github.com/get-icon/geticon/raw/master/icons/nodejs-icon.svg" alt="Node.js" width="21px" height="21px"></a>
<a href="https://getbootstrap.com/" title="Bootstrap"><img src="https://github.com/get-icon/geticon/raw/master/icons/bootstrap.svg" alt="Bootstrap" width="21px" height="21px"></a>
<a href="https://www.w3.org/TR/html5/" title="HTML5"><img src="https://github.com/get-icon/geticon/raw/master/icons/html-5.svg" alt="HTML5" width="21px" height="21px"></a>
<a href="https://www.w3.org/TR/CSS/" title="CSS3"><img src="https://github.com/get-icon/geticon/raw/master/icons/css-3.svg" alt="CSS3" width="21px" height="21px"></a>
<a href="https://spring.io/" title="Spring"><img src="https://github.com/get-icon/geticon/raw/master/icons/spring.svg" alt="Spring" width="21px" height="21px"></a>
<a href="https://www.docker.com/" title="docker"><img src="https://github.com/get-icon/geticon/raw/master/icons/docker-icon.svg" alt="docker" width="21px" height="21px"></a>
<a href="https://git-scm.com/" title="Git"><img src="https://github.com/get-icon/geticon/raw/master/icons/git-icon.svg" alt="Git" width="21px" height="21px"></a>

## Backend 
- Sprint boot <img src="">
## Frontend
- Angular
## Maquina virtual 
- Docker

## Clases
- Ganado
- Medicina
- Alimentacion
- Reproduccion
- Control de enfermedades
- Area de crianza

## Diagrama de clases
 <img src="diagrama-clases.jpg" alt="Diagrama de clases" width="100%" height="100%"> 

## Diagrama de secuencia
 <img src="diagrama-secuencia.jpg" alt="Diagrama de clases" width="100%" height="100%">

## Comandos para ejecutar docker
### Crear imagen
```mermaid
docker build -t ganaderia .
```
### Crear contenedor
```mermaid
docker run -d -p 8080:8080 ganaderia
```
### Ver contenedores
```mermaid
docker ps
```
### Eliminar contenedor
```mermaid
docker rm -f <id_contenedor>
```
### Eliminar imagen
```mermaid
docker rmi -f <id_imagen>
```
### Ver logs
```mermaid
docker logs <id_contenedor>
```
### Ver imagenes
```mermaid
docker images
```
### Ver contenedores
```mermaid
docker ps
```
### Ver contenedores detenidos
```mermaid
docker ps -a
```
###  Para correr el docker compose
```mermaid
docker-compose up
```
### Para correr el docker compose en segundo plano
```mermaid
docker-compose up -d
```
### Para parar el docker compose
```mermaid
docker-compose down
```
### Para parar el docker compose y eliminar los contenedores
```mermaid
docker-compose down --rmi all
```
### Para parar el docker compose y eliminar los contenedores y las imagenes
```mermaid
docker-compose down --rmi all --volumes
```
### Para parar el docker compose y eliminar los contenedores y las imagenes y las redes
```mermaid
docker-compose down --rmi all --volumes --remove-orphans
```
### Para parar el docker compose y eliminar los contenedores y las imagenes y las redes y los volumenes
```mermaid
docker-compose down --rmi all --volumes --remove-orphans --volumes
```
### Para parar el docker compose y eliminar los contenedores y las imagenes y las redes y los volumenes y los orfanos
```mermaid
docker-compose down --rmi all --volumes --remove-orphans --volumes --remove-orphans
```
### Para parar el docker compose y eliminar los contenedores y las imagenes y las redes y los volumenes todos los orfanos
```mermaid
docker-compose down --rmi all --volumes --remove-orphans --volumes --remove-orphans --rmi all
```
## Comandos para ejecutar angular
### Para instalar angular
```mermaid
npm install -g @angular/cli
```
### Para crear un proyecto de angular
```mermaid
ng new <nombre_proyecto>
```
### Para crear un proyecto de angular sin repositorio de git
```mermaid
ng new <nombre_proyecto> --skip-git
```
### Crear un componente
```mermaid
ng generate component <nombre_componente>
```
### Crear un servicio
```mermaid
ng generate service <nombre_servicio>
```
### Crear un modulo
```mermaid
ng generate module <nombre_modulo>
```
### Crear un modulo de rutas
```mermaid
ng generate module app-routing --flat --module=app
```
### Crear un modulo de rutas con un prefijo
```mermaid
ng generate module app-routing --flat --module=app --routingPrefix=app
```
### Para correr el proyecto de angular y se abra en el navegador
```mermaid
ng serve -o
```
### Para correr el proyecto de angular en un puerto especifico
```mermaid
ng serve --port <numero_puerto>
```
### Para correr el proyecto de angular en un puerto especifico y se abra en el navegador
```mermaid
ng serve --port <numero_puerto> -o
```
## Comandos de git
### Para inicializar un repositorio de git
```mermaid
git init
```
### Para agregar todos los archivos al repositorio de git
```mermaid
git add .
```
### Realizar un commit con un mensaje
```mermaid
git commit -m "mensaje"
```
### Crear la rama master
```mermaid
git branch -M master
```
### Iniciar sesion en git
```mermaid
git config --global user.email "email"
git config --global user.name "nombre"
```
### Para ver los repositorios remotos
```mermaid
git remote -v
```
### Para agregar un repositorio remoto
```mermaid
git remote add origin <url_repositorio>
```
### Para subir los cambios al repositorio remoto
```mermaid
git push -u origin master
```
### Para clonar un repositorio
```mermaid
git clone <url_repositorio>
```
### Para clonar un rama especifica de un repositorio
```mermaid
git clone -b <nombre_rama> <url_repositorio>
```
### Para ver el estado de los archivos
```mermaid
git status
```
### Para ver el historial de los commits
```mermaid
git log
```
### Para ver el historial de los commits de una forma mas resumida
```mermaid
git log --oneline
```
### Enviar cambios al repositorio remoto
```mermaid
git push
```
### Para ver las ramas
```mermaid
git branch
```
### Para crear una rama
```mermaid
git branch <nombre_rama>
```
### Para cambiar de rama
```mermaid
git checkout <nombre_rama>
```
### Para crear una rama y cambiar a esa rama
```mermaid
git checkout -b <nombre_rama>
```
