package com.typ.gk.net;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.Toast;

import com.phone.bill.MainActivity;
import com.phone.bill.R;
import com.typ.gk.net.loopj.*;

public class GKHttpWrapper {

	private static final String TAG = GKHttpWrapper.class.getSimpleName();

	// public static String SERVER_URL = "http://111.67.206.182:8889/";
	public static String SERVER_URL = "http://115.84.112.165:8080/";

	private static String API_URL;
	private static AsyncHttpClient client = new AsyncHttpClient();
	public static String mUserAgent = "";

	public static String getSERVER_URL() {
		return SERVER_URL;
	}

	public static String getApiURL() {
		return API_URL;
	}

	public static void initUrl(Context context) {

		if (API_URL == null) {
			API_URL = SERVER_URL + "posrecharge/posrechargeAction!";
		}

	}

	/**
	 * >>>>>>> .merge-right.r2267 post 请求
	 * 
	 * @param method
	 * @param params
	 * @param responseHandler
	 * @throws NoHttpParamsException
	 */
	public static void get(String url, RequestParams params, final RestCallback callback) {
		if (params == null) {
			// throw new NoHttpParamsException();
		}

		url = API_URL.concat(url);

		// 添加rkUid，accessToken,appid
		addRequestParams(params);

		progress();

		client.get(url, params, new JsonHttpResponseHandler() {
			@Override
			public void onFailure(Throwable throwable, JSONObject json) {
				progressDismiss();
				if (json == null) {
					json = new JSONObject();
					try {
						json.put("message", "network error!!");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				try {
					callback.onFailure(throwable, json);
				} catch (Exception e) {
					json = new JSONObject();
					try {
						json.put("message", "network error!!");
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					callback.onFailure(throwable, json);

				}

			}

			@Override
			public void onFailure(Throwable throwable, JSONArray json) {
				// callback.onFailure(throwable, json.);
				progressDismiss();
			}

			@Override
			public void onSuccess(JSONObject json) {
				// 预处理json，解析update数据
				 progressDismiss();

				System.out.println("onSuccess: "+json.toString());
				
				try {
					int statusCode = json.optInt("statusCode");

					if (statusCode == 0) {
						callback.onSuccess(json);
					} else {
						callback.onFailure(new Throwable(), json);
					}
				} catch (Exception e) {
					json = new JSONObject();
					try {
						json.put("message", "network error!!");
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					callback.onFailure(new Throwable(), json);

				}

			}

		});
	}

	static ProgressDialog progressDialog;

	private static void progress() {
		if (!MainActivity.currentAct.isFinishing()) {
			progressDialog = new ProgressDialog(MainActivity.currentAct);
			progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progressDialog.setMessage(MainActivity.currentAct.getText(R.string.loading));
			progressDialog.setCancelable(false);
			progressDialog.show();
		}
	}

	public static void progressDismiss() {
		if (progressDialog != null) {
			progressDialog.dismiss();
			progressDialog = null;
		}
	}

	public static void getBinaryData(String url, BinaryHttpResponseHandler responseHandler) {
		// String[] allowedTypes = new String[] { "image/png", "image/jpeg" };
		client.get(url, responseHandler);
	}

	private static void addRequestParams(RequestParams params) {
		if (params == null) {
			params = new RequestParams();
			return;
		}
		params.put("model", android.os.Build.MODEL);
	}

	public static String getUserAgent(Context applicationContext) {
		String format = "Gac/%s (Android/%s; %s/%s) Package/%s Version/%s Channel/%s Mac/%s GameVersion/%s Network/%s RMac/%s";
		String androidVersion = Build.VERSION.RELEASE;
		String model = Build.MODEL;
		String display = Build.DISPLAY;
		String packageName = applicationContext.getPackageName();
		PackageManager pm = applicationContext.getPackageManager();
		String appVersion = "";

		return model;
	}

}
