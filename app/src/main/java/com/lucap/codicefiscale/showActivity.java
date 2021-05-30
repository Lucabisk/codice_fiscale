package com.lucap.codicefiscale;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class showActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvCF;
    private String cf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_activity);
        Intent intent = getIntent();
        cf = intent.getStringExtra("cf");
        tvCF = findViewById(R.id.tvCF);
        tvCF.setText(cf);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //
    }

    @Override
    public void onBackPressed() {
        //
    }

    @Override
    public void onClick(View view) {

    }



}
