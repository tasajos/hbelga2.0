package org.chakuy.hbelga.ui.verlista.slideshow;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.chakuy.hbelga.R;
import org.chakuy.hbelga.databinding.FragmentSlideshowBinding;
import org.chakuy.hbelga.ui.slideshow.SlideshowViewModel;

import org.chakuy.hbelga.databinding.FragmentVlistaBinding;
import org.chakuy.hbelga.ui.verlista.slideshow.VlistaViewModel;

public class vlista extends Fragment {

    private FragmentVlistaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        VlistaViewModel vlistaViewModel =
                new ViewModelProvider(this).get(VlistaViewModel.class);

        binding = FragmentVlistaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;
        vlistaViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}