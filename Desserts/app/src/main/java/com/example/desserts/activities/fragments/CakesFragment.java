package com.example.desserts.activities.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desserts.R;
import com.example.desserts.activities.adaptors.ItemListAdapter;
import com.example.desserts.database.DBLoader;
import com.example.desserts.databinding.FragmentCakesBinding;
import com.example.desserts.structures.Dessert;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.DESKeySpec;

public class CakesFragment extends Fragment {

    private FragmentCakesBinding binding;
    private View view;
    private List<Dessert> cakesList = new ArrayList<>();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

//        binding = FragmentCakesBinding.inflate(inflater, container, false);

//        List<Dessert> cakesList = DBLoader.getAllCakes();
//        MockDataSet mockDataSet = new MockDataSet();
//        List<Dessert> cakesList = mockDataSet.getItems();
//        List<Dessert> cakesList = DBLoader.getAllCakes();
        view = inflater.inflate(R.layout.fragment_cakes, container, false);
        RecyclerView cakeRecyclerView = (RecyclerView) view.findViewById(R.id.cake_listview);
        ItemListAdapter itemListAdapter = new ItemListAdapter(cakesList, "cake");
        cakeRecyclerView.setAdapter(itemListAdapter);
        cakeRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false));

        return view;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                NavHostFragment.findNavController(CakesFragment.this)
////                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//            }
//        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void populateCakes(List<Dessert> cakes) {
        cakesList = cakes;
    }

}