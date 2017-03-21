package com.example.du.isec2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class SplashScreen extends AppCompatActivity {
    private static final int SPLASH_DURATION = 10000;
    ProgressBar mProgressBar;
    boolean mbActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
                Thread timerThread = new Thread() {
                    @Override
                    public void run() {
                        mbActive = true;
                        try {
                            int waited = 0;
                            while (mbActive && (waited < SPLASH_DURATION)) {
                                sleep(150);
                                if (mbActive) {
                                    waited += 500;
                                    updateProgress(waited);
                                }
                            }
                        } catch (InterruptedException e) {
                        } finally {
                            onContinue();
                        }
                    }
                };
                timerThread.start();
            }

            public void updateProgress(final int timePasses) {
                if (mProgressBar != null) {
                    final int progress = mProgressBar.getMax() * timePasses / SPLASH_DURATION;
                    mProgressBar.setProgress(progress);

                }
            }

            public void onContinue() {
                Intent intent = new Intent(SplashScreen.this, daysSchedule.class);
                startActivity(intent);
                finish();
            }

            protected void onPause(){
                super.onPause();
                finish();
            }
        }

