package com.example.kmtimer;

import androidx.appcompat.app.AppCompatActivity;

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
    int totalTime = 60;

    public String convertTimeText(int time) {
        String minutes, seconds;

        minutes = ((totalTime / 60 < 10)?"0":"") + (totalTime / 60);
        seconds = ((totalTime % 60 < 10)?"0":"") + (totalTime % 60);

        return minutes + ":" + seconds;
    }

    public void startTimer(View view) {

        final ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.title_image);

        new CountDownTimer(totalTime * 1000, 1000) {

            public void onTick(long millisLeft) {

                timerTextView.setText(convertTimeText((int) millisLeft));
                totalTime--;
            }

            public void onFinish() {
                Toast.makeText(getApplicationContext(), "Time is out!", Toast.LENGTH_SHORT).show();
                timerTextView.setText("00:00");
                imageView.setImageResource(R.drawable.timer_finish_image);
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView = findViewById(R.id.timerTextView);

        SeekBar timeSeekBar = findViewById(R.id.timeSeekBar);

        timeSeekBar.setMax(3600);
        timeSeekBar.setProgress((int) totalTime);
        timerTextView.setText(convertTimeText(totalTime));

        timeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                Log.i("SeekBar progress", Integer.toString(progress));
                totalTime = (Math.max(progress, 1));
                timerTextView.setText(convertTimeText(totalTime));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

    }
}
