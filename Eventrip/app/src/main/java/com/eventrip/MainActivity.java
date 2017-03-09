package com.eventrip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD
        LinearLayout searchButton = (LinearLayout) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.searchButton:
                //Intent intent = new Intent(this, new MainActivity());
                break;
        }
=======
        Intent intent = new Intent(this,ListActivity.class);
        startActivity(intent);
>>>>>>> 6cb0e5b315562031575a413628efddf8e8fd28b4
    }
}
