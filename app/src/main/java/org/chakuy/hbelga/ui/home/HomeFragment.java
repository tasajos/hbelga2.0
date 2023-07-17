package org.chakuy.hbelga.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;


import com.google.firebase.auth.FirebaseAuth;

import org.chakuy.hbelga.R;
import org.chakuy.hbelga.login;

public class HomeFragment extends Fragment {

    private LinearLayout linearLayoutAgregar;
    private LinearLayout listado;
    private LinearLayout descargar;
    private LinearLayout cerrarsesion;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        linearLayoutAgregar = root.findViewById(R.id.linearLayoutAgregar);
        listado = root.findViewById(R.id.listado);
        descargar = root.findViewById(R.id.descargar);
        cerrarsesion = root.findViewById(R.id.cerrarsesion);

        setupCardClickListeners();

        return root;
    }

    private void setupCardClickListeners() {
        linearLayoutAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navegar a GalleryFragment
                Navigation.findNavController(view).navigate(R.id.nav_gallery);
            }
        });

        listado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Acción para la tarjeta "Listado"
                // Aquí puedes implementar el comportamiento deseado
                Navigation.findNavController(view).navigate(R.id.nav_lista);
                Toast.makeText(getActivity(), "Cargando Listado.....", Toast.LENGTH_SHORT).show();
            }
        });

        descargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Acción para la tarjeta "Descargar"
                // Aquí puedes implementar el comportamiento deseado
                Toast.makeText(getActivity(), "Haz clic en Descargar", Toast.LENGTH_SHORT).show();
            }
        });

        cerrarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Acción para la tarjeta "Cerrar Sesión"
                logout();
            }
        });
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(getActivity(), "Cierre de sesión exitoso", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getActivity(), login.class));
        getActivity().finish();
    }
}