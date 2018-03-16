package com.rajramchandani.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView tv;
    int flag=0;
    int bool=0;
    CountDownTimer countdowntimer;
    public void updatetimer(int progress)
    {
        int minutes=progress/60;
        int seconds=progress-minutes*60;
        if(minutes<10) {
            if (seconds < 10) {

                tv.setText("0"+Integer.toString(minutes) + ":" + "0" + Integer.toString(seconds));

            } else {
                tv.setText("0"+Integer.toString(minutes) + ":" + Integer.toString(seconds));
            }
        }
        else
        {
            if (seconds < 10) {

                tv.setText("0"+Integer.toString(minutes) + ":" + "0" + Integer.toString(seconds));

            } else {
                tv.setText("0"+Integer.toString(minutes) + ":" + Integer.toString(seconds));
            }
        }

    }

    public void controltimer(View view)
    {
        final Button b=(Button)findViewById(R.id.controltimer);
        if(flag==0) {
            flag=1;
            b.setText("STOP");
          countdowntimer=  new CountDownTimer(seekBar.getProgress() * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    seekBar.setEnabled(false);
                    updatetimer((int) millisUntilFinished / 1000);
                }
                @Override
                public void onFinish() {
                    MediaPlayer media=MediaPlayer.create(getApplicationContext(),R.raw.horn);
                    media.start();
                    flag=0;
                    seekBar.setEnabled(true);
                    b.setText("GO!");

                }
            }.start();

        }
        else
        {
            flag=0;
            seekBar.setEnabled(true);
            seekBar.setProgress(0);
            b.setText("GO!");
            tv.setText("00:00");
            countdowntimer.cancel();

        }


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         seekBar=(SeekBar)findViewById(R.id.seekBar);
         tv=(TextView)findViewById(R.id.textView);
        seekBar.setMax(600);
        seekBar.setProgress(0);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               updatetimer(progress);
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
