package com.yildirimtechnology.yakalabalik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class choosecolor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosecolor);
    }
    public void buttonmavibalik (View view) {
        Intent intent = new Intent(choosecolor.this , Mavibalikzorluk.class);
        startActivity(intent);
    }
    public void buttonkirmizibalik (View view) {
        Intent intent = new Intent(choosecolor.this,kirmizibalikzorluk.class);
        startActivity(intent);
    }

}