import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class App {

    // =========================================================================
    // [SECCIÓN 1: Variables Globales y Listas Paralelas]
    // =========================================================================
    // Aquí simulamos la base de datos de la universidad.
    // Usamos listas paralelas para relacionar datos sin usar objetos (POO).
    // El índice 0 de 'nombresCursos' corresponde al precio en 'valoresCursos'
    // y a los cupos disponibles en 'cuposCursos'.
    static ArrayList<String> nombresCursos = new ArrayList<>(Arrays.asList(
            "Logica de programacion", "Estructuras discretas",
            "Lenguaje de programacion I", "Logica de programacion II",
            "Lenguaje de programacion II", "Bases de datos I",
            "Interaccion humano computador", "Ingenieria de software",
            "Arquitectura de software", "Seguridad en software I"));

    static ArrayList<Double> valoresCursos = new ArrayList<>(Arrays.asList(
            180.0, 200.0, 190.0, 210.0, 250.0, 195.0, 220.0, 240.0, 185.0, 230.0));

    // Esta lista disminuye cuando un estudiante se inscribe. Es nuestra fuente de verdad.
    static ArrayList<Integer> cuposCursos = new ArrayList<>(Arrays.asList(
            35, 30, 25, 40, 20, 30, 25, 15, 35, 28));

    // =========================================================================
    // [SECCIÓN 2: Memoria del Estudiante Actual]
    // =========================================================================
    // Estas listas son el "carrito" del alumno actual.
    // Al cambiar de usuario, estas listas se limpian (.clear()), pero los cupos globales se mantienen.
    static ArrayList<String> matriculaCursos = new ArrayList<>();
    static ArrayList<Double> matriculaValores = new ArrayList<>();

    // Constantes y variables de estado del sistema
    static final int MAX_CURSOS_ESTUDIANTE = 5;
    static String usuarioActual = "Estudiante 1";
    static Scanner scanner = new Scanner(System.in);

    // =========================================================================
    // [SECCIÓN 3: Ciclo Principal y Menú]
    // =========================================================================
    public static void main(String[] args) {
        int opcion = -1;
        
        // Ciclo do-while: Garantiza que el menú se imprima por lo menos la primera vez.
        do {
            mostrarMenu();
            System.out.print("Seleccione una opcion: ");
            
            // [MANEJO DE ERROR 1: Validación de entrada]
            // Evita que el programa colapse (InputMismatchException) si el usuario ingresa una letra.
            while (!scanner.hasNextInt()) {
                System.out.println("Error: Por favor ingresa un numero del menu.");
                scanner.next(); // Limpia la "basura" ingresada en el teclado
            }
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el "Enter" fantasma del buffer

            // Switch: Estructura de control más limpia y eficiente que múltiples if-else.
            switch (opcion) {
                case 1: verCursos(); break;
                case 2: inscribirCurso(); break;
                case 3: verMatricula(); break;
                case 4: calcularBecaAutomatica(); break;
                case 5: cambiarUsuario(); break;
                case 6: agregarCursoCatalogo(); break;
                case 7: verOperadoresSistema(); break;
                case 0: System.out.println("Cerrando sistema... ¡Hasta pronto!"); break;
                default: System.out.println("Esa opcion no existe, intenta de nuevo.");
            }
        } while (opcion != 0); // La condición de salida del sistema.
    }

    static void mostrarMenu() {
        System.out.println("\n============================================");
        System.out.println("   UNIVERSIDAD SALLE - MATRICULAS");
        System.out.println("   USUARIO ACTUAL: " + usuarioActual);
        System.out.println("============================================");
        System.out.println("1. Ver catalogo de cursos");
        System.out.println("2. Inscribir materia (1 cupo)");
        System.out.println("3. Ver mi matricula (" + matriculaCursos.size() + "/" + MAX_CURSOS_ESTUDIANTE + ")");
        System.out.println("4. Ver total y Becas");
        System.out.println("5. Cambiar de estudiante (Siguiente en la fila)");
        System.out.println("6. Agregar curso nuevo (Admin)");
        System.out.println("7. Explicacion de operadores");
        System.out.println("0. Salir");
    }

    // =========================================================================
    // [SECCIÓN 4: Lógica de Inscripción (El corazón del sistema)]
    // =========================================================================
    static void verCursos() {
        System.out.println("\n--- LISTA DE CURSOS DISPONIBLES ---");
        System.out.printf("%-3s | %-30s | %-10s | %-6s\n", "ID", "Curso", "Precio", "Cupos");
        
        // Ciclo for: Itera usando el índice común 'i' para conectar las listas paralelas.
        for (int i = 0; i < nombresCursos.size(); i++) {
            System.out.printf("%-3d | %-30s | %-10.2f | %-6d\n",
                    (i + 1), nombresCursos.get(i), valoresCursos.get(i), cuposCursos.get(i));
        }
    }

    static void inscribirCurso() {
        // Validación 1: Operador relacional para limitar la carga académica.
        if (matriculaCursos.size() >= MAX_CURSOS_ESTUDIANTE) {
            System.out.println("Lo siento, ya tienes el maximo de materias permitidas (" + MAX_CURSOS_ESTUDIANTE + ")");
            return;
        }

        verCursos();
        System.out.print("Escribe el ID del curso: ");
        int id = scanner.nextInt() - 1; // Restamos 1 para pasar de la vista humana (1-10) al índice Java (0-9)

        // [MANEJO DE ERROR 2: Evitar IndexOutOfBoundsException]
        // Operador AND (&&): Asegura que el ID esté dentro del rango válido de las listas.
        if (id >= 0 && id < nombresCursos.size()) {
            String cursoNombre = nombresCursos.get(id);

            // [MANEJO DE ERROR 3: Evitar materia duplicada]
            if (matriculaCursos.contains(cursoNombre)) {
                System.out.println("Ya estas inscrito en este curso.");
                return;
            }

            // [MANEJO DE ERROR 4: Control de Cupos Negativos]
            if (cuposCursos.get(id) > 0) {
                // Almacenamos la elección en la memoria del estudiante
                matriculaCursos.add(cursoNombre);
                matriculaValores.add(valoresCursos.get(id));

                // Restamos un cupo de la lista global usando .set()
                cuposCursos.set(id, cuposCursos.get(id) - 1);
                System.out.println("¡Listo! Te has inscrito en " + cursoNombre);
            } else {
                System.out.println("Lo sentimos, no quedan cupos para este curso.");
            }
        } else {
            System.out.println("Ese ID no es valido.");
        }
    }

    // =========================================================================
    // [SECCIÓN 5: Cálculos Matemáticos y Sistema de Becas]
    // =========================================================================
    static void verMatricula() {
        if (matriculaCursos.isEmpty()) {
            System.out.println("Tu matricula esta vacia. ¡Inscribe algo!");
            return;
        }
        
        System.out.println("\n--- TU MATRICULA ACTUAL (" + usuarioActual + ") ---");
        double total = 0;
        
        for (int i = 0; i < matriculaCursos.size(); i++) {
            System.out.println("- " + matriculaCursos.get(i) + " ($" + matriculaValores.get(i) + ")");
            // Operador de Asignación Acumulativa: Va sumando cada valor iterado al total.
            total += matriculaValores.get(i); 
        }
        System.out.println("Total a pagar sin becas: $" + total);
    }

    static void calcularBecaAutomatica() {
        double total = 0;
        
        // Bucle for-each: Iteración rápida sin necesidad de usar un índice tradicional.
        for (double v : matriculaValores) total += v;

        if (total == 0) {
            System.out.println("Primero debes inscribir materias.");
            return;
        }

        System.out.println("\n--- SISTEMA AUTOMATICO DE BECAS ---");
        System.out.println("Costo total de materias: $" + total);

        double descuento = 0;
        String tipoBeca = "Ninguna";

        // Estructura if - else if: Ideal para categorizaciones.
        // El orden de mayor a menor garantiza que nadie se quede atrapado en una beca inferior.
        if (total >= 1000) {
            descuento = 0.60;
            tipoBeca = "Beca Excelencia (60%)";
        } else if (total >= 600) {
            descuento = 0.40;
            tipoBeca = "Beca Merito (40%)";
        } else if (total >= 200) {
            descuento = 0.20;
            tipoBeca = "Beca Parcial (20%)";
        }

        if (descuento > 0) {
            double ahorro = total * descuento;
            System.out.println("¡Buenas noticias! Tienes: " + tipoBeca);
            System.out.println("Te ahorras: $" + ahorro);
            System.out.println("TOTAL FINAL A PAGAR: $" + (total - ahorro));
        } else {
            System.out.println("No aplicas a becas aun. Necesitas un total mayor a $200.");
        }
    }

    // =========================================================================
    // [SECCIÓN 6: Administración y Cambio de Usuario]
    // =========================================================================
    static void cambiarUsuario() {
        System.out.println("Guardando sesion de " + usuarioActual + "...");
        System.out.print("Nombre del proximo estudiante: ");
        usuarioActual = scanner.nextLine();

        // Limpiamos la sesión del estudiante para empezar de cero, pero el catálogo global no se toca.
        matriculaCursos.clear();
        matriculaValores.clear();

        System.out.println("¡Bienvenido " + usuarioActual + "! Puedes empezar tu matricula.");
    }

    static void agregarCursoCatalogo() {
        System.out.print("Nombre del nuevo curso: ");
        String nombre = scanner.nextLine();
        System.out.print("Valor de la matricula (USD): ");
        double valor = scanner.nextDouble();
        System.out.print("Cupos iniciales: ");
        int cupos = scanner.nextInt();

        // Operador Lógico AND múltiple: Valida tres condiciones a la vez antes de insertar datos.
        if (!nombre.isEmpty() && valor > 0 && cupos >= 0) {
            nombresCursos.add(nombre);
            valoresCursos.add(valor);
            cuposCursos.add(cupos);
            System.out.println("Curso agregado al catalogo.");
        } else {
            System.out.println("Datos no validos. Verifique que el valor sea > 0 y los cupos >= 0.");
        }
    }

    static void verOperadoresSistema() {
        System.out.println("\n---OPERADORES USADOS EN EL SISTEMA---");
        System.out.println("1. Aritmeticos: * y + para calcular subtotales y totales.");
        System.out.println("2. De asignacion: += para acumular el total.");
        System.out.println("3. Relacionales: >= para verificar si aplica a beca parcial.");
        System.out.println("4. Logicos: && para validar rango de descuento y disponibilidad.");
    }
}