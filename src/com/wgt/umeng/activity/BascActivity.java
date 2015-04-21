package com.wgt.umeng.activity;

import com.umeng.message.PushAgent;

import android.app.Activity;
import android.os.Bundle;

public class BascActivity extends Activity {
	private PushAgent mPushAgent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPushAgent = PushAgent.getInstance(this);
		mPushAgent.enable();
		mPushAgent.onAppStart();
		
		
		
	}
}
