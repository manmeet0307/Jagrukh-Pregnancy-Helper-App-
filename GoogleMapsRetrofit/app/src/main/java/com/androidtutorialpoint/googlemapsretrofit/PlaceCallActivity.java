package com.androidtutorialpoint.googlemapsretrofit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.sinch.android.rtc.calling.Call;

public class PlaceCallActivity extends BaseActivity {

//    private Button mCallButton;
//    private EditText mCallName;
      TextView videoCall, phoneCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

//        mCallName = (EditText) findViewById(R.id.callName);
//        mCallButton = (Button) findViewById(R.id.callButton);
//        mCallButton.setEnabled(false);
//        mCallButton.setOnClickListener(buttonClickListener);
        videoCall = (TextView) findViewById(R.id.videoCall);
        videoCall.setEnabled(false);
        videoCall.setOnClickListener(buttonClickListener);
        phoneCall = (TextView) findViewById(R.id.phoneCall);
        phoneCall.setOnClickListener(buttonClickListener);
        Button stopButton = (Button) findViewById(R.id.stopButton);
        stopButton.setOnClickListener(buttonClickListener);
    }

    @Override
    protected void onServiceConnected() {
        TextView userName = (TextView) findViewById(R.id.loggedInName);
//        userName.setText(getSinchServiceInterface().getUserName());
        userName.setText("Doctor");
        videoCall.setEnabled(true);
    }

    @Override
    public void onDestroy() {
        if (getSinchServiceInterface() != null) {
            getSinchServiceInterface().stopClient();
        }
        super.onDestroy();
    }

    private void stopButtonClicked() {
        if (getSinchServiceInterface() != null) {
            getSinchServiceInterface().stopClient();
        }
        finish();
    }

    private void callButtonClicked() {
        String userName = "doctor";
//        if (userName.isEmpty()) {
//            Toast.makeText(this, "Please enter a user to call", Toast.LENGTH_LONG).show();
//            return;
//        }

        Call call = getSinchServiceInterface().callUserVideo(userName);
        String callId = call.getCallId();

        Intent callScreen = new Intent(this, CallScreenActivity.class);
        callScreen.putExtra(SinchService.CALL_ID, callId);
        startActivity(callScreen);
    }

    private void phoneCallClicked(){
        String number = "tel:08585962392";
        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(number));
        startActivity(callIntent);


    }

    private OnClickListener buttonClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.videoCall:
                    callButtonClicked();
                    break;

                case R.id.stopButton:
                    stopButtonClicked();
                    break;
                case R.id.phoneCall:
                    phoneCallClicked();
                    break;

            }
        }
    };
}
