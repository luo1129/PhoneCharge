package com.phone.bill;

import java.text.SimpleDateFormat;
import java.util.Locale;

import org.json.JSONObject;

import com.typ.gk.net.GKHttpWrapper;
import com.typ.gk.net.RestCallback;
import com.typ.gk.net.loopj.RequestParams;

import Util.KeyValue;
import Util.ShareData;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends MainActivity {

	SharedPreferences mSP;

	int currentNation = 0;
	String accountName;
	boolean storeAccount = false;

	public static LoginActivity mainAct;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.login);
		init();

		// SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		// String date = sdf.format(new java.util.Date());
		//
		// int time = Integer.parseInt(date);
		// System.out.println("time: "+time);
		// if(time > 20150620){
		// this.finish();
		// }

	}

	private void loadData(String name, String pwd) {

		KeyValue.operatorID = name;

		RequestParams params = new RequestParams();

		params.put("termId", "1");
		params.put("operatorId", name);
		params.put("operatorPwd", pwd);
		params.put("langType", "0" + String.valueOf(currentNation + 1));

		GKHttpWrapper.get("merchant_landing.action", params, new RestCallback() {

			@Override
			public void onSuccess(JSONObject object) {
				// TODO Auto-generated method stub
//				System.out.println(object.toString());
//				String message = object.optString("message");
//				Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();

				KeyValue.mAccount = object.optString("mercAccount");
				KeyValue.mName = object.optString("merchantName");
				KeyValue.isOpen = object.optBoolean("isOpen", true);

				if (TextUtils.isEmpty(KeyValue.mAccount)) {
					return;
				}

				Intent intent3 = new Intent(LoginActivity.this, WelcomeActivity.class);
				startActivityForResult(intent3, 100);
				LoginActivity.this.finish();
			}

			@Override
			public void onFailure(Throwable throwable, JSONObject object) {
				// TODO Auto-generated method stub
				String message = object.optString("message");
				Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
			}

		});

	}

	private void init() {
		mainAct = this;
		currentNation = ShareData.getInt("currentNation");
		this.findViewById(R.id.login_nation1).setBackgroundColor(0x000000ff);
		this.findViewById(R.id.login_nation2).setBackgroundColor(0x000000ff);
		this.findViewById(R.id.login_nation3).setBackgroundColor(0x000000ff);
		this.findViewById(R.id.login_nation4).setBackgroundColor(0x000000ff);
		this.findViewById(R.id.login_nation1 + currentNation).setBackgroundColor(0xff0000ff);

		storeAccount = ShareData.getBoolean("storeAccount");
		CheckBox cb = (CheckBox) this.findViewById(R.id.login_checkbox);
		if (storeAccount) {
			cb.setChecked(true);
		}
		cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				storeAccount = isChecked;
				ShareData.putBoolean("storeAccount", storeAccount);
			}

		});

		accountName = ShareData.getString("accountName", "");
		EditText account = (EditText) this.findViewById(R.id.login_account);
		if (storeAccount) {
			account.setText(accountName);
		}
		account.addTextChangedListener(new TextWatcher() {

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (storeAccount) {
					ShareData.putString("accountName", s.toString());
				}
			}

		});

	}

	private void setLanguage(Locale locale) {
		Resources resources = getResources();// 获得res资源对象
		Configuration config = resources.getConfiguration();// 获得设置对象
		DisplayMetrics dm = resources.getDisplayMetrics();// 获得屏幕参数：主要是分辨率，像素等。
		config.locale = locale;
		resources.updateConfiguration(config, dm);

		Intent intent = new Intent();
		intent.setClass(this, SplashActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		this.startActivity(intent);

		this.finish();
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.login_nation1:
			currentNation = 0;
			ShareData.putInt("currentNation", currentNation);
			this.findViewById(R.id.login_nation1).setBackgroundColor(0xff0000ff);
			this.findViewById(R.id.login_nation2).setBackgroundColor(0x000000ff);
			this.findViewById(R.id.login_nation3).setBackgroundColor(0x000000ff);
			this.findViewById(R.id.login_nation4).setBackgroundColor(0x000000ff);
			setLanguage(Locale.ENGLISH);
			break;
		case R.id.login_nation2:
//			currentNation = 1;
//			ShareData.putInt("currentNation", currentNation);
//			this.findViewById(R.id.login_nation1).setBackgroundColor(0x000000ff);
//			this.findViewById(R.id.login_nation2).setBackgroundColor(0xff0000ff);
//			this.findViewById(R.id.login_nation3).setBackgroundColor(0x000000ff);
//			this.findViewById(R.id.login_nation4).setBackgroundColor(0x000000ff);
//			setLanguage(Locale.CHINESE);
			break;
		case R.id.login_nation3:
//			currentNation = 2;
//			ShareData.putInt("currentNation", currentNation);
//			this.findViewById(R.id.login_nation1).setBackgroundColor(0x000000ff);
//			this.findViewById(R.id.login_nation2).setBackgroundColor(0x000000ff);
//			this.findViewById(R.id.login_nation3).setBackgroundColor(0xff0000ff);
//			this.findViewById(R.id.login_nation4).setBackgroundColor(0x000000ff);
//			setLanguage(Locale.CHINESE);
			break;
		case R.id.login_nation4:
			currentNation = 3;
			ShareData.putInt("currentNation", currentNation);
			this.findViewById(R.id.login_nation1).setBackgroundColor(0x000000ff);
			this.findViewById(R.id.login_nation2).setBackgroundColor(0x000000ff);
			this.findViewById(R.id.login_nation3).setBackgroundColor(0x000000ff);
			this.findViewById(R.id.login_nation4).setBackgroundColor(0xff0000ff);
			setLanguage(Locale.CHINESE);
			break;
		case R.id.login_register:
			Intent intent1 = new Intent(this, RegisterActivity.class);
			this.startActivityForResult(intent1, 100);
			break;
		case R.id.login_forget:
			Intent intent2 = new Intent(this, ForgetActivity.class);
			this.startActivityForResult(intent2, 100);
			break;
		case R.id.login_login:

			EditText tv = (EditText) this.findViewById(R.id.login_account);
			String account = tv.getEditableText().toString();
			tv = (EditText) this.findViewById(R.id.login_pwd);
			String pwd = tv.getEditableText().toString();

			if (TextUtils.isEmpty(account)) {
				return;
			} else if (account.length() < 6) {
				return;
			}
			if (TextUtils.isEmpty(pwd)) {
				return;
			}
			loadData(account, pwd);

			break;
		case R.id.login_checkbox:

			break;
		}
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
