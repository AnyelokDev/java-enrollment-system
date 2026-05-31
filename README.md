# Sistema de Matrícula Universitaria - Universidad SALLE

## Información del Proyecto
- **Programa:** Ingeniería de software
- **Materia:** Lógica de programación 1
- **Unidad:** 3
- **Docente:** JOHAN MANUEL GORDILLO MESA
- **Estudiante:** Miguel Angel Peralta Cano
- **Fecha:** 31/05/2026

## Descripción del Proyecto

El presente proyecto es una aplicación desarrollada en el lenguaje de programación Java, ejecutada en un entorno de consola. Su objetivo principal es aplicar los conceptos de estructuras de datos (listas paralelas), control de flujo y operadores lógicos, relacionales y aritméticos en un entorno simulado de inscripciones universitarias. El sistema permite gestionar el proceso de matrícula académica, la administración del catálogo de cursos, la validación de cupos y la asignación automática de becas.

## Características Principales

- **Gestión de Catálogo:** Visualización detallada de los cursos disponibles, precios y control de cupos en tiempo real.
- **Inscripción Inteligente:** Validación de límites de cursos por estudiante (máximo 5) y control de cupos (1 cupo por materia para evitar acaparamiento).
- **Asignación de Becas Automática:**
  - Beca Excelencia (60%): Para montos mayores o iguales a 1000 USD.
  - Beca Mérito (40%): Para montos mayores o iguales a 600 USD.
  - Beca Parcial (20%): Para montos mayores o iguales a 200 USD.
- **Soporte Multi-usuario:** Capacidad de procesar la matrícula de múltiples estudiantes de manera secuencial, manteniendo el estado global del inventario de cursos.
- **Administración:** Funcionalidad para que los administradores agreguen nuevos cursos al catálogo en tiempo de ejecución.

## Estructura del Proyecto

```text
sistema-matricula-salle/
├── src/
│   └── App.java            # Código fuente principal de la aplicación
├── docs/
│   └── documentacion.pdf   # Documento técnico generado con la justificación del código
├── bin/                    # Binarios compilados (.class)
└── README.md               # Este archivo de presentación
```

## Requisitos del Sistema

- **Java Development Kit (JDK):** Versión 8 o superior.

## Instalación y Uso

1. Clonar el repositorio en su entorno local.
2. Navegar al directorio raíz del proyecto mediante la terminal:
   ```bash
   cd sistema-matricula-salle
   ```
3. Compilar el código fuente:
   ```bash
   javac src/App.java -d bin/
   ```
4. Ejecutar la aplicación:
   ```bash
   java -cp bin/ App
   ```

## Documentación

Para comprender a fondo la arquitectura, el diseño de las estructuras de datos (listas paralelas) y las justificaciones a las validaciones clave empleadas en la resolución del problema, por favor consulte el documento técnico ubicado en `docs/documentacion.pdf`.