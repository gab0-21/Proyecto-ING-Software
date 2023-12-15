import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Clase que representa a un usuario
class Usuario {
    private String nombreUsuario;
    private String contrasena;
    private String cita; // Información de la cita programada

    // Constructor
    public Usuario(String nombreUsuario, String contrasena) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.cita = null; // Inicialmente, no hay cita programada
    }

    // Métodos para obtener información del usuario
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getCita() {
        return cita;
    }

    // Método para establecer la información de la cita
    public void setCita(String cita) {
        this.cita = cita;
    }
}

// Clase que maneja la autenticación y registro de usuarios
class Autenticacion {
    private Map<String, Usuario> usuariosRegistrados; // Almacena usuarios registrados

    // Constructor
    public Autenticacion() {
        this.usuariosRegistrados = new HashMap<>();
    }

    // Método para registrar un nuevo usuario
    public void registrarUsuario(String nombreUsuario, String contrasena) {
        Usuario nuevoUsuario = new Usuario(nombreUsuario, contrasena);
        usuariosRegistrados.put(nombreUsuario, nuevoUsuario);
        System.out.println("Usuario registrado exitosamente.");
    }

    // Método para autenticar un usuario
    public boolean autenticar(String nombreUsuario, String contrasena) {
        Usuario usuario = usuariosRegistrados.get(nombreUsuario);
        return usuario != null && usuario.getContrasena().equals(contrasena);
    }

    // Método para obtener un usuario por nombre de usuario
    public Usuario getUsuario(String nombreUsuario) {
        return usuariosRegistrados.get(nombreUsuario);
    }
}

// Clase principal que contiene el programa principal
public class InicioSesion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Autenticacion autenticacion = new Autenticacion(); // Objeto para manejar autenticación

        int opcion;
        Usuario usuarioAutenticado = null; // Usuario autenticado actualmente

        do {
            // Menú principal
            System.out.println("\nMenú:");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después de nextInt()

            switch (opcion) {
                case 1:
                    // Registro de usuario
                    System.out.print("Registro de usuario\nIngrese nombre de usuario: ");
                    String nuevoUsuario = scanner.nextLine();

                    System.out.print("Ingrese contraseña: ");
                    String nuevaContrasena = scanner.nextLine();

                    autenticacion.registrarUsuario(nuevoUsuario, nuevaContrasena);
                    break;
                case 2:
                    // Inicio de sesión
                    System.out.print("\nInicio de sesión\nIngrese nombre de usuario: ");
                    String nombreUsuario = scanner.nextLine();

                    System.out.print("Ingrese contraseña: ");
                    String contrasena = scanner.nextLine();

                    if (autenticacion.autenticar(nombreUsuario, contrasena)) {
                        System.out.println("Inicio de sesión exitoso. ¡Bienvenido!");
                        usuarioAutenticado = autenticacion.getUsuario(nombreUsuario);
                        mostrarMenuCitas(scanner, usuarioAutenticado); // Llama al menú de citas
                    } else {
                        System.out.println("Nombre de usuario o contraseña incorrectos. Inicio de sesión fallido.");
                    }
                    break;
                case 3:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
                    break;
            }
        } while (opcion != 3);

        scanner.close();
    }

    // Método que muestra el menú de citas
    private static void mostrarMenuCitas(Scanner scanner, Usuario usuario) {
        int opcionCitas;
        do {
            // Menú de citas
            System.out.println("\nMenú de Citas:");
            System.out.println("1. Programar cita");
            System.out.println("2. Cancelar cita");
            System.out.println("3. Mostrar citas registradas");
            System.out.println("4. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            opcionCitas = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después de nextInt()

            switch (opcionCitas) {
                case 1:
                    // Submenú para programar cita
                    subMenuProgramarCita(scanner, usuario);
                    break;
                case 2:
                    // Cancelar cita
                    if (usuario.getCita() != null) {
                        System.out.println("Cita actual: " + usuario.getCita());
                        System.out.print("¿Está seguro de que desea cancelar la cita? (S/N): ");
                        String confirmacion = scanner.nextLine();
                        if (confirmacion.equalsIgnoreCase("S")) {
                            usuario.setCita(null);
                            System.out.println("Cita cancelada exitosamente.");
                        }
                    } else {
                        System.out.println("No hay cita programada para cancelar.");
                    }
                    break;
                case 3:
                    // Mostrar citas registradas
                    if (usuario.getCita() != null) {
                        System.out.println("Citas registradas: " + usuario.getCita());
                    } else {
                        System.out.println("No hay citas registradas.");
                    }
                    break;
                case 4:
                    System.out.println("Volviendo al menú principal.");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
                    break;
            }
        } while (opcionCitas != 4);
    }

    // Método que muestra el submenú para programar la cita
    private static void subMenuProgramarCita(Scanner scanner, Usuario usuario) {
        System.out.println("\nSubmenú Programar Cita:");
        System.out.println("1. Programar día de la cita");
        System.out.println("2. Programar hora de la cita");
        System.out.println("3. Volver al menú de citas");
        System.out.print("Seleccione una opción: ");

        int opcionSubMenu = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después de nextInt()

        switch (opcionSubMenu) {
            case 1:
                // Programar día de la cita
                System.out.print("Ingrese el día de la cita: ");
                String nuevoDia = scanner.nextLine();
                usuario.setCita(nuevoDia);
                System.out.println("Día de la cita programado exitosamente.");
                break;
            case 2:
                // Programar hora de la cita
                System.out.print("Ingrese la hora de la cita: ");
                String nuevaHora = scanner.nextLine();
                usuario.setCita(usuario.getCita() + ", " + nuevaHora);
                System.out.println("Hora de la cita programada exitosamente.");
                break;
            case 3:
                System.out.println("Volviendo al menú de citas.");
                break;
            default:
                System.out.println("Opción no válida. Inténtelo de nuevo.");
                break;
        }
    }
}

