package holamundo.proyecto;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class AgregarUsuario extends AppCompatActivity {
    private EditText txtCedula, txtNombre, txtApellido;
    private Spinner cmbMetodoPago;
    private String opc[];
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_usuario);

        txtCedula= findViewById(R.id.txtCedula);
        txtNombre = findViewById(R.id.txtNombre);
        txtApellido= findViewById(R.id.txtApellido);
        cmbMetodoPago=findViewById(R.id.cmbMetodoPago);

        opc = getResources().getStringArray(R.array.metodoPago);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,opc);
        cmbMetodoPago.setAdapter(adapter);
    }

    public void guardar(View v){
        String cedula, nombre, apellido, id, metodoPago;;
        //foto = this.fotoAleatoria();

        id = Datos.getId();
        cedula = txtCedula.getText().toString();
        nombre = txtNombre.getText().toString();
        apellido = txtApellido.getText().toString();
        metodoPago = cmbMetodoPago.getSelectedItem().toString();
        Usuario u = new Usuario(id,cedula,nombre,apellido,metodoPago);
        u.guardar();
        limpiar();
        Snackbar.make(v,getResources().getString(R.string.usuario_guardado_exitoso),Snackbar.LENGTH_SHORT).show();
    }

    public void onBackPressed(){
        finish();
        Intent i = new Intent(AgregarUsuario.this,Principal.class);
        startActivity(i);
    }

    public void limpiar(){
        txtCedula.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        cmbMetodoPago.setSelection(0);
        txtCedula.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public void limpiar(View v){
        limpiar();
    }
}
