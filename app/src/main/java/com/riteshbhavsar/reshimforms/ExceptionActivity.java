package com.riteshbhavsar.reshimforms;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by ritesh.bhavsar on 10-07-2017.
 */

public class ExceptionActivity extends AppCompatActivity {

    TextView txt_error;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exception);

        txt_error = (TextView) findViewById(R.id.txt_exception);

        txt_error.setText(getIntent().getStringExtra("error"));



    }
}
