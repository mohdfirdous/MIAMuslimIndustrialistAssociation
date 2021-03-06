package com.nexusinfo.mia_muslimindustrialistassociation.ui.activities;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nexusinfo.mia_muslimindustrialistassociation.LocalDatabaseHelper;
import com.nexusinfo.mia_muslimindustrialistassociation.R;
import com.nexusinfo.mia_muslimindustrialistassociation.models.MemberModel;
import com.nexusinfo.mia_muslimindustrialistassociation.models.UserModel;
import com.nexusinfo.mia_muslimindustrialistassociation.viewmodels.MemberViewModel;

import static com.nexusinfo.mia_muslimindustrialistassociation.utils.Util.showCustomToast;

public class MemberProfileActivity extends AppCompatActivity {

    private ProfileViewHolder holder;
    private MemberViewModel viewModel;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_profile);

        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

        holder = new ProfileViewHolder(this);
        viewModel = ViewModelProviders.of(this).get(MemberViewModel.class);

        int memberId = getIntent().getExtras().getInt("memberId");
        String of = getIntent().getStringExtra("Of");

        FetchProfile task = new FetchProfile(this, holder, viewModel);
        task.execute(of, "" + memberId);

        UserModel user = LocalDatabaseHelper.getInstance(this).getUser();

        Log.e("memberId", of + " : " + memberId + " : " + user.getMemberId());

        holder.linearLayoutProductCount.setOnClickListener(view -> {
            if (of.equals("ThisMember") || memberId == user.getMemberId()){
                Intent productsIntent = new Intent(this, HomeActivity.class);
                productsIntent.putExtra("fromProfile", true);
                productsIntent.putExtra("products", true);
                startActivity(productsIntent);
            }
            else if (of.equals("OtherMember")){
                Intent productsIntent = new Intent(this, ProductActivity.class);
                productsIntent.putExtra("memberId", memberId);
                startActivity(productsIntent);
            }
        });

        holder.linearLayoutServiceCount.setOnClickListener(view -> {
            if (of.equals("ThisMember") || memberId == user.getMemberId()){
                Intent servicesIntent = new Intent(this, HomeActivity.class);
                servicesIntent.putExtra("fromProfile", true);
                servicesIntent.putExtra("services", true);
                startActivity(servicesIntent);
            }
            else if (of.equals("OtherMember")){
                Intent servicesIntent = new Intent(this, ServiceActivity.class);
                servicesIntent.putExtra("memberId", memberId);
                startActivity(servicesIntent);
            }
        });
    }

    public static class FetchProfile extends AsyncTask<String, String, MemberModel> {

        private MemberProfileActivity activity;
        private ProfileViewHolder holder;
        private MemberViewModel viewModel;

        public FetchProfile(MemberProfileActivity activity, ProfileViewHolder holder, MemberViewModel viewModel){
            this.activity = activity;
            this.holder = holder;
            this.viewModel = viewModel;
        }

        @Override
        protected void onPreExecute() {
            holder.linearLayout.setVisibility(View.INVISIBLE);
            holder.progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected MemberModel doInBackground(String... strings) {

            MemberModel member = (MemberModel) activity.getIntent().getSerializableExtra("MemberModel");
            viewModel.setMember(member);

            if(viewModel.getMember() == null){
                try {
                    if(strings[0].equals("OtherMember")){
                        viewModel.setMember(activity, true, Integer.parseInt(strings[1]));
                        member = viewModel.getMember();
                    }
                    else {
                        viewModel.setMember(activity, false, 0);
                        member = viewModel.getMember();
                    }
                }
                catch (Exception e) {
                    Log.e("Error", e.toString());
                    publishProgress("Exception");
                    cancel(true);
                }
            }
            else
                member = viewModel.getMember();

            return member;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            if(values[0].equals("Exception")){
                showCustomToast(activity, "Some error occurred.",1);
                holder.progressBar.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        protected void onPostExecute(MemberModel member) {
            holder.linearLayout.setVisibility(View.VISIBLE);
            holder.progressBar.setVisibility(View.INVISIBLE);

            if (member != null){

                String companyName, memberName, memberDesignation, email, mobile, address;
                int productCount, serviceCount;
                float ratings;

                companyName = member.getCompanyName();
                memberName = member.getName();
                memberDesignation = member.getDesignation();
                ratings = member.getRatings();
                email = member.getEmail();
                mobile = member.getMobile();
                address = member.getOfficeAddress();
                productCount = member.getProductCount();
                serviceCount = member.getServiceCount();
                byte[] photoData = member.getPhoto();

                Bitmap bmp;
                if(photoData != null)
                    bmp = BitmapFactory.decodeByteArray(photoData, 0, photoData.length);
                else
                    bmp = ((BitmapDrawable) activity.getResources().getDrawable(R.drawable.ic_profile_white)).getBitmap();

                holder.ivMemberPhoto.setImageBitmap(bmp);

                if (companyName != null && !companyName.equals(""))
                    holder.tvCompanyName.setText(companyName);
                else
                    holder.tvCompanyName.setText("-");

                if (memberName != null && !memberName.equals(""))
                    holder.tvMemberName.setText(memberName);
                else
                    holder.tvMemberName.setText("-");

                if (memberDesignation != null && !memberDesignation.equals(""))
                    holder.tvMemberDesignation.setText(memberDesignation);
                else
                    holder.tvMemberDesignation.setText("-");

                if (ratings != 0f)
                    holder.tvRatings.setText("" + ratings);
                else
                    holder.tvRatings.setText("-");

                if (email != null && !email.equals(""))
                    holder.tvEmail.setText(email);
                else
                    holder.tvEmail.setText("-");

                if (mobile != null && !mobile.equals(""))
                    holder.tvMobile.setText(mobile);
                else
                    holder.tvMobile.setText("-");

                if (address != null && !address.equals(""))
                    holder.tvAddress.setText(address);
                else
                    holder.tvAddress.setText("-");

                holder.tvProductCount.setText("" + productCount);
                holder.tvServiceCount.setText("" + serviceCount);

                UserModel user = LocalDatabaseHelper.getInstance(activity).getUser();

                if (member.getMemberId() == user.getMemberId()){
                    holder.linearLayoutAllDetails.setVisibility(View.VISIBLE);
                    holder.linearLayoutProfileEdit.setVisibility(View.VISIBLE);

                    holder.linearLayoutAllDetails.setOnClickListener(view -> {
                        showCustomToast(activity, "View all details",1);
                    });

                    holder.linearLayoutProfileEdit.setOnClickListener(view -> {
                        showCustomToast(activity, "Edit profile",1);
                    });
                }
                else {
                    holder.linearLayoutAllDetails.setVisibility(View.GONE);
                    holder.linearLayoutProfileEdit.setVisibility(View.GONE);
                }
            }
        }
    }

    public class ProfileViewHolder {
        public LinearLayout linearLayout, linearLayoutAllDetails, linearLayoutProfileEdit,
                            linearLayoutProductCount, linearLayoutServiceCount;
        public TextView tvCompanyName, tvMemberName, tvMemberDesignation,
                        tvProductCount, tvServiceCount, tvRatings,
                        tvEmail, tvMobile, tvAddress;
        public ImageView ivMemberPhoto;
        public ProgressBar progressBar;

        public ProfileViewHolder(Activity activity) {
            linearLayout = activity.findViewById(R.id.linearLayout_profile);
            tvCompanyName = activity.findViewById(R.id.textView_companyName_profile);
            tvMemberName = activity.findViewById(R.id.textView_memberNameProfile);
            tvMemberDesignation = activity.findViewById(R.id.textView_memberDesignationProfile);

            tvProductCount = activity.findViewById(R.id.textView_productCountProfile);
            linearLayoutProductCount = activity.findViewById(R.id.linearLayout_productCountProfile);

            tvServiceCount = activity.findViewById(R.id.textView_serviceCountProfile);
            linearLayoutServiceCount = activity.findViewById(R.id.linearLayout_serviceCountProfile);

            tvRatings = activity.findViewById(R.id.textView_ratingsProfile);
            tvEmail = activity.findViewById(R.id.textView_memberEmailProfile);
            tvMobile = activity.findViewById(R.id.textView_memberMobileProfile);
            tvAddress = activity.findViewById(R.id.textView_memberAddressProfile);
            linearLayoutAllDetails = activity.findViewById(R.id.linearLayout_viewAllDetailsProfile);
            linearLayoutProfileEdit = activity.findViewById(R.id.linearLayout_editProfile);
            ivMemberPhoto = activity.findViewById(R.id.imageView_memberPhotoProfile);
            progressBar = activity.findViewById(R.id.progressBar_profile);
        }
    }
}
