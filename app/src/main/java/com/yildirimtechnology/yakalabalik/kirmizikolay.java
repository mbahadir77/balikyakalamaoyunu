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

public class kirmizikolay extends AppCompatActivity {
    TextView skorKKtext;
    TextView zamanKKtext;
    TextView bestscoreKK;
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
        setContentView(R.layout.activity_kirmizikolay);
        skorKKtext = (TextView) findViewById(R.id.skorKKtext);
        zamanKKtext = (TextView) findViewById(R.id.zamanKKtext);
        bestscoreKK = (TextView) findViewById(R.id.bestscoreKK);
        imageView0 = findViewById(R.id.imageView0);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imagearray = new ImageView[] {imageView0 ,imageView1 , imageView2 , imageView3};
        sharedPreferences = this.getSharedPreferences("com.yildirimtechnology.yakalabalik", Context.MODE_PRIVATE);
        final int bestScore = sharedPreferences.getInt("bestScore", 0);
        bestscoreKK.setText("En Yüksek : " + bestScore);
        resimlerigizle();
        score = 0 ;
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                zamanKKtext.setText("Zaman: " + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                if (score > bestScore){
                    bestscoreKK.setText("Best: " + score);
                    sharedPreferences.edit().putInt("bestScore", score).apply();
                } else {
                    bestscoreKK.setText("Best: " + bestScore);
                }
                zamanKKtext.setText("Süreniz Sona Erdi" );
                handler.removeCallbacks(runnable);
                for (ImageView image: imagearray) {
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert = new AlertDialog.Builder(kirmizikolay.this);
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
                        Toast.makeText(kirmizikolay.this, "Oyun Sona Erdi!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(kirmizikolay.this , MainActivity.class);
                        startActivity(intent);
                    }
                });
                alert.show();

            }
        }.start();
    }
    public void skorartir (View view) {
        score++;
        skorKKtext.setText("Skor :" + score);
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
