package holamundo.proyecto;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EscogerPlato extends AppCompatActivity {
    private TextView txtBienvenido;
    private Spinner cmbTiposPlatos, cmbPlatos;
    private ArrayAdapter<String> adapterTipos, adapterPlatos;
    private String opcTipos[];
    private DatabaseReference databaseReference;
    private EscogerMesa em;
    private String db = "Platos";
    private Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escoger_plato);

        txtBienvenido=findViewById(R.id.txtBienvenido);
        cmbTiposPlatos=findViewById(R.id.cmbTiposPlatos);
        cmbPlatos=findViewById(R.id.cmbPlato);

        b = this.getIntent().getExtras();
        if (b!=null){
            String nombre = b.getString("nombre");
            String mensaje = "Hola " + nombre;
            txtBienvenido.setText(mensaje);
        }

        opcTipos = getResources().getStringArray(R.array.opcionesPlato);
        adapterTipos = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,opcTipos);
        cmbTiposPlatos.setAdapter(adapterTipos);

        databaseReference =  FirebaseDatabase.getInstance().getReference();


    }

    public void cargar(View v){
        databaseReference.child(db).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<String> platos = new ArrayList<>();
                String sel = cmbTiposPlatos.getSelectedItem().toString();
                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()){
                    String tipo = areaSnapshot.child("tipo").getValue(String.class);
                    if (sel.equalsIgnoreCase(tipo)){
                        String plato = areaSnapshot.child("nombre").getValue(String.class);
                        platos.add(plato);
                    }
                }
                adapterPlatos = new ArrayAdapter<>(EscogerPlato.this, android.R.layout.simple_spinner_item,platos);
                adapterPlatos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                cmbPlatos.setAdapter(adapterPlatos);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void guardar(View v){
        String id, cedula, nombre, plato, mesa;
        b = this.getIntent().getExtras();

        if (validar()){
            id = Datos.getId();
            cedula=b.getString("cedula");
            nombre=b.getString("nombre");
            plato=cmbPlatos.getSelectedItem().toString();
            mesa=b.getString("mesa");

            Pedido p = new Pedido(id,cedula,nombre,plato,mesa);
            p.guardar();
            limpiar();
            Snackbar.make(v,getResources().getString(R.string.pedido_guardado),Snackbar.LENGTH_SHORT).show();
        }
    }

    public void limpiar(){

    }

    public boolean validar(){
        int o = cmbPlatos.getSelectedItemPosition();
        if (o==-1){
            Toast.makeText(this, getResources().getString(R.string.errorPlato), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}