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
                    System.out.println("\n ============= Lista de Eventos =============");
                    for (Evento evento : eventos.values()) {
                        System.out.println("ID: " + evento.getId() + " | " + evento.getTitulo() + " | " + evento.getFecha());
                    }   
                    System.out.println("Ingrese el ID del evento del que desea eliminar la galería: ");
                    int idEventoEliminarGaleria = scanner.nextInt();
                    scanner.nextLine();

                    if (!eventos.containsKey(idEventoEliminarGaleria)) {
                        System.out.println("Error: El evento no existe.");
                        break;
                    }

                    ArrayList<Galeria> galeriasEvento = eventos.get(idEventoEliminarGaleria).getGalerias();
                    if (galeriasEvento.isEmpty()) {
                        System.out.println("No hay galerías registradas para este evento.");
                        break;
                    }

                    System.out.println("\n ============= Galerías del Evento =============");
                    for (Galeria galeria : galeriasEvento) {
                        System.out.println("ID: " + galeria.getId() + " | " + galeria.getTitulo());
                    }
                    System.out.println("Ingrese el ID de la galería a eliminar: ");
                    int idEliminarGaleria = scanner.nextInt();
                    scanner.nextLine();

                    Galeria galeriaAEliminar = null;
                    for (Galeria galeria : galeriasEvento) {
                        if (galeria.getId() == idEliminarGaleria) {
                            galeriaAEliminar = galeria;
                            break;
                        }
                    }

                    if (galeriaAEliminar == null) {
                        System.out.println("Error: La galería no existe.");
                    } else {
                        galeriasEvento.remove(galeriaAEliminar);
                        System.out.println("Galería eliminada correctamente.");
                    }
                    break;

                // Opción 7: Añadir a Favoritos
                case 7: 
                    if(eventos.isEmpty() || usuarios.isEmpty()) {
                        System.out.println("No hay eventos o usuarios registrados.");
                        break;
                    }
                    System.out.print("Ingrese el email del usuario: ");
                    String emailFav = scanner.nextLine();
                    if (!usuarios.containsKey(emailFav)) {
                        System.out.println("Error: El usuario no existe.");
                        break;
                    }

                    System.out.println("\n ============= Lista de Eventos =============");
                    for (Evento evento : eventos.values()) {
                        System.out.println("ID: " + evento.getId() + " | " + evento.getTitulo() + " | " + evento.getFecha());
                    }
                    System.out.print("Ingrese el ID del evento a añadir a favoritos: ");
                    int idFav = scanner.nextInt();
                    scanner.nextLine();

                    if (!eventos.containsKey(idFav)) {
                        System.out.println("Error: El evento no existe.");
                        break;
                    }

                    Favorito nuevoFavorito = new Favorito(emailFav, idFav);
                    favoritos.add(nuevoFavorito);
                    System.out.println("Evento añadido a favoritos correctamente.");
                    break;
                
                // Opción 8: Eliminar de Favoritos
                case 8:
                    if (favoritos.isEmpty()) {
                        System.out.println("No hay eventos en favoritos.");
                        break;
                    }
                    System.out.print("Ingrese el email del usuario: ");
                    String emailEliminarFav = scanner.nextLine();
                    if (!usuarios.containsKey(emailEliminarFav)) {
                        System.out.println("Error: El usuario no existe.");
                        break;
                    }
                    System.out.print("Ingrese el ID del evento a eliminar de favoritos: ");
                    int idEliminarFav = scanner.nextInt();
                    scanner.nextLine();

                    Favorito favoritoAEliminar = null;
                    for (Favorito favorito : favoritos) {
                        if (favorito.getCorreoUsuario().equals(emailEliminarFav) && favorito.getIdEvento() == idEliminarFav) {
                            favoritoAEliminar = favorito;
                            break;
                        }
                    }

                    if (favoritoAEliminar == null) {
                        System.out.println("Error: El evento no está en favoritos.");
                    } else {
                        favoritos.remove(favoritoAEliminar);
                        System.out.println("Evento eliminado de favoritos correctamente.");
                    }
                    break;
                
                // Opción 9: Salir
                case 9:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Escoja entre 1 y 9.");
                    break;
            }

        } while (opcion != 9);


    }    
        
        
        
}
