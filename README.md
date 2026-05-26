# Sistema de Matrícula Universitaria - Universidad SALLE

Este proyecto es una aplicación de consola desarrollada en Java como parte de la asignatura de Lógica de Programación I. Permite simular el proceso de matrícula académica, gestionando un catálogo de cursos y la inscripción de estudiantes.

## Estructura del Proyecto

- `src/App.java`: Código fuente de la aplicación.
- `docs/documentacion.pdf`: Documentación técnica detallada.
- `bin/`: Archivos compilados (se generan al compilar).

## Requisitos

- Java Development Kit (JDK) 8 o superior.

## Instrucciones de Uso

### Compilación

Desde la raíz del proyecto, ejecute:

```bash
javac src/App.java -d bin/
```

### Ejecución

Una vez compilado, ejecute:

```bash
java -cp bin/ App
```

## Funcionalidades Principales

1. **Catálogo de Cursos:** Visualización de cursos, precios y cupos.
2. **Inscripción:** Selección de cursos y validación de disponibilidad.
3. **Beca Parcial:** El sistema informa si el estudiante aplica a beca (Total >= 200 USD).
4. **Descuentos:** Aplicación de becas porcentuales personalizadas.
5. **Gestión:** Opción para añadir nuevos cursos al catálogo.

---
**Desarrollado por:** Miguel Angel Peralta Cano
**Fecha:** 25 de Mayo de 2026
