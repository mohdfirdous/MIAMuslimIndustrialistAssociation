package com.nexusinfo.mia_muslimindustrialistassociation.ui.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.nexusinfo.mia_muslimindustrialistassociation.R;

public class AddServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                finish();
                break;
        }

        return true;
    }

    class AddServiceViewHolder {
        public EditText etServiceName, etServiceDescription, etServiceKeywords;
        public ImageView ivImageUpload;

        public AddServiceViewHolder(AddProductActivity activity) {

            etServiceName = activity.findViewById(R.id.editText_serviceName);
            etServiceDescription = activity.findViewById(R.id.editText_serviceDescription);
            etServiceKeywords = activity.findViewById(R.id.editText_serviceKeywords);
            ivImageUpload = activity.findViewById(R.id.imageView_uploadServicePhoto);

        }
    }
}
