package com.yildirimtechnology.yakalabalik;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.GnssAntennaInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Mavibalikkolayoyun extends AppCompatActivity {
    TextView skortext;
    TextView zamantext;
    TextView bestscoreMK;
    int score;
    int bestscore;
    ImageView imageView0;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView [] imagearray;
    Handler handler;
    Runnable runnable;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mavibalikkolayoyun);
        skortext = (TextView) findViewById(R.id.skortext);
        zamantext = (TextView) findViewById(R.id.zamantext);
        bestscoreMK = (TextView) findViewById(R.id.bestscoreMK);
        imageView0 = findViewById(R.id.imageView0);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imagearray = new ImageView[] {imageView0 ,imageView1 , imageView2 , imageView3};
        sharedPreferences = this.getSharedPreferences("com.yildirimtechnology.yakalabalik", Context.MODE_PRIVATE);
        final int bestScore = sharedPreferences.getInt("bestScore", 0);
        bestscoreMK.setText("En Yüksek : " + bestScore);
        resimlerigizle();
        score = 0 ;
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                zamantext.setText("Zaman: " + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                if (score > bestScore){
                    bestscoreMK.setText("Best: " + score);
                    sharedPreferences.edit().putInt("bestScore", score).apply();
                } else {
                    bestscoreMK.setText("En Yüksek: " + bestScore);
                }
                    zamantext.setText("Süreniz Sona Erdi" );
                    handler.removeCallbacks(runnable);
                for (ImageView image: imagearray) {
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert = new AlertDialog.Builder(Mavibalikkolayoyun.this);
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
                        Toast.makeText(Mavibalikkolayoyun.this, "Oyun Sona Erdi!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Mavibalikkolayoyun.this , MainActivity.class);
                        startActivity(intent);
                    }
                });
                alert.show();

            }
        }.start();
    }
    public void skorartir (View view) {
        score++;
        skortext.setText("Skor :" + score);
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
                int i = random.nextInt(4);
                imagearray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this, 1000);
            }
        };

        handler.post(runnable);
    }
}

