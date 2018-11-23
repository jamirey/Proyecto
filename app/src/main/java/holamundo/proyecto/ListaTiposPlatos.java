package holamundo.proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListaTiposPlatos extends AppCompatActivity {
    private ListView lv;
    private String[] opc;
    private Intent in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tipos_platos);

        lv= findViewById(R.id.lstTiposPlatos);

        opc= getResources().getStringArray(R.array.opcionesPlatoLV);
        ArrayAdapter<String> adapter =new ArrayAdapter(this, android.R.layout.simple_list_item_1,opc);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        String principal = opc[i];
                        in = new Intent( ListaTiposPlatos.this, ListaPlatos.class);
                        in.putExtra("opcion",principal);
                        startActivity(in);
                        break;
                    case 1:
                        String guarnicion = opc[i];
                        in = new Intent( ListaTiposPlatos.this, ListaPlatos.class);
                        in.putExtra("opcion",guarnicion);
                        startActivity(in);
                        break;
                    case 2:
                        String bebida=opc[i];
                        in = new Intent( ListaTiposPlatos.this, ListaPlatos.class);
                        in.putExtra("opcion",bebida);
                        startActivity(in);
                        break;
                    case 3:
                        String postre=opc[i];
                        in.putExtra("opcion",postre);
                        in = new Intent( ListaTiposPlatos.this, ListaPlatos.class);
                        startActivity(in);
                        break;
                }
            }
        });
    }
}
