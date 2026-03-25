public class Usuario {

    // Atributos
    private String nombre;
    private String email;
    private String password;

    // Constructor para crear un nuevo usuario
    public Usuario(String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = password; 
    }
    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    // ToString para mostrar la información del usuario
    @Override
    public String toString() {
        return "Usuario: " + nombre + ", Email: " + email;
    }
}