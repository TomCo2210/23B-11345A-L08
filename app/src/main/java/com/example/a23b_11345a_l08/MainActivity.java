package com.example.a23b_11345a_l08;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;

import com.example.a23b_11345a_l08.Interfaces.StepCallback;
import com.example.a23b_11345a_l08.Utilities.StepDetector;
import com.google.android.material.textview.MaterialTextView;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private MaterialTextView main_LBL_stepsX;
    private MaterialTextView main_LBL_stepsY;

    private StepDetector stepDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        initStepDetector();
    }

    private void initStepDetector() {
        stepDetector = new StepDetector(this, new StepCallback() {
            @Override
            public void stepX() {
                main_LBL_stepsX.setText("" + stepDetector.getStepsX());

            }

            @Override
            public void stepY() {
                main_LBL_stepsY.setText("" + stepDetector.getStepsY());
            }

            @Override
            public void stepZ() {
                // pass
            }
        });
    }

    private void findViews() {
        main_LBL_stepsX = findViewById(R.id.main_LBL_stepsX);
        main_LBL_stepsY = findViewById(R.id.main_LBL_stepsY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer = MediaPlayer.create(this, R.raw.lifelike);
        mediaPlayer.setVolume(1.0f,1.0f);
        mediaPlayer.start(); // no need to call prepare(); create() does that for you
        stepDetector.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
        stepDetector.stop();
    }

}