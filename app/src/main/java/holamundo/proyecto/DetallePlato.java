package holamundo.proyecto;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class DetallePlato extends AppCompatActivity {
    private TextView txtNombre, txtTipo, txtPrecio;
    private Bundle bundle;
    private Intent i;
    private ImageView foto;
    private String id, nombre, tipo, precio, foto2;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_plato);
        txtNombre=findViewById(R.id.txtNombrePlato);
        txtTipo=findViewById(R.id.txtTipo);
        txtPrecio=findViewById(R.id.txtPrecio);
        foto=findViewById(R.id.fotoPlato);

        storageReference = FirebaseStorage.getInstance().getReference();
        i = getIntent();
        bundle = i.getBundleExtra("datos");

        id=bundle.getString("id");
        nombre=bundle.getString("nombre");
        tipo=bundle.getString("tipo");
        precio=bundle.getString("precio");
        foto2=bundle.getString("foto");

        txtNombre.setText(nombre);
        txtTipo.setText(tipo);
        txtPrecio.setText(precio);

        storageReference.child(foto2).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(foto);
            }
        });
    }
}
