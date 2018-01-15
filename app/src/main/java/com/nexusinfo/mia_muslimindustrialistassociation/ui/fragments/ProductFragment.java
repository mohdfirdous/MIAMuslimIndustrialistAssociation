package com.nexusinfo.mia_muslimindustrialistassociation.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nexusinfo.mia_muslimindustrialistassociation.R;
import com.nexusinfo.mia_muslimindustrialistassociation.models.ProductModel;
import com.nexusinfo.mia_muslimindustrialistassociation.ui.activities.AddProductActivity;
import com.nexusinfo.mia_muslimindustrialistassociation.ui.adapters.ProductAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment {


    public ProductFragment() {
        // Required empty public constructor
    }

    private View view;
    private FloatingActionButton mFab;
    private RecyclerView mRecyclerView;
    private List<ProductModel> mProducts;
    private ProductAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        getActivity().setTitle(R.string.title_my_products);

        view = inflater.inflate(R.layout.fragment_product, container, false);

        mFab = view.findViewById(R.id.fab_add_product);
        mRecyclerView = view.findViewById(R.id.recyclerView_product);

        mProducts = new ArrayList<>();

        for(int i = 0; i < 10; i++) {
            ProductModel model = new ProductModel();
            model.setProductName("Nedusoft");
            model.setCategoryName("Onespot Nexusinfo");
            model.setSpecification("School ERP software for Management of Schools/Colleges");

            mProducts.add(model);
        }

        mAdapter = new ProductAdapter(getContext(), mProducts);
        mRecyclerView.setAdapter(mAdapter);

        mFab.setOnClickListener(v -> {
            Intent addProductIntent = new Intent(getContext(), AddProductActivity.class);
            startActivity(addProductIntent);
        });

        return view;
    }

}
