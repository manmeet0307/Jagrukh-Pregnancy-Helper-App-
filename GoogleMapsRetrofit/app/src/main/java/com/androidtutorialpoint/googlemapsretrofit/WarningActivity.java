package com.androidtutorialpoint.googlemapsretrofit;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class WarningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warning);
        TextView info= (TextView) findViewById(R.id.textView3);
        Typeface cb = Typeface.createFromAsset(getAssets(),"fonts/garbold.ttf");
        info.setTypeface(cb);
    }

    public void sendMsg(View v)
    {
        ArrayList<String> phone_nos= new ArrayList<String>();
        phone_nos.add("08585962392");
        phone_nos.add("09958962625");
        for(int i=0;i<phone_nos.size();++i){
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone_nos.get(i), null, "ALERT: I need help. I am not feeling well.", null, null);
        }
    }
}
