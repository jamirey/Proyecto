package holamundo.proyecto;

public class Plato {
    private String id;
    private String nombre;
    private String tipo;
    private int precio;
    private String foto;

    public Plato() {
    }

    public Plato(String id, String nombre, String tipo, int precio, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.foto = foto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void guardar(){
        Datos.agregarPlato(this);
    }

    public void eliminar(){
        Datos.eliminarPlato(this);
    }

    public void editar(){
        Datos.editarPlato(this);
    }
}
