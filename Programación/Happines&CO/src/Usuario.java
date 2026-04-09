import java.util.Map;
import java.util.Scanner;

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

    // Metodo para crear un nuevo usuario
    public static void registrarUsuario(Scanner scanner, Map<String, Usuario> usuarios) {
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
    }

    // Metodo para eliminar un usuario
    public static void eliminarUsuario(Scanner scanner, Map<String, Usuario> usuarios) {
        System.out.print("Ingrese el email del usuario a eliminar: ");
        String emailEliminar = scanner.nextLine();
        if (usuarios.containsKey(emailEliminar)) {
            usuarios.remove(emailEliminar);
            System.out.println("Usuario eliminado Correctamente.");
        } else {
            System.out.println("Error: El usuario no existe.");
        }

    }   
}