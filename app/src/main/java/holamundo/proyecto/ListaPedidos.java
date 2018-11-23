package holamundo.proyecto;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListaPedidos extends AppCompatActivity  implements AdaptadorPedido.OnPedidoClickListener{
    private RecyclerView lstPedidos;
    private ArrayList<Pedido> pedidos;
    private LinearLayoutManager llm;
    private DatabaseReference databaseReference;
    private String db = "Pedidos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedidos);

        lstPedidos = findViewById(R.id.lstPedidos);
        pedidos=new ArrayList<>();

        final AdaptadorPedido adaptadorPedido = new AdaptadorPedido(pedidos,this);

        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        lstPedidos.setLayoutManager(llm);
        lstPedidos.setAdapter(adaptadorPedido);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child(db).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pedidos.clear();
                if (dataSnapshot.exists()){
                    for (DataSnapshot snapshot :dataSnapshot.getChildren()){
                        Pedido p = snapshot.getValue(Pedido.class);
                        pedidos.add(p);
                    }
                }
                adaptadorPedido.notifyDataSetChanged();
                Datos.setPedidos(pedidos);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onPedidoClick(Pedido p) {
        String nombre = getResources().getString(R.string.mensaje1) + p.getNombreUsuario();
        String ubicacion = getResources().getString(R.string.mensaje2) + p.getMesa();
        String plato = getResources().getString(R.string.mensaje3)+ p.getPlato();
        String mensaje = nombre + "\n" + ubicacion + "\n" + plato;
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle(R.string.infoVenta);
        b.setMessage(mensaje);
        b.setNeutralButton(R.string.realizado,null);
        Dialog d = b.create();
        d.show();
    }
}
