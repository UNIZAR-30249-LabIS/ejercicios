# Ejercicios LabIS

## Implementación de una relación bidireccional 0..1 a 1 y 1 a 1 en Java
Los paquetes `unizar.labis.zero_one_to_one` y `unizar.labis.one_to_one` implementan una relación 0..1 a 1 y 1 a 1 respectivamente entre un Ministerio y la Persona que lo dirige (el ministro o la ministra). En el caso 0..1 a 1 algunas Personas no son ministras, en el caso 1 a 1 todas las Personas lo son; en ambos casos un Ministerio siempre tiene alguien que lo dirige.

La implementación es de mínimos y sin apoyo en framework ni ORM. El objetivo principal es ilustrar la problemática de implementar desde la base este tipo de relaciones entre objetos, pero apunta algunas cosas que iremos viendo sobre diseño defensivo y basado en contratos.

Para probarlo, clona el repositorio y ejecuta `./gradlew clean test` (necesitas Java 1.8 instalado).
