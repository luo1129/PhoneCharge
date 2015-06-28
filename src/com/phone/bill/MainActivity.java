package com.phone.bill;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.json.JSONObject;

import com.typ.gk.net.GKHttpWrapper;
import com.typ.gk.net.RestCallback;
import com.typ.gk.net.loopj.RequestParams;

import Util.KeyValue;
import Util.ShareData;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static ArrayList<MainActivity> list = new ArrayList<MainActivity>();

	public static Activity currentAct;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		System.out.println("this.getClass().toString(): " + this.getClass().toString());

		for (Activity one : list) {

			if (one.getClass().toString().equals(this.getClass().toString())) {
				one.finish();
			}

		}

		list.add(this);
		ShareData.init(this);
		GKHttpWrapper.initUrl(this);
		currentAct = this;
		int currentNation = ShareData.getInt("currentNation");
		Resources resources = getResources();// 获得res资源对象
		Configuration config = resources.getConfiguration();// 获得设置对象
		DisplayMetrics dm = resources.getDisplayMetrics();// 获得屏幕参数：主要是分辨率，像素等。
		Locale locale = Locale.ENGLISH;
		switch (currentNation) {
		case 0:
			locale = Locale.ENGLISH;
			break;
		case 1:
			locale = Locale.ENGLISH;
			break;
		case 2:
			locale = Locale.ENGLISH;
			break;
		case 3:
			locale = Locale.CHINESE;
			break;
		}
		if (locale.getLanguage().equals(config.locale.getLanguage())) {
			return;
		}

		config.locale = locale;
		resources.updateConfiguration(config, dm);
	}

	public void hideSetting() {
		this.findViewById(R.id.top_setting).setVisibility(View.INVISIBLE);
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.top_back:
		case R.id.open_business_cancel:
			this.finish();
			break;
		case R.id.top_setting:
			Intent intent = new Intent(this, SettingActivity.class);
			this.startActivityForResult(intent, 101);
			break;
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);

		System.out.println("////////////////onConfigurationChanged//////////////////");

		switch (newConfig.orientation) {
		// 更改为LANDSCAPE
		case (Configuration.ORIENTATION_LANDSCAPE):
			// 如果转换为横向屏时，有要做的事，请写在这里

			break;
		// 更改为PORTRAIT
		case (Configuration.ORIENTATION_PORTRAIT):
			// 如果转换为竖向屏时，有要做的事，请写在这里

			break;

		}
	}

	public void getAuthCode(String phone) {

		RequestParams params = new RequestParams();

		params.put("termId", "1");
		params.put("operatorId", KeyValue.operatorID);
		params.put("mercAccount", KeyValue.mAccount);
		params.put("usrPhonenumber", phone);
		String Imei = ((TelephonyManager) getSystemService(TELEPHONY_SERVICE)).getDeviceId();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhss");
		String time = sdf.format(new java.util.Date());
		params.put("tradeNo", Imei.concat(time));

		params.put("transactionId", "1");
		params.put("tradeTime", time);

		GKHttpWrapper.get("sms_sending.action", params, new RestCallback() {

			@Override
			public void onSuccess(JSONObject object) {
				// TODO Auto-generated method stub
				System.out.println(object.toString());
				String message = object.optString("message");
				Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onFailure(Throwable throwable, JSONObject object) {
				// TODO Auto-generated method stub
				String message = object.optString("message");
				Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
			}

		});

	}
	
	AlertDialog quit;

	protected void showQuitDialog() {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.setting_line_7);
		builder.setPositiveButton(R.string.confirm, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				quit.dismiss();
				quitApplication();
			}

		});
		builder.setNegativeButton(R.string.back, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				quit.dismiss();
			}

		});
		
		quit = builder.create();
		quit.show();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub

		System.out.println("this: " + this);

		list.remove(this);
		super.onDestroy();
	}

	protected void quitApplication() {
		for (MainActivity act : list) {
			act.finish();
		}
	}
}
