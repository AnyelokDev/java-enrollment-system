import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class App {

    // Aqui guardamos todos los datos de los cursos que ofrece la universidad
    // Usamos listas paralelas: el nombre del curso en la posicion 0 coincide con el precio en la 0
    static ArrayList<String> nombresCursos = new ArrayList<>(Arrays.asList(
        "Logica de programacion", "Estructuras discretas", 
        "Lenguaje de programacion I", "Logica de programacion II", 
        "Lenguaje de programacion II", "Bases de datos I", 
        "Interaccion humano computador", "Ingenieria de software", 
        "Arquitectura de software", "Seguridad en software I"
    ));

    static ArrayList<Double> valoresCursos = new ArrayList<>(Arrays.asList(
        180.0, 200.0, 190.0, 210.0, 250.0, 195.0, 220.0, 240.0, 185.0, 230.0
    ));

    // Esta lista es clave porque se va actualizando a medida que los alumnos se inscriben
    static ArrayList<Integer> cuposCursos = new ArrayList<>(Arrays.asList(
        35, 30, 25, 40, 20, 30, 25, 15, 35, 28
    ));

    // Estas listas guardan lo que el estudiante actual va eligiendo
    static ArrayList<String> matriculaCursos = new ArrayList<>();
    static ArrayList<Double> matriculaValores = new ArrayList<>();
    
    // Definimos constantes para que el codigo sea facil de mantener
    static final int MAX_CURSOS_ESTUDIANTE = 5;
    static String usuarioActual = "Estudiante 1";

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        // El do-while asegura que el menu se muestre al menos una vez
        do {
            mostrarMenu();
            System.out.print("Seleccione una opcion: ");
            // Validamos que el usuario ingrese un numero para que el programa no se cierre por error
            while (!scanner.hasNextInt()) {
                System.out.println("Error: Por favor ingresa un numero del menu.");
                scanner.next();
            }
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiamos el salto de linea del buffer

            // Switch es mas limpio que usar muchos if-else para el menu
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
        } while (opcion != 0); // Si es 0, la condicion es falsa y sale del bucle
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

    static void verCursos() {
        System.out.println("\n--- LISTA DE CURSOS DISPONIBLES ---");
        // Usamos printf para que la tabla se vea alineada y profesional
        System.out.printf("%-3s | %-30s | %-10s | %-6s\n", "ID", "Curso", "Precio", "Cupos");
        for (int i = 0; i < nombresCursos.size(); i++) {
            System.out.printf("%-3d | %-30s | %-10.2f | %-6d\n", 
                (i + 1), nombresCursos.get(i), valoresCursos.get(i), cuposCursos.get(i));
        }
    }

    static void inscribirCurso() {
        // Primero validamos que no supere el limite de 5 materias por alumno
        if (matriculaCursos.size() >= MAX_CURSOS_ESTUDIANTE) {
            System.out.println("Lo siento, ya tienes el maximo de materias permitidas (" + MAX_CURSOS_ESTUDIANTE + ")");
            return;
        }

        verCursos();
        System.out.print("Escribe el ID del curso: ");
        int id = scanner.nextInt() - 1; // Restamos 1 porque las listas empiezan en 0
        
        // Validacion relacional para asegurar que el ID existe
        if (id >= 0 && id < nombresCursos.size()) {
            String cursoNombre = nombresCursos.get(id);
            
            // Usamos .contains para evitar que el mismo alumno se inscriba dos veces en lo mismo
            if (matriculaCursos.contains(cursoNombre)) {
                System.out.println("Ya estas inscrito en este curso.");
                return;
            }

            // Verificamos si todavia quedan cupos en la universidad para este curso
            if (cuposCursos.get(id) > 0) {
                matriculaCursos.add(cursoNombre);
                matriculaValores.add(valoresCursos.get(id));
                
                // Actualizamos los cupos globales restando 1
                cuposCursos.set(id, cuposCursos.get(id) - 1);
                
                System.out.println("¡Listo! Te has inscrito en " + cursoNombre);
            } else {
                System.out.println("Lo sentimos, no quedan cupos para este curso.");
            }
        } else {
            System.out.println("Ese ID no es valido.");
        }
    }

    static void verMatricula() {
        // Usamos .isEmpty() para saber si la lista no tiene elementos
        if (matriculaCursos.isEmpty()) {
            System.out.println("Tu matricula esta vacia. ¡Inscribe algo!");
            return;
        }
        System.out.println("\n--- TU MATRICULA ACTUAL (" + usuarioActual + ") ---");
        double total = 0;
        for (int i = 0; i < matriculaCursos.size(); i++) {
            System.out.println("- " + matriculaCursos.get(i) + " ($" + matriculaValores.get(i) + ")");
            total += matriculaValores.get(i); // Acumulamos el valor
        }
        System.out.println("Total a pagar sin becas: $" + total);
    }

    static void calcularBecaAutomatica() {
        double total = 0;
        // Usamos un bucle for-each para sumar los precios de forma mas rapida
        for (double v : matriculaValores) total += v;

        if (total == 0) {
            System.out.println("Primero debes inscribir materias.");
            return;
        }

        System.out.println("\n--- SISTEMA AUTOMATICO DE BECAS ---");
        System.out.println("Costo total de materias: $" + total);

        double descuento = 0;
        String tipoBeca = "Ninguna";

        // Usamos una estructura if-else if para determinar la beca segun el monto
        // Usamos operadores logicos y relacionales para los rangos
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

    static void cambiarUsuario() {
        // Esta funcion simula que un estudiante termina y llega otro
        System.out.println("Guardando sesion de " + usuarioActual + "...");
        System.out.print("Nombre del proximo estudiante: ");
        usuarioActual = scanner.nextLine();
        
        // Vaciamos las listas individuales para el nuevo alumno
        // Pero NO tocamos el catalogo global para que los cupos se mantengan restados
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
        System.out.println("   Ejemplo: double subtotal = matriculaValores.get(i) * matriculaCantidades.get(i);");
        
        System.out.println("2. De asignacion: += para acumular el total.");
        System.out.println("   Ejemplo: total += matriculaValores.get(i) * matriculaCantidades.get(i);");
        
        System.out.println("3. Relacionales: >= para verificar si aplica a beca parcial.");
        System.out.println("   Ejemplo: if (total >= 200.0)");
        
        System.out.println("4. Logicos: && para validar rango de descuento y disponibilidad.");
        System.out.println("   Ejemplo: if (cantidad > 0 && cantidad <= cuposCursos.get(index))");
    }
}
