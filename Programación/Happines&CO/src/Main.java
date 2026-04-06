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
                
                // Opción 1:  Añadir Usuario
                case 1: 
                     Usuario.registrarUsuario(scanner, usuarios);               
                    break;

                // Opción 2:Eliminar Usuario
                case 2: 
                    System.out.print("Ingrese el email del usuario a eliminar: ");
                    String emailEliminar = scanner.nextLine();
                    if (usuarios.containsKey(emailEliminar)) {
                        usuarios.remove(emailEliminar);
                        System.out.println("Usuario eliminado Correctamente.");
                    } else {
                        System.out.println("Error: El usuario no existe.");
                    }
                    break;
                
                // Opción 3: Añadir Evento
                case 3: 
                    System.out.print("Ingrese la fecha del evento (dd/mm/yyyy): ");
                    String fecha = scanner.nextLine();
                    System.out.print("Título del evento: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Ubicación del evento: ");
                    String ubicacion = scanner.nextLine();
                    System.out.print("Descripción para el evento: ");
                    String descripcion = scanner.nextLine();

                    contadorEventos++;
                    Evento nuevoEvento = new Evento(contadorEventos, fecha, titulo, ubicacion, descripcion);
                    eventos.put(contadorEventos, nuevoEvento);
                    System.out.println("Evento creado exitosamente con ID: " + contadorEventos);
                    break;
                

                // Opción 4: Eliminar Evento
                case 4: 
                    
                    if (eventos.isEmpty()) {
                        System.out.println("No hay eventos registrados.");
                        break;
                    } 
                    System.out.println("\n ============= Lista de Eventos =============");
                    for (Evento evento : eventos.values()) {
                        System.out.println("ID: " + evento.getId() + " | " + evento.getTitulo() + " | " + evento.getFecha());
                    }
                    System.out.print("Ingrese el ID del evento a eliminar: ");
                    int idEliminarEvento = scanner.nextInt();
                    scanner.nextLine(); 
                    if (eventos.containsKey(idEliminarEvento)) {
                        eventos.remove(idEliminarEvento);
                        System.out.println("Evento eliminado correctamente.");
                    } else {
                        System.out.println("Error: El evento no existe.");
                    }

                    // Opción 5: Añadir galería
                    case 5: 
                    if (eventos.isEmpty()) {
                        System.out.println("No hay eventos registrados.");
                        break;
                    }

                    System.out.println("\n ============= Lista de Eventos =============");
                    for (Evento evento : eventos.values()) {
                        System.out.println("ID: " + evento.getId() + " | " + evento.getTitulo() + " | " + evento.getFecha());
                    }
                    System.out.println("Ingrese el ID del evento al que desea añadir una galería: ");
                    int idEventoGaleria = scanner.nextInt();
                    scanner.nextLine(); 

                    if (eventos.containsKey(idEventoGaleria)) {
                        System.out.print("Ingrese el título de la galería: ");
                        String tituloGaleria = scanner.nextLine();
                        contadorGalerias++;
                        Galeria nuevaGaleria = new Galeria(contadorGalerias, tituloGaleria, idEventoGaleria);
                        eventos.get(idEventoGaleria).getGalerias().add(nuevaGaleria);
                        System.out.println("Galería añadida exitosamente al evento.");
                    } else {
                        System.out.println("Error: El evento no existe.");
                    }

                // Opción 6: Eliminar galería
                case 6:
                    if (eventos.isEmpty()) {
                        System.out.println("No hay eventos registrados.");
                        break;
                    }

                    
                default:
                    break;
            }




        
        } while (opcion != 9);


    }    
        
        
        
}
