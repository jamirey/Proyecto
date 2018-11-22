package holamundo.proyecto;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorPlato extends RecyclerView.Adapter<AdaptadorPlato.PlatoViewHolder> {
    private ArrayList<Plato> platos;
    private OnPlatoClickListener clickListener;

    public AdaptadorPlato(ArrayList<Plato> platos, OnPlatoClickListener clickListener) {
        this.platos = platos;
        this.clickListener = clickListener;
    }

    @Override
    public PlatoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plato,parent,false);
        return new PlatoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final PlatoViewHolder holder, int position) {
        final Plato p = platos.get(position);
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        storageReference.child(p.getFoto()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(holder.foto);
            }
        });
        holder.nombre.setText(p.getNombre());
        holder.tipo.setText(p.getTipo());
        holder.precio.setText(Integer.toString(p.getPrecio()));

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onPlatoClick(p);
            }
        });
    }


    @Override
    public int getItemCount() {
        return platos.size();
    }

    public static class PlatoViewHolder extends RecyclerView.ViewHolder {
        private ImageView foto;
        private TextView nombre;
        private TextView tipo;
        private TextView precio;
        private View v;

        public PlatoViewHolder(View itemView) {
            super(itemView);
            v = itemView;
            foto=v.findViewById(R.id.fotoI);
            nombre=v.findViewById(R.id.lblNombre);
            tipo=v.findViewById(R.id.lblTipo);
            precio=v.findViewById(R.id.lblPrecio);
        }
    }
    
    public interface OnPlatoClickListener{
        void onPlatoClick(Plato p);
    }
}
