package holamundo.proyecto;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Datos {
    private static String db_platos = "Platos";
    private static String db_usuarios = "Usuarios";
    private static String db_pedidos = "Pedidos";

    private static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public static ArrayList<Plato> platos = new ArrayList<>();
    public static ArrayList<Usuario> usuarios = new ArrayList<>();
    public static ArrayList<Pedido> pedidos = new ArrayList<>();

    public static void agregarPlato(Plato p){
        databaseReference.child(db_platos).child(p.getId()).setValue(p);
    }

    public static void eliminarPlato(Plato p){
        databaseReference.child(db_platos).child(p.getId()).removeValue();
    }

    public static void editarPlato(Plato p){
        agregarPlato(p);
    }

    public static void agregarUsuario(Usuario u){
        databaseReference.child(db_usuarios).child(u.getId()).setValue(u);
    }

    public static void eliminarUsuario(Usuario u){
        databaseReference.child(db_usuarios).child(u.getId()).removeValue();
    }

    public static void editarUsuario(Usuario u){
        agregarUsuario(u);
    }

    public static void agregarPedido(Pedido p){
        databaseReference.child(db_pedidos).child(p.getId()).setValue(p);
    }

    public static void eliminarPedido(Pedido p){
        databaseReference.child(db_pedidos).child(p.getId()).removeValue();
    }

    public static void editarPedido(Pedido p){
        agregarPedido(p);
    }


    public static String getId(){
        return databaseReference.push().getKey();
    }

    public static void setPlatos(ArrayList<Plato> platos){
        Datos.platos = platos;
    }

    public static void setUsuarios(ArrayList<Usuario> usuarios){
        Datos.usuarios = usuarios;
    }

    public static void setPedidos(ArrayList<Pedido> pedidos) {
        Datos.pedidos = pedidos;
    }

    public static ArrayList<Plato> obtenerPlatos(){
        return platos;
    }

    public static ArrayList<Usuario> obtenerUsuarios(){
        return usuarios;
    }

    public static ArrayList<Pedido> obtenerPedidos(){
        return pedidos;
    }
}
