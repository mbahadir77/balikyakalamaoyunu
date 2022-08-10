package com.yildirimtechnology.yakalabalik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Mavibalikzorluk extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mavibalikzorluk);

    }
    public void kolay (View view) {
        Intent intent = new Intent(Mavibalikzorluk.this , Mavibalikkolayoyun.class);
        startActivity(intent);
    }
    public void orta (View view) {
        Intent intent = new Intent(Mavibalikzorluk.this ,Mavibalikortaoyun.class);
        startActivity(intent);
    }
    public void zor (View view) {
        Intent intent =new Intent(Mavibalikzorluk.this , Mavibalikzoroyun.class);
        startActivity(intent);
    }




}