package com.yildirimtechnology.yakalabalik;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Mavibalikzoroyun extends AppCompatActivity {
    TextView skorMZtext;
    TextView zamanMZtext;
    TextView bestscoreMZ;
    int score;
    int bestscore;
    ImageView imageView0;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView imageView10;
    ImageView imageView11;
    ImageView [] imagearray;
    Handler handler;
    Runnable runnable;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mavibalikzoroyun);
        skorMZtext = (TextView) findViewById(R.id.skorMZtext);
        zamanMZtext = (TextView) findViewById(R.id.zamanMZtext);
        bestscoreMZ = (TextView) findViewById(R.id.bestscoreMZ);
        imageView0 = findViewById(R.id.imageView0);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);
        imageView10 = findViewById(R.id.imageView10);
        imageView11 = findViewById(R.id.imageView11);
        imagearray = new ImageView[] {imageView0 ,imageView1 , imageView2 , imageView3 , imageView4 , imageView5 , imageView6 , imageView7 , imageView8,imageView9,imageView10,imageView11};
        sharedPreferences = this.getSharedPreferences("com.yildirimtechnology.yakalabalik", Context.MODE_PRIVATE);
        final int bestScore = sharedPreferences.getInt("bestScore", 0);
        bestscoreMZ.setText("En Yüksek : " + bestScore);
        resimlerigizle();
        score = 0 ;
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                zamanMZtext.setText("Zaman: " + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                if (score > bestScore){
                    bestscoreMZ.setText("Best: " + score);
                    sharedPreferences.edit().putInt("bestScore", score).apply();
                } else {
                    bestscoreMZ.setText("Best: " + bestScore);
                }
                zamanMZtext.setText("Süreniz Sona Erdi" );
                handler.removeCallbacks(runnable);
                for (ImageView image: imagearray) {
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert = new AlertDialog.Builder(Mavibalikzoroyun.this);
                alert.setTitle("Baştan Başlamak İstermisiniz ?");
                alert.setMessage("Skorunuz: "+score + "\n");
                alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //restart
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(Mavibalikzoroyun.this, "Oyun Sona Erdi", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Mavibalikzoroyun.this , MainActivity.class);
                        startActivity(intent);
                    }
                });
                alert.show();

            }
        }.start();
    }
    public void skorartir (View view) {
        score++;
        skorMZtext.setText("Skor :" + score);
    }
    public void resimlerigizle() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image: imagearray) {
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(12);
                imagearray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this, 200);
            }
        };

        handler.post(runnable);
    }
}



