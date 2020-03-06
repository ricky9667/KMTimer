package com.example.kmtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView timerTextView;
    SeekBar timeSeekBar;
    ImageView imageView;
    int totalTime = 1;

    public String convertTimeText(int time) {
        String minutes, seconds;
        minutes = ((totalTime / 60 < 10)?"0":"") + (totalTime / 60);
        seconds = ((totalTime % 60 < 10)?"0":"") + (totalTime % 60);
        return minutes + ":" + seconds;
    }

    public void startTimer(View view) {

        timeSeekBar.setEnabled(false);

        new CountDownTimer(totalTime * 1000, 1000) {

            public void onTick(long millisLeft) {
                totalTime--;
                timerTextView.setText(convertTimeText((int) millisLeft));
            }

            public void onFinish() {
                imageView.setImageResource(R.drawable.timer_finish_image);
                timeSeekBar.setEnabled(true);
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView = findViewById(R.id.timerTextView);
        timeSeekBar = findViewById(R.id.timeSeekBar);
        imageView = findViewById(R.id.imageView);

        timeSeekBar.setMax(3600);
        timeSeekBar.setProgress(totalTime);
        timerTextView.setText(convertTimeText(totalTime));

        timeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                Log.i("SeekBar progress", Integer.toString(progress));
                totalTime = (Math.max(progress, 1));
                timerTextView.setText(convertTimeText(totalTime));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                imageView.setImageResource(R.drawable.title_image);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

    }
}
