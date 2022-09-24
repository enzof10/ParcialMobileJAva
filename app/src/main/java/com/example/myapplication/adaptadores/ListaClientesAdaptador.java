package com.example.myapplication.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.VerActivity;
import com.example.myapplication.entidadades.Clientes;

import java.util.ArrayList;

public class ListaClientesAdaptador  extends RecyclerView.Adapter<ListaClientesAdaptador.ClienteViewHolder> {

    ArrayList<Clientes> clientesArrayList;

    public  ListaClientesAdaptador(ArrayList<Clientes> clientesArrayList){
        this.clientesArrayList = clientesArrayList;
    }

    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_cliente, null, false);

        return new ClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder holder, int position) {
        holder.viewNombre.setText(clientesArrayList.get(position).getNombre());
        holder.viewApellido.setText(clientesArrayList.get(position).getApellido());
        holder.viewDireccion.setText(clientesArrayList.get(position).getDireccion());
        holder.viewEmail.setText(clientesArrayList.get(position).getEmail());
        holder.viewDni.setText(clientesArrayList.get(position).getDni());
        holder.viewTelefono.setText(clientesArrayList.get(position).getTelefono());
    }

    @Override
    public int getItemCount() {
        return  clientesArrayList.size();
    }

    public  class  ClienteViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombre, viewApellido, viewDireccion, viewEmail, viewDni, viewTelefono;
        public ClienteViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.textViewNombre);
            viewApellido = itemView.findViewById(R.id.textViewApellido);
            viewDireccion = itemView.findViewById(R.id.textViewDireccion);
            viewEmail = itemView.findViewById(R.id.textViewEmail);
            viewDni = itemView.findViewById(R.id.textViewDni);
            viewTelefono = itemView.findViewById(R.id.textViewTelefono);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID", clientesArrayList.get(getAdapterPosition()).getId());
                    context.startActivity(intent);

                }
            });
        }
    }
}
