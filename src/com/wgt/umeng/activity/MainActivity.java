package com.wgt.umeng.activity;

import com.umeng.message.UmengRegistrar;
import com.wgt.umeng.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;

public class MainActivity extends BascActivity {
	private final String TAG = "MainActivity";
	private String DeviceToken;
	
	private EditText ed;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DeviceToken = UmengRegistrar.getRegistrationId(this);
        
        initUI();
        
         
    }

	private void initUI() {
		ed =(EditText) findViewById(R.id.ed_device_token);
		ed.setText(DeviceToken);
	}
    
}
