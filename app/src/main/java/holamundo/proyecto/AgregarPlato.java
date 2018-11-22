package holamundo.proyecto;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AgregarPlato extends AppCompatActivity {
    private Intent i;
    private EditText txtNombrePlato, txtPrecioPlato;
    private Spinner cmbTipos;
    private ImageView foto;
    private ArrayAdapter<String> adapter;
    private String opc[];
    private Uri uri;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_plato);

        foto=findViewById(R.id.fotoPlato);
        txtNombrePlato=findViewById(R.id.txtNombrePlato);
        txtPrecioPlato=findViewById(R.id.txtPrecio);
        cmbTipos=findViewById(R.id.cmbTipos);

        opc= getResources().getStringArray(R.array.opcionesPlato);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,opc);
        cmbTipos.setAdapter(adapter);

        storageReference = FirebaseStorage.getInstance().getReference();
    }

    public void guardar(View v){
        String foto, nombre, tipo, id;
        int precio;

        id = Datos.getId();
        foto=id+".jpg";
        nombre=txtNombrePlato.getText().toString();
        tipo=cmbTipos.getSelectedItem().toString();
        precio=Integer.parseInt(txtPrecioPlato.getText().toString());

        Plato p = new Plato(id,nombre,tipo,precio,foto);
        p.guardar();
        subirFoto(foto);
        limpiar();
        Snackbar.make(v,getResources().getString(R.string.platoAgregado),Snackbar.LENGTH_SHORT)
                .show();
    }

    private void subirFoto(String foto){
        StorageReference child = storageReference.child(foto);
        UploadTask uploadTask = child.putFile(uri);
    }

    public void limpiar(){
        foto.setImageResource(android.R.drawable.ic_menu_gallery);
        txtNombrePlato.setText("");
        cmbTipos.setSelection(0);
        txtPrecioPlato.setText("");
        txtNombrePlato.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public void limpiar(View v){
        limpiar();
    }

    public void seleccionarFoto(View v){
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i,
                getResources().getString(R.string.seleccionarFoto)),1);
    }

    protected void onActivityResult(int requesCode,int resultCode,Intent data){
        super.onActivityResult(requesCode,resultCode,data);

        if(requesCode==1){
            uri = data.getData();

            if(uri != null){
                foto.setImageURI(uri);

            }
        }
    }
}
