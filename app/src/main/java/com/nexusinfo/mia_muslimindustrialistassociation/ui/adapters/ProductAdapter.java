package com.nexusinfo.mia_muslimindustrialistassociation.ui.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nexusinfo.mia_muslimindustrialistassociation.R;
import com.nexusinfo.mia_muslimindustrialistassociation.models.ProductModel;

import java.util.List;

/**
 * Created by firdous on 1/15/2018.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    private List<ProductModel> mProducts;
    private Context mContext;

    public ProductAdapter(Context mContext, List<ProductModel> mProducts) {
        this.mProducts = mProducts;
        this.mContext = mContext;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.listitem_product, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {

        byte[] photoData = mProducts.get(position).getPhoto();
        String productName = mProducts.get(position).getProductName();
        String companyName = mProducts.get(position).getCompanyName();
        String productSpecification = mProducts.get(position).getSpecification();

        if(photoData != null) {
            Bitmap bmp = BitmapFactory.decodeByteArray(photoData, 0, photoData.length);
            holder.ivProductPhoto.setImageBitmap(bmp);
        }

        if (productName != null && !productName.equals(""))
            holder.tvProductName.setText(productName);
        else
            holder.tvProductName.setText("-");

        if (companyName != null && !companyName.equals(""))
            holder.tvCompanyName.setText(companyName);
        else
            holder.tvCompanyName.setText("-");

        if (productSpecification != null && !productSpecification.equals(""))
            holder.tvProductSpecification.setText(productSpecification);
        else
            holder.tvProductSpecification.setText("-");

    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{

        ImageView ivProductPhoto;
        TextView tvProductName, tvCompanyName, tvProductSpecification;


        public ProductViewHolder(View itemView) {
            super(itemView);

            ivProductPhoto = itemView.findViewById(R.id.imageView_productPhoto);
            tvProductName = itemView.findViewById(R.id.textView_productName);
            tvCompanyName = itemView.findViewById(R.id.textView_companyName);
            tvProductSpecification = itemView.findViewById(R.id.textView_productSpecification);
        }
    }
}
