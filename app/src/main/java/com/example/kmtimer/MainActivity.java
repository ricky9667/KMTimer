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

public class MainActivity extends AppCompatActivity {

    TextView timerTextView;
    Button startButton;
    SeekBar timeSeekBar;
    ImageView imageView;
    MediaPlayer mediaPlayer;
    int totalTime = 1;

    public String convertTimeText(int time) {
        String minutes, seconds;
        minutes = ((totalTime / 60 < 10)?"0":"") + (totalTime / 60);
        seconds = ((totalTime % 60 < 10)?"0":"") + (totalTime % 60);
        return minutes + ":" + seconds;
    }

    public void startTimer(View view) {

        startButton.setEnabled(false);

        timeSeekBar.setEnabled(false);
        MediaPlayer.create(this, R.raw.timer_start).start();
        mediaPlayer = MediaPlayer.create(this, R.raw.timer_stop);

        new CountDownTimer(totalTime * 1000, 1000) {

            public void onTick(long millisLeft) {
                timerTextView.setText(convertTimeText((int) millisLeft));
                totalTime--;
            }

            public void onFinish() {
                timerTextView.setText("00:00");
                imageView.setImageResource(R.drawable.timer_finish_image);

                timeSeekBar.setEnabled(true);
                startButton.setEnabled(true);

                mediaPlayer.start();
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        timerTextView = findViewById(R.id.timerTextView);
        timeSeekBar = findViewById(R.id.timeSeekBar);
        imageView = findViewById(R.id.imageView);

        timeSeekBar.setMax(1200);
        timeSeekBar.setProgress(totalTime);
        timerTextView.setText(convertTimeText(totalTime));

        mediaPlayer = MediaPlayer.create(this, R.raw.time_change);


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
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.start();
            }
        });

    }
}
