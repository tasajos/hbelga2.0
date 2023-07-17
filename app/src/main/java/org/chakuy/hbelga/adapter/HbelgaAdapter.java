package org.chakuy.hbelga.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.chakuy.hbelga.R;
import org.chakuy.hbelga.model.HbelgaModel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class HbelgaAdapter extends FirestoreRecyclerAdapter<HbelgaModel,HbelgaAdapter.ViewHolder> {


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public HbelgaAdapter(@NonNull FirestoreRecyclerOptions<HbelgaModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int position, @NonNull HbelgaModel HbelgaModel) {
        viewHolder.name.setText(HbelgaModel.getNombre());
        viewHolder.description.setText(HbelgaModel.getdescripcion());
        viewHolder.state.setText(HbelgaModel.getestado());
        viewHolder.dateb.setText(HbelgaModel.getfecha());
        viewHolder.areab.setText(HbelgaModel.getarea());
     }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewhb_single, parent,false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, description, state, dateb, areab;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nombre);
            description = itemView.findViewById(R.id.descripcionc);
            state = itemView.findViewById(R.id.estadoc);
            dateb = itemView.findViewById(R.id.fecha);
            areab = itemView.findViewById(R.id.areabc);



        }
    }
}
