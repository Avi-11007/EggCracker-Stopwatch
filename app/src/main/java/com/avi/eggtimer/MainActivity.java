package com.avi.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView timerTextView;
    SeekBar timerSeekBar;
    Boolean counterIsActive=false;
    Button goButton;
    CountDownTimer countDownTimer;
    ImageView imageView;
    public void resetTimer(){
        timerTextView.setText("00:07");
        timerSeekBar.setProgress(7);
        timerSeekBar.setEnabled(true);
        countDownTimer.cancel();
        goButton.setText("G0!");
        imageView.setImageResource(R.drawable.egg);
        counterIsActive=false;
    }

    public void buttonClicked(View view) {
        imageView = findViewById(R.id.eggImageView);
        if (counterIsActive) {
            resetTimer();

        } else {
            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            goButton.setText("STOP!");

             countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.eggcrack);
                    mplayer.start();
                    imageView.setImageResource(R.drawable.cracked);
                    resetTimer();

                }
            }.start();
        }
    }
    public void updateTimer(int secondsLeft){
        int minutes=secondsLeft/60;
        int seconds=secondsLeft-(minutes*60);
        String secondString=Integer.toString(seconds);
        if (seconds<=9){
            secondString="0"+secondString;
        }

        timerTextView.setText(Integer.toString(minutes)+":"+secondString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerSeekBar=findViewById(R.id.timerSeekBar);
        timerTextView=findViewById(R.id.countdownTextView);
        goButton=findViewById(R.id.goButton);


        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(7);
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}