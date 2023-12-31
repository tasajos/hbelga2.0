package org.chakuy.hbelga;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.chakuy.hbelga.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        mAuth = FirebaseAuth.getInstance(); // Inicializar FirebaseAuth

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_ordenador)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // Configurar el evento de clic en los elementos del menú
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Manejar eventos de clic en los elementos del menú
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    navController.navigate(R.id.nav_home);
                } else if (id == R.id.nav_gallery) {
                    navController.navigate(R.id.nav_gallery);
                } else if (id == R.id.nav_slideshow) {
                    navController.navigate(R.id.nav_slideshow);
                } else if (id == R.id.nav_logout) {
                    logout();
                } else if (id == R.id.nav_lista) {
                    navController.navigate(R.id.nav_lista);
                } else if (id == R.id.nav_ordenador) {
                    navController.navigate(R.id.nav_ordenador);
                }

                drawer.closeDrawers();
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(MainActivity.this, login.class));
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem linkedInItem = menu.findItem(R.id.action_settings);
        linkedInItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                openLinkedInProfile();
                return true;
            }
        });

        return true;
    }

    private void openLinkedInProfile() {
        Uri linkedInUri = Uri.parse("https://www.linkedin.com/in/carlosazcarraga/");
        Intent intent = new Intent(Intent.ACTION_VIEW, linkedInUri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Agrega esta línea para abrir en un navegador externo
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void logout() {
        mAuth.signOut(); // Cerrar sesión
        Toast.makeText(MainActivity.this, "Cierre de sesión exitoso", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this, login.class)); // Redirigir a la pantalla de inicio de sesión
        finish();
    }
}