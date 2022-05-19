# poc-bank

## Stack usado 🛠️

* Spring boot
* Maven
* H2
* Junit, mockito

## Ejecutando las pruebas JUnit ⚙️

```
mvn test
```

NOTA: Solo se ha testeado la funcionalidad de usuario. Se aplicará la misma filosofía para cuentas y transferencias en futuras releases.

## Instalación 🔧

Generacion del jar:

```
mvn clean install package -DskipTests
```

Ejecucción del jar:

```
java -jar target/poc-bank-0.0.1-SNAPSHOT.jar
```

Para acceder a la consola h2 en memoria:

http://localhost:8080/h2-console/


## Ejecutando pruebas API 🔩

En el fichero: src/main/resources/Poc-builders.postman_collection.json se pueden encontrar distintas formas de consumir las apis importándolo en el software postman.


