package holamundo.proyecto;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListaPlatos extends AppCompatActivity implements AdaptadorPlato.OnPlatoClickListener {
    private TextView opcion;
    private RecyclerView lstPlatos;
    private ArrayList<Plato> platos;
    private LinearLayoutManager llm;
    private DatabaseReference databaseReference;
    private String db = "Platos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_platos);

        opcion = findViewById(R.id.txtOpcion);

        Bundle b = this.getIntent().getExtras();
        if (b!=null){
            String opcionEsc = b.getString("opcion");
            String mensaje = getResources().getString(R.string.listaEsc) + opcionEsc;
            opcion.setText(mensaje);
        }

        lstPlatos = findViewById(R.id.lstPlatos);
        platos = new ArrayList<>();

        final AdaptadorPlato adaptadorPlato = new AdaptadorPlato(platos,this);

        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        lstPlatos.setLayoutManager(llm);
        lstPlatos.setAdapter(adaptadorPlato);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child(db).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                platos.clear();
                if (dataSnapshot.exists()){
                    for (DataSnapshot snapshot :dataSnapshot.getChildren()){
                        Plato p = snapshot.getValue(Plato.class);
                        platos.add(p);
                    }
                }
                adaptadorPlato.notifyDataSetChanged();
                Datos.setPlatos(platos);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onPlatoClick(Plato p) {
        Intent i = new Intent(ListaPlatos.this,DetallePlato.class);
        Bundle b = new Bundle();
        b.putString("id",p.getId());
        b.putString("nombre",p.getNombre());
        b.putString("tipo",p.getTipo());
        b.putString("precio",Integer.toString(p.getPrecio()));
        b.putString("foto",p.getFoto());
        i.putExtra("datos",b);
        startActivity(i);
        finish();
    }
}
