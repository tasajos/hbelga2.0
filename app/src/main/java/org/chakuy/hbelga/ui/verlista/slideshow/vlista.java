package org.chakuy.hbelga.ui.verlista.slideshow;

import androidx.lifecycle.ViewModelProvider;

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

import org.chakuy.hbelga.R;
import org.chakuy.hbelga.adapter.HbelgaAdapter;
import org.chakuy.hbelga.databinding.FragmentSlideshowBinding;
import org.chakuy.hbelga.model.HbelgaModel;
import org.chakuy.hbelga.ui.slideshow.SlideshowViewModel;

import org.chakuy.hbelga.databinding.FragmentVlistaBinding;
import org.chakuy.hbelga.ui.verlista.slideshow.VlistaViewModel;

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