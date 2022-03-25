package com.geektech.newsapp41.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.geektech.newsapp41.R;
import com.geektech.newsapp41.databinding.FragmentHomeBinding;
import com.geektech.newsapp41.databinding.FragmentNewsBinding;

import models.News;


public class NewsFragment extends Fragment {
    private FragmentNewsBinding binding;
    private News news;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = FragmentNewsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        news = (News) requireArguments().getSerializable("update");
        if (news != null) {
            binding.editText.setText(news.getTitle());
            binding.btnSave.setText("Edit");
        }
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();

            }
        });
    }
    private void save() {
        String text = binding.editText.getText().toString();
        Bundle bundle = new Bundle();
        if (news == null) {
            news = new News(text, System.currentTimeMillis());
        } else {
            news.setTitle(text);
        }
        bundle.putSerializable("news", news);
        getParentFragmentManager().setFragmentResult("rk_news", bundle);
        close();
    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }
}