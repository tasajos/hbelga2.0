package org.chakuy.hbelga.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.chakuy.hbelga.R;
import org.chakuy.hbelga.databinding.FragmentGalleryBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GalleryFragment extends Fragment {

    ImageButton btn_add;
    EditText nombre, area, descripcion, fecha;
    Spinner tipoSpinner;

    private FirebaseFirestore mfirestore;
    private FragmentGalleryBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        tipoSpinner = root.findViewById(R.id.arq);
        nombre = root.findViewById(R.id.equipo);
        area = root.findViewById(R.id.area);
        descripcion = root.findViewById(R.id.procesador);
        fecha = root.findViewById(R.id.fecha);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(), R.array.tipos_producto, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoSpinner.setAdapter(adapter);

        btn_add = root.findViewById(R.id.btn_imgregistrar);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rnombre = nombre.getText().toString().trim();
                String rarea = area.getText().toString().trim();
                String restado = tipoSpinner.getSelectedItem().toString().trim();
                String rdescripcion = descripcion.getText().toString().trim();
                String rfecha = fecha.getText().toString().trim();

                if (rnombre.isEmpty() || rarea.isEmpty() || restado.isEmpty() || rdescripcion.isEmpty() || rfecha.isEmpty()) {
                    Toast.makeText(requireContext(), "Ingresar todos los datos", Toast.LENGTH_SHORT).show();
                } else {
                    postHB(rnombre, rarea, restado, rdescripcion, rfecha);
                }
            }
        });

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd, MMMM, yyyy");
        String formattedDate = dateFormat.format(currentDate);
        fecha.setText(formattedDate);

        return root;
    }

    private void postHB(String rnombre, String rarea, String restado, String rdescripcion, String rfecha) {
        mfirestore = FirebaseFirestore.getInstance();
        Map<String, Object> map = new HashMap<>();
        map.put("nombre", rnombre);
        map.put("area", rarea);
        map.put("estado", restado);
        map.put("descripcion", rdescripcion);
        map.put("fecha", rfecha);

        mfirestore.collection("hbdb").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(requireContext(), "Registro Existoso", Toast.LENGTH_SHORT).show();
                clearFields();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(requireContext(), "Error al Ingresar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clearFields() {
        nombre.setText("");
        area.setText("");
        descripcion.setText("");
        tipoSpinner.setSelection(0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}