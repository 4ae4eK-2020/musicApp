package com.example.myapplication;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button stopBtn, sound1Btn, sound2Btn;
    private MediaPlayer mediaPlayer;
    private Context context = this;
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        stopBtn = findViewById(R.id.button);
        sound1Btn = findViewById(R.id.button2);
        sound2Btn = findViewById(R.id.button3);

        mediaPlayer = MediaPlayer.create(this, R.raw.nggg);

        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        });

        sound1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = MediaPlayer.create(context, R.raw.nggg);
                } else {
                    mediaPlayer = MediaPlayer.create(context, R.raw.nggg);
                }

                mediaPlayer.start();

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    long[] pattern = {0, 400, 200, 200};
                    //vibrator.vibrate(VibrationEffect.createWaveform(pattern, -1));
                    vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    vibrator.vibrate(1000);
                }
            }
        });

        sound2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = MediaPlayer.create(context, R.raw.vine_boom);
                } else {
                    mediaPlayer = MediaPlayer.create(context, R.raw.vine_boom);
                }

                mediaPlayer.start();
            }
        });
    }
}