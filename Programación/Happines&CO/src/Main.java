import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
                    Usuario.eliminarUsuario(scanner, usuarios);         
                    break;
                
                // Opción 3: Añadir Evento
                case 3: 
                    añadirEvento(scanner, eventos);
                    break;
                

                // Opción 4: Eliminar Evento
                case 4: 
                    eliminarEvento(eventos, contadorEventos, scanner);
                    break;

                // Opción 5: Añadir galería
                case 5: 
                    añadirGaleriaEvento(scanner, eventos);
                    break;

                // Opción 6: Eliminar galería
                case 6:
                    eliminarGaleriaEvento(scanner, eventos);
                    break;

                // Opción 7: Añadir a Favoritos
                case 7: 
                    añadirEventoAFavoritos(scanner, usuarios, eventos, favoritos);
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
    
    
    // Metodo para añadir un Evento
    public static void añadirEvento(Scanner scanner, HashMap<Integer, Evento> eventos) {
        contadorEventos++;
        Evento nuevoEvento = Evento.crearEvento(scanner, contadorEventos);
        eventos.put(contadorEventos, nuevoEvento);
        System.out.println("Evento creado exitosamente con ID: " + contadorEventos);
    }
    
    // Método para eliminar un Evento
    public static void eliminarEvento(HashMap<Integer, Evento> eventos, int id, Scanner scanner) {
        if (eventos.isEmpty()) {
            System.out.println("No hay eventos registrados.");
            return;
        }
        Evento.mostrarEventos(new ArrayList<>(eventos.values()));

        System.out.print("Ingrese el ID del evento a eliminar: "); 
        int idEliminarEvento = scanner.nextInt(); 
        scanner.nextLine();

        if (eventos.containsKey(idEliminarEvento)) {
            eventos.remove(idEliminarEvento);
            System.out.println("Evento eliminado correctamente.");
            } else {
                System.out.println("Error: El evento no existe.");
            }
    }


    // Método para añadir una galería a un evento
    public static void añadirGaleriaEvento (Scanner scanner, Map<Integer, Evento> eventos) {
            if (eventos.isEmpty()) {
                System.out.println("No hay eventos registrados.");
                return;
            }
    
            Evento.mostrarEventos(new ArrayList<>(eventos.values()));

            System.out.print("Ingrese el ID del evento al que desea añadir una galería: ");
            int idEventoGaleria = scanner.nextInt();
            scanner.nextLine();

            if (eventos.containsKey(idEventoGaleria)) {
                contadorGalerias++;
                Galeria nuevaGaleria = Galeria.añadirGaleria(scanner, contadorGalerias, idEventoGaleria);
                eventos.get(idEventoGaleria).getGalerias().add(nuevaGaleria);
                System.out.println("Galería añadida exitosamente al evento.");
            } else {
                System.out.println("Error: El evento no existe.");
            }

    }

    // Metodo para eliminar una galería de un evento
    public static void eliminarGaleriaEvento(Scanner scanner, Map<Integer, Evento> eventos) {
        if (eventos.isEmpty()) {
            System.out.println("No hay eventos registrados.");
            return;
        }
        Evento.mostrarEventos(new ArrayList<>(eventos.values()));

        System.out.print("Ingrese el ID del evento del que desea eliminar una galería: ");
        int idEventoEliminarGaleria = scanner.nextInt();
        scanner.nextLine();

        if (!eventos.containsKey(idEventoEliminarGaleria)) {
            System.out.println("Error: El evento no existe.");
            return;
        }

        Evento eventoSeleccionado = eventos.get(idEventoEliminarGaleria);
        Galeria.listarGaleriasEvento(eventoSeleccionado);

        if (eventoSeleccionado.getGalerias().isEmpty()) {
            System.out.println("No hay galerías registradas para este evento.");
            return;
        }

        System.out.print("Ingrese el ID de la galería a eliminar: ");
        int idEliminarGaleria = scanner.nextInt();
        scanner.nextLine();

        boolean galeriaEliminada = eventoSeleccionado.getGalerias().removeIf(galeria -> galeria.getId() == idEliminarGaleria);

        if (galeriaEliminada) {
            System.out.println("Galería eliminada correctamente.");
        } else {
            System.out.println("Error: La galería no existe en este evento.");
        }

    }    

    //Metodo para añadir a favoritos
    public static void añadirEventoAFavoritos(Scanner scanner, Map<String, Usuario> usuarios, Map<Integer, Evento> eventos, ArrayList<Favorito> favoritos) {
        if(eventos.isEmpty() || usuarios.isEmpty()) {
            System.out.println("No hay eventos o usuarios registrados.");
            return;
        }

        System.out.print("Ingrese el email del usuario: ");
        String emailFav = scanner.nextLine();
        if (!usuarios.containsKey(emailFav)) {
            System.out.println("Error: El usuario no existe.");
            return;
        }

        Evento.mostrarEventos(new ArrayList<>(eventos.values()));

        System.out.print("Ingrese el ID del evento a añadir a favoritos: ");
        int idFav = scanner.nextInt();
        scanner.nextLine();

        if (!eventos.containsKey(idFav)) {
            System.out.println("Error: El evento no existe.");
            return;
        }

        Favorito nuevoFavorito = Favorito.crearFavorito(emailFav, idFav);
        favoritos.add(nuevoFavorito);
        System.out.println("Evento añadido a favoritos correctamente.");

    }
}
