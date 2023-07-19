package org.chakuy.hbelga.ui.ordenadorpc;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import org.chakuy.hbelga.R;

public class ordenadorpc extends Fragment {

    private OrdenadorpcViewModel mViewModel;

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
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí puedes agregar el código para manejar el evento de clic en el botón
                Toast.makeText(getContext(), "Botón Añadir PC clickeado", Toast.LENGTH_SHORT).show();
                // Agrega el código adicional que deseas ejecutar al hacer clic en el botón
                // Por ejemplo, guardar los datos del formulario en la base de datos
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(OrdenadorpcViewModel.class);
        // TODO: Use the ViewModel
    }
}