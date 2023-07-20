package org.chakuy.hbelga.ui.datospc;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.chakuy.hbelga.R;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class datosPc extends Fragment {

    private DatosPcViewModel mViewModel;
    private WebView webView; // Agrega la declaración de la variable webView

    public static datosPc newInstance() {
        return new datosPc();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_datos_pc, container, false);
        webView = view.findViewById(R.id.webView); // Inicializar la variable webView con la vista inflada
        return view; // Retorna la vista inflada

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Habilitar JavaScript para la hoja de Google Sheets

        // Configurar el cliente del WebView
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());

        // URL de la hoja de Google Sheets (asegúrate de tener acceso público a la hoja)
        String sheetUrl = "https://docs.google.com/spreadsheets/d/1I6tg2MoYo6uKboE42owJVkIiX95qN_hb1hKIuPyshFk/edit?usp=sharing";

        // Cargar la hoja de Google Sheets en el WebView
        webView.loadUrl(sheetUrl);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DatosPcViewModel.class);
        // TODO: Use the ViewModel
    }
}