import java.util.ArrayList;

public class Evento {
    
    // Atributos
    private int id;
    private String fecha;
    private String titulo;
    private String ubicacion;
    private String descripcion;
    private ArrayList<Galeria> galerias;

    //Constructor para crear un nuevo evento
    public Evento(int id, String fecha, String titulo, String ubicacion, String descripcion) {
        this.id = id;
        this.fecha = fecha;
        this.titulo = titulo;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.galerias = new ArrayList<>();
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public ArrayList<Galeria> getGalerias() {
        return galerias;
    }

    //setters
    public void setId(int id) { 
        this.id = id;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public void setGalerias(ArrayList<Galeria> galerias) {
        this.galerias = galerias;
    }

    //toString
    @Override
    public String toString() {
        return "Evento: " + titulo + ", Fecha: " + fecha + ", Ubicación: " + ubicacion + ", Descripción: " + descripcion;
    }
    
}