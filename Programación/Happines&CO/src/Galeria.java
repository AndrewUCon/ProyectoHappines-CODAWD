import java.util.ArrayList;
import java.util.Scanner;

public class Galeria {

    //Atributos
    private int id;
    private String titulo;
    private int idEvento;

    // Constructor
    public Galeria(int id, String titulo, int idEvento) {
        this.id = id;
        this.titulo = titulo;
        this.idEvento = idEvento;
    }

    // Getters
    public int getId() {
        return id;  
    }

    public String getTitulo() {
        return titulo;
    }

    public int getIdEvento() {
        return idEvento;
    }

    // Setters
    public void setId(int id) {
        this.id = id;  
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    // toString 
    @Override
    public String toString() {
        return "Galeria: " + titulo + " (ID: " + id + ", Evento ID: " + idEvento + ")";
    }
    

    // Método para añadir galeria
    public static Galeria añadirGaleria (Scanner scanner, int idGaleria, int idEvento) {
        System.out.print("Ingrese el título de la galería: ");
        String titulo = scanner.nextLine();
        return new Galeria(idGaleria, titulo, idEvento);
    }

    // Método para listar galerias de un evento
    public static void listarGaleriasEvento(Evento evento) {
        ArrayList <Galeria> galerias = evento.getGalerias();
         if (galerias.isEmpty()) {
             System.out.println("No hay galerías disponibles para este evento.");
         } else { 
             System.out.println("\n-------- Galerías del evento: " + evento.getTitulo() + " --------");
             for (Galeria galeria : galerias) {
                 System.out.println("ID: " + galeria.getId() + " | " + galeria.getTitulo());
             }
         }
    }

    
}
