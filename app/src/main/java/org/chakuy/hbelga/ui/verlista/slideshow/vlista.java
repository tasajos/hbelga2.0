package org.chakuy.hbelga.ui.verlista.slideshow;

import androidx.core.content.FileProvider;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import org.chakuy.hbelga.R;
import org.chakuy.hbelga.adapter.HbelgaAdapter;
import org.chakuy.hbelga.databinding.FragmentSlideshowBinding;
import org.chakuy.hbelga.model.HbelgaModel;
import org.chakuy.hbelga.ui.slideshow.SlideshowViewModel;

import org.chakuy.hbelga.databinding.FragmentVlistaBinding;
import org.chakuy.hbelga.ui.verlista.slideshow.VlistaViewModel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class vlista extends Fragment {

    RecyclerView mRecycler;
    HbelgaAdapter mAdapter;
    FirebaseFirestore mFirestore;

    private FragmentVlistaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        VlistaViewModel vlistaViewModel =
                new ViewModelProvider(this).get(VlistaViewModel.class);

        binding = FragmentVlistaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mFirestore = FirebaseFirestore.getInstance();
        mRecycler = root.findViewById(R.id.recyclerviewListado);

        mRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));

        Query query = mFirestore.collection("hbdb");

        FirestoreRecyclerOptions<HbelgaModel> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<HbelgaModel>().setQuery(query, HbelgaModel.class).build();

        mAdapter = new HbelgaAdapter(firestoreRecyclerOptions);
        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);

        List<hbdbdata> dataList = new ArrayList<>();

        query.get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                hbdbdata data = document.toObject(hbdbdata.class);
                dataList.add(data);
            }

            // aquí es donde puedes escribir los datos en Excel

            // crear un nuevo archivo de Excel
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Datos HBDB");

            // agregar los datos a las celdas
            int rowNum = 0;
            for (hbdbdata data : dataList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(data.getArea());
                row.createCell(1).setCellValue(data.getDescripcion());
                row.createCell(2).setCellValue(data.getEstado());
                row.createCell(3).setCellValue(data.getNombre());
                row.createCell(4).setCellValue(data.getFecha());
            }

            // guardar el archivo
            String filePath = requireActivity().getExternalFilesDir(null).getPath() + "/datos_hbdb.xlsx";
            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                workbook.write(outputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }

            File file = new File(filePath);
            Uri fileUri = FileProvider.getUriForFile(requireContext(), requireContext().getApplicationContext().getPackageName() + ".provider", file);

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("application/vnd.ms-excel");
            intent.putExtra(Intent.EXTRA_STREAM, fileUri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(Intent.createChooser(intent, "Compartir archivo"));

        });

        final TextView textView = binding.textSlideshow;
        vlistaViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}