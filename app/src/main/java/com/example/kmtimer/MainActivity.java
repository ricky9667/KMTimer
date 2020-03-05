package com.example.kmtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView timerTextView;
    long totalTime = 5;

    public void startTimer(View view) {

        new CountDownTimer(totalTime * 1000, 1000) {

            String minutes, seconds, timerText;

            public void onTick(long millisLeft) {

                minutes = ((totalTime / 60 < 10)?"0":"") + (totalTime / 60);
                seconds = ((totalTime % 60 < 10)?"0":"") + (totalTime % 60);

                timerText = minutes + ":" + seconds;

                timerTextView.setText(timerText);
                totalTime--;
            }

            public void onFinish() {
                Toast.makeText(getApplicationContext(), "Time is out!", Toast.LENGTH_SHORT).show();
                timerTextView.setText("00:00");
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.startButton);
        timerTextView = findViewById(R.id.timerTextView);
    }
}
