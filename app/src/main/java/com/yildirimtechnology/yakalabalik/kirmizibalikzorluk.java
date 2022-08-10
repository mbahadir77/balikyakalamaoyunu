package com.yildirimtechnology.yakalabalik;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class kirmizibalikzorluk extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kirmizibalikzorluk);
    }
    public void  kirmizikolay (View view){
        Intent intent =new Intent(kirmizibalikzorluk.this,kirmizikolay.class);
        startActivity(intent);
    }
    public void  kirmiziorta (View view) {
        Intent intent = new Intent(kirmizibalikzorluk.this, kirmiziorta.class);
        startActivity(intent);
    }
    public void  kirmizizor (View view) {
        Intent intent = new Intent(kirmizibalikzorluk.this,kirmizizor.class);
        startActivity(intent);
    }
}



