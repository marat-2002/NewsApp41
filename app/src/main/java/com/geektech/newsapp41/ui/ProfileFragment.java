package com.geektech.newsapp41.ui;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.geektech.newsapp41.Prefs;
import com.geektech.newsapp41.R;
import com.geektech.newsapp41.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private Prefs prefs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prefs = new Prefs(requireContext());
        binding.editText.setText(prefs.getUserName());
        binding.imageView.setOnClickListener(view1 -> mGetContent.launch("image/*"));
        if (prefs.getImage() != null){
            Glide.with(binding.imageView).load(prefs.getImage()).into(binding.imageView);
        }
    }

    @Override
    public void onDestroy() {
        prefs.saveUserName(binding.editText.getText().toString());
        super.onDestroy();
    }
    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri result) {
           Glide.with(binding.imageView).load(result).into(binding.imageView);
           prefs.saveImage(String.valueOf(result));
        }
    });
}