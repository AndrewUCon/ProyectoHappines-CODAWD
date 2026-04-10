public class Favorito {
    
    // Atributos
    private String correoUsuario;
    private int idEvento;

    // Constructor
    public Favorito(String correoUsuario, int idEvento) {
        this.correoUsuario = correoUsuario;
        this.idEvento = idEvento;   
    }

    // Getters

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public int getIdEvento() {
        return idEvento;
    }

    // Setters
    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    // toString
    @Override
    public String toString() {
        return "Favorito: Usuario " + correoUsuario + " - Evento ID: " + idEvento;
    }

    // Método para crear un nuevo favorito
    public static Favorito crearFavorito(String correoUsuario, int idEvento) {
        return new Favorito(correoUsuario, idEvento);
    }

}
