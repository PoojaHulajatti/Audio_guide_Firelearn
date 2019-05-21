package com.example.firelearn;

import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button mRecordBtn;
    private TextView mRecordLabel;

    private MediaRecorder mRecorder;
    private String mFileName= null;

    private static final String LOG_TAG="Record_log";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecordLabel = (TextView) findViewById(R.id.recordLabel);
        mRecordBtn = (Button) findViewById(R.id.recordBtn);

        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName="/recorded_audio.3gp";

        mRecordBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                if (motionEvent.getAction()== MotionEvent.ACTION_DOWN){
                    startRecording();
                    mRecordLabel.setText("Recording Started.... ");

                }else if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    stopRecording();
                    mRecordLabel.setText("Recording Stopped.... ");
                }
                return false;
            }
        });

    }

        private void startRecording() {
            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mRecorder.setOutputFile(mFileName);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            try {
                mRecorder.prepare();
            } catch (IOException e) {
                Log.e(LOG_TAG, "prepare() failed");
            }

            mRecorder.start();
        }

        private void stopRecording() {
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
        }

}
