package com.wgt.umeng.activity;

import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.IUmengUnregisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

public class MyApplication extends Application {
	private final String TAG = MyApplication.class.getName();
	private PushAgent pushAgent;
	public static IUmengRegisterCallback mRegisterCallback;
	public static IUmengUnregisterCallback mUnregisterCallback;

	@Override
	public void onCreate() {
		super.onCreate();
		pushAgent = PushAgent.getInstance(this);
		if (!pushAgent.isEnabled()) {
			Toast.makeText(this, "推送未开启", 0).show();
		}
		UmengMessageHandler umengMessageHandler = new UmengMessageHandler() {
			@Override
			public void dealWithCustomMessage(final Context arg0,
					final UMessage arg1) {
				new Handler(getMainLooper()).post(new Runnable() {

					@Override
					public void run() {
						UTrack.getInstance(getApplicationContext())
								.trackMsgClick(arg1);
						Toast.makeText(arg0, arg1.custom, Toast.LENGTH_LONG)
								.show();
					}
				});
			}

			@Override
			public Notification getNotification(Context arg0, UMessage arg1) {
				return super.getNotification(arg0, arg1);
			}
		};
		pushAgent.setMessageHandler(umengMessageHandler);
		UmengNotificationClickHandler umengNotificationClickHandler = new UmengNotificationClickHandler() {
			@Override
			public void dealWithCustomAction(Context arg0, UMessage arg1) {
				Toast.makeText(arg0, arg1.custom, Toast.LENGTH_LONG).show();
			}
		};
		pushAgent.setNotificationClickHandler(umengNotificationClickHandler);
		mRegisterCallback = new IUmengRegisterCallback() {

			@Override
			public void onRegistered(String registrationId) {
				// TODO Auto-generated method stub
				Intent intent = new Intent("callback_receiver_action");
				sendBroadcast(intent);
			}

		};
		pushAgent.setRegisterCallback(mRegisterCallback);

		mUnregisterCallback = new IUmengUnregisterCallback() {

			@Override
			public void onUnregistered(String registrationId) {
				// TODO Auto-generated method stub
				Intent intent = new Intent("callback_receiver_action");
				sendBroadcast(intent);
			}
		};
		pushAgent.setUnregisterCallback(mUnregisterCallback);
	}
}
