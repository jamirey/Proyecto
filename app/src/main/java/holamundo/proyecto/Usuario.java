package holamundo.proyecto;

public class Usuario {
    private String id;
    private String cedula;
    private String nombre;
    private String apellido;
    private String metodoPago;

    public Usuario(String id, String cedula, String nombre, String apellido, String metodoPago) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.metodoPago = metodoPago;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public void guardar(){
        Datos.agregarUsuario(this);
    }

    public void eliminar(){Datos.eliminarUsuario(this);}

    public void editar(){Datos.editarUsuario(this);}
}
