import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    // Colecciones para almacenar usuarios, eventos y favoritos
    static HashMap<String, Usuario> usuarios = new HashMap<>();
    static HashMap<Integer, Evento> eventos = new HashMap<>();
    static ArrayList<Favorito> favoritos = new ArrayList<>();

    // Contadores para generar IDs únicos
    static int contadorEventos = 0;
    static int contadorGalerias = 0;


    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {

            // Mostrar el menú principal
            System.out.println("\n============ Menú Principal ===========");
            System.out.println("1. Añadir Usuario:");
            System.out.println("2. Eliminar Usuario:");
            System.out.println("3. Añadir Evento:");
            System.out.println("4. Eliminar Evento:");
            System.out.println("5. Añadir galería: ");
            System.out.println("6. Eliminar galería: ");
            System.out.println("7. Añadir a Favoritos:");
            System.out.println("8. Eliminar de Favoritos:");
            System.out.println("9. Salir:");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1: // Añadir Usuario
                    System.out.print("Ingrese el nombre del usuario: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese el email: ");
                    String email = scanner.nextLine();
                    System.out.print("Ingrese la contraseña: ");
                    String password = scanner.nextLine();

                    if (usuarios.containsKey(email)) {
                        System.out.println("Error: El usuario ya existe.");
                    } else {
                        Usuario nuevoUsuario = new Usuario(nombre, email, password);
                        usuarios.put(email, nuevoUsuario);
                        System.out.println("Usuario creado exitosamente.");
                    }                    
                    break;

                
            
                default:
                    break;
            }




        
        } while (opcion != 9);


    }    
        
        
        
}
