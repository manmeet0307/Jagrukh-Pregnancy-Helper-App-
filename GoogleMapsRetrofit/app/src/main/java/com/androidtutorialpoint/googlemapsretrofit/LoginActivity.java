package com.androidtutorialpoint.googlemapsretrofit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.tbouron.shakedetector.library.ShakeDetector;
import com.sinch.android.rtc.SinchError;

import java.util.ArrayList;

public class LoginActivity extends BaseActivity implements SinchService.StartFailedListener {

    private Button mLoginButton;
    private EditText mLoginName;
    private ProgressDialog mSpinner;

    Button checkMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
//
        ShakeDetector.create(this, new ShakeDetector.OnShakeListener() {
            @Override
            public void OnShake() {
//                String number = "tel:08585962392";
//                Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(number));
//                startActivity(callIntent);
                ArrayList<String> phone_nos= new ArrayList<String>();
                phone_nos.add("08585962392");
                phone_nos.add("09958962625");
                for(int i=0;i<phone_nos.size();++i){
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phone_nos.get(i), null, "ALERT: I need help. I am not feeling well.", null, null);
                }
            }

        });
        mLoginName = (EditText) findViewById(R.id.loginName);

        mLoginButton = (Button) findViewById(R.id.loginButton);
        mLoginButton.setEnabled(false);
        mLoginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                loginClicked();
            }
        });
    }



    @Override
    protected void onServiceConnected() {
        mLoginButton.setEnabled(true);
        getSinchServiceInterface().setStartListener(this);
    }

    @Override
    protected void onPause() {
        if (mSpinner != null) {
            mSpinner.dismiss();
        }
        super.onPause();
    }

    @Override
    public void onStartFailed(SinchError error) {
        Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show();
        if (mSpinner != null) {
            mSpinner.dismiss();
        }
    }

    @Override
    public void onStarted() {
        openPlaceCallActivity();
    }

    private void loginClicked() {
        String userName = "UserName";

        if (userName.isEmpty()) {
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
            return;
        }

        if (!getSinchServiceInterface().isStarted()) {
            getSinchServiceInterface().startClient(userName);
            showSpinner();
        } else {
            openPlaceCallActivity();
        }
    }

    private void openPlaceCallActivity() {
        Intent mainActivity = new Intent(this, PlaceCallActivity.class);
        startActivity(mainActivity);
    }

    private void showSpinner() {
        mSpinner = new ProgressDialog(this);
        mSpinner.setTitle("Logging in");
        mSpinner.setMessage("Please wait...");
        mSpinner.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ShakeDetector.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        ShakeDetector.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ShakeDetector.destroy();
    }

}
