package com.nexusinfo.mia_muslimindustrialistassociation.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import com.nexusinfo.mia_muslimindustrialistassociation.LocalDatabaseHelper;
import com.nexusinfo.mia_muslimindustrialistassociation.connection.DatabaseConnection;
import com.nexusinfo.mia_muslimindustrialistassociation.models.ProductModel;
import com.nexusinfo.mia_muslimindustrialistassociation.models.UserModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Created by firdous on 1/13/2018.
 */

public class ProductViewModel extends ViewModel {

    private List<ProductModel> products;
    private int memberId;

    public void setProducts(Context context, boolean others, int memberId) throws Exception{
        products = new ArrayList<>();

        UserModel user = LocalDatabaseHelper.getInstance(context).getUser();

        if (others){
            this.memberId = memberId;
        }
        else {
            this.memberId = user.getMemberId();
        }

        DatabaseConnection connection = new DatabaseConnection(DatabaseConnection.MIA_DB_NAME);
        Connection conn = connection.getConnection();

        Statement stmt = conn.createStatement();

        String query = "SELECT ProductId, PorductName, CompanyName, Name, Designation, CategoryId, CategoryName, SubCategoryId, SubCategoryName, Specification, Photo, isActive, cmpid, brcode FROM View_MemberProduct WHERE MemberId = " + this.memberId;
        Log.e("ProductsQuery", query);

        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            ProductModel productModel = new ProductModel();

            productModel.setProductId(rs.getInt("ProductId"));
            productModel.setProductName(rs.getString("PorductName"));

            productModel.setMemberId(this.memberId);
            productModel.setMemberName(rs.getString("Name"));
            productModel.setMemberDesignation(rs.getString("Designation"));
            productModel.setCompanyName(rs.getString("CompanyName"));

            productModel.setCategoryId(rs.getInt("CategoryId"));
            productModel.setCategoryName(rs.getString("CategoryName"));

            productModel.setSubCategoryId(rs.getInt("SubCategoryId"));
            productModel.setSubCategoryName(rs.getString("SubCategoryName"));

            productModel.setSpecification(rs.getString("Specification"));
            productModel.setPhoto(rs.getBytes("Photo"));
            productModel.setActive(rs.getBoolean("isActive"));
            productModel.setCompanyId(rs.getString("cmpid"));
            productModel.setBranchCoce(rs.getString("brcode"));

            products.add(productModel);
        }
    }

    public void setProducts(List<ProductModel> products){
        this.products = products;
    }

    public List<ProductModel> getProducts() {
        return products;
    }
}
