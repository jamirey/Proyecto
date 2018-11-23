package holamundo.proyecto;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class AdaptadorPedido extends RecyclerView.Adapter<AdaptadorPedido.PedidoViewHolder>{
    private ArrayList<Pedido> pedido;
    private OnPedidoClickListener clickListener;

    public AdaptadorPedido(ArrayList<Pedido> pedido, OnPedidoClickListener clickListener) {
        this.pedido = pedido;
        this.clickListener = clickListener;
    }

    @Override
    public PedidoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pedido, parent,false);
        return new PedidoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final PedidoViewHolder holder, int position) {
        final Pedido p = pedido.get(position);
        holder.plato.setText(p.getPlato());
        holder.cedula.setText(p.getCedulaUsuario());
        holder.nombre.setText(p.getNombreUsuario());
        holder.mesa.setText(p.getMesa());

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onPedidoClick(p);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pedido.size();
    }

    public static class PedidoViewHolder extends RecyclerView.ViewHolder {
        private TextView plato, cedula, nombre, mesa;
        private View v;

        public PedidoViewHolder(View itemView) {
            super(itemView);
            v = itemView;
            plato = v.findViewById(R.id.lblPlatoI);
            cedula = v.findViewById(R.id.lblCedulaI);
            nombre = v.findViewById(R.id.lblNombreI);
            mesa = v.findViewById(R.id.lblMesaI);
        }
    }

    public interface OnPedidoClickListener{
        void onPedidoClick(Pedido p);
    }
}
