package holamundo.proyecto;

public class Pedido {
    private String id;
    private String cedulaUsuario;
    private String nombreUsuario;
    private String plato;
    private String mesa;

    public Pedido() {
    }

    public Pedido(String id, String cedulaUsuario, String nombreUsuario, String plato, String mesa) {
        this.id = id;
        this.cedulaUsuario = cedulaUsuario;
        this.nombreUsuario = nombreUsuario;
        this.plato = plato;
        this.mesa = mesa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCedulaUsuario() {
        return cedulaUsuario;
    }

    public void setCedulaUsuario(String cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPlato() {
        return plato;
    }

    public void setPlato(String plato) {
        this.plato = plato;
    }

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    public void guardar(){
        Datos.agregarPedido(this);
    }

    public void eliminar(){
        Datos.eliminarPedido(this);
    }

    public void editar(){
        Datos.editarPedido(this);
    }
}
