package holamundo.proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EscogerMesa extends AppCompatActivity {
    private Spinner cmbMesa;
    private EditText txtCedula;
    private TextView txtNombre;
    private ArrayAdapter<String> adapter;
    private String opc[];
    private String db = "Usuarios";
    private DatabaseReference databaseReference;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escoger_mesa);

        cmbMesa=findViewById(R.id.cmbNumMesa);
        txtCedula=findViewById(R.id.txtCedulaBuscar);
        txtNombre=findViewById(R.id.txtNombreUsuario);

        opc = getResources().getStringArray(R.array.mesas);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,opc);
        cmbMesa.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void buscar_usuario(View v){
        databaseReference.child(db).addValueEventListener(new ValueEventListener() {
            String cedula = txtCedula.getText().toString();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()){
                    String cedulaUsuario = areaSnapshot.child("cedula").getValue(String.class);
                    if (cedula.equalsIgnoreCase(cedulaUsuario)){
                        String nombreUsuario = areaSnapshot.child("nombre").getValue(String.class);
                        txtNombre.setText(nombreUsuario);
                    }
                }
                validarBuscar();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void siguiente(View v){
        String nombre = txtNombre.getText().toString();
        String mesa = cmbMesa.getSelectedItem().toString();
        String cedula = txtCedula.getText().toString();

        if (validar()){
            i = new Intent(EscogerMesa.this,EscogerPlato.class);
            i.putExtra("nombre", nombre);
            i.putExtra("cedula",cedula);
            i.putExtra("mesa",mesa);

            startActivity(i);
        }
    }

    public boolean validar(){
        int o = cmbMesa.getSelectedItemPosition();
        if (o==0){
            Toast.makeText(this, getResources().getString(R.string.errorMetodo), Toast.LENGTH_SHORT).show();
            return false;
        }
        if (txtNombre.getText().toString().isEmpty()){
            Toast.makeText(this, getResources().getString(R.string.errorUsuario), Toast.LENGTH_SHORT).show();
            txtCedula.requestFocus();
            return false;
        }
        return true;
    }

    public boolean validarBuscar(){
        if (txtCedula.getText().toString().isEmpty()){
            Toast.makeText(this, getResources().getString(R.string.errorCedula), Toast.LENGTH_SHORT).show();
            txtCedula.requestFocus();
            return false;
        }
        if (txtNombre.getText().toString().isEmpty()){
            Toast.makeText(this, getResources().getString(R.string.errorBuscar), Toast.LENGTH_SHORT).show();
            txtCedula.requestFocus();
            return false;
        }
        return true;
    }
}
