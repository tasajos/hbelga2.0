package org.chakuy.hbelga.ui.ordenadorpc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.chakuy.hbelga.R;

import java.util.HashMap;
import java.util.Map;

public class ordenadorpc extends Fragment {

    ImageButton btn_add;
    EditText equipo, area, mram, procesador, so, hdoc, hdlib,ip;
    Spinner tipoSpinner, tipoSpinnerMra;

    private FirebaseFirestore mfirestore;

    public static ordenadorpc newInstance() {
        return new ordenadorpc();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ordenadorpc, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton btnRegistrar = view.findViewById(R.id.btn_imgregistrar);

        tipoSpinner = view.findViewById(R.id.arq); //arquitectura
        tipoSpinnerMra = view.findViewById(R.id.mram); //memoria ram
        equipo = view.findViewById(R.id.equipo); //equipo
        area = view.findViewById(R.id.area); //area
        procesador = view.findViewById(R.id.procesador);//procesador
        so = view.findViewById(R.id.so); //sistema operativo
        hdoc = view.findViewById(R.id.hdoc); //hd libre
        hdlib = view.findViewById(R.id.hdlib); //hd ocupado
        ip = view.findViewById(R.id.ip); //ip ocupado

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(), R.array.tipos_arq, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoSpinner.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapters = ArrayAdapter.createFromResource(requireContext(), R.array.tipos_ram, android.R.layout.simple_spinner_item);
        adapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoSpinnerMra.setAdapter(adapters);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String requipo = equipo.getText().toString().trim();
                String rarea = area.getText().toString().trim();
                String rso = so.getText().toString().trim();
                String rprocesador = procesador.getText().toString().trim();
                String rarq = tipoSpinner.getSelectedItem().toString().trim();
                String rmram = tipoSpinnerMra.getSelectedItem().toString().trim();
                String rhdlib = hdlib.getText().toString().trim();
                String rhdoc = hdoc.getText().toString().trim();
                String rip = ip.getText().toString().trim();

                if (requipo.isEmpty() || rarea.isEmpty()|| rip.isEmpty() || rso.isEmpty() ||rprocesador.isEmpty() || rhdlib.isEmpty() || rhdoc.isEmpty() || rarq.isEmpty() || rmram.isEmpty()) {
                    Toast.makeText(requireContext(), "Ingresar todos los datos", Toast.LENGTH_SHORT).show();
                } else {
                    postHB(requipo, rarea, rprocesador, rarq, rmram, rhdlib, rhdoc,rso,rip);
                }
            }
        });
    }

    private void postHB(String requipo, String rarea, String rprocesador, String rarq, String rmram, String rhdlib, String rhdoc,String rso,String rip) {
        mfirestore = FirebaseFirestore.getInstance();
        Map<String, Object> map = new HashMap<>();
        map.put("equipo", requipo);
        map.put("area", rarea);
        map.put("so", rso);
        map.put("procesador", rprocesador);
        map.put("tipoSpinner", rarq);
        map.put("tipoSpinnerMra", rmram);
        map.put("hdlib", rhdlib);
        map.put("hdoc", rhdoc);
        map.put("rip", rip);

        mfirestore.collection("hbor").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(requireContext(), "Registro Exitoso", Toast.LENGTH_SHORT).show();
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
        equipo.setText("");
        area.setText("");
        so.setText("");
        procesador.setText("");
        tipoSpinner.setSelection(0);
        tipoSpinnerMra.setSelection(0);
        hdlib.setText("");
        hdoc.setText("");
        ip.setText("");
    }
}