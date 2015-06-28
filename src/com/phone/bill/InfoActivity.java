package com.phone.bill;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.typ.gk.net.GKHttpWrapper;
import com.typ.gk.net.RestCallback;
import com.typ.gk.net.loopj.RequestParams;

import Util.KeyValue;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class InfoActivity extends MainActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.user_info);
		hideSetting();
	}

	private void loadData() {

		RequestParams params = new RequestParams();

		params.put("mercAccount", KeyValue.mAccount);

		GKHttpWrapper.get("information_query.action", params,
				new RestCallback() {

					@Override
					public void onSuccess(JSONObject object) {
						// TODO Auto-generated method stub
						System.out.println(object.toString());

						parseData(object);

					}

					@Override
					public void onFailure(Throwable throwable, JSONObject object) {
						// TODO Auto-generated method stub

					}

				});

	}

	private void parseData(JSONObject object) {

		TextView tv = (TextView) this.findViewById(R.id.user_info_name);
		tv.append(object.optString("merchantName"));
		tv = (TextView) this.findViewById(R.id.user_info_phone);
		tv.append(object.optString("merchantPhone"));

		JSONArray list = object.optJSONArray("list");

		ArrayList<String[]> operatorList = new ArrayList<String[]>();

		if (list == null) {

			this.findViewById(R.id.linearLayout1).setVisibility(View.INVISIBLE);

			this.findViewById(R.id.linearLayout2).setVisibility(View.INVISIBLE);

			return;
		}

		for (int i = 0; i < list.length(); i++) {
			JSONObject one = list.optJSONObject(i);

			String[] date = new String[3];

			date[0] = one.optString("crtAccount");
			date[1] = one.optString("vendorName");
			date[2] = one.optString("openType");
			operatorList.add(date);
		}

		if (operatorList.size() == 0) {

			this.findViewById(R.id.linearLayout1).setVisibility(View.INVISIBLE);

			this.findViewById(R.id.linearLayout2).setVisibility(View.INVISIBLE);

			return;
		}

		if (!operatorList.get(0)[2].equals("0")) {
			this.findViewById(R.id.linearLayout1).setVisibility(View.GONE);
		} else {
			tv = (TextView) this.findViewById(R.id.user_info_operator_1);
			tv.append(operatorList.get(0)[1]);
			tv = (TextView) this.findViewById(R.id.user_info_account_1);
			tv.append(operatorList.get(0)[0]);
		}

		if (operatorList.size() == 1) {
			this.findViewById(R.id.linearLayout2).setVisibility(View.INVISIBLE);
			return;
		}

		if (!operatorList.get(1)[2].equals("0")) {
			this.findViewById(R.id.linearLayout2).setVisibility(View.GONE);
		} else {
			tv = (TextView) this.findViewById(R.id.user_info_operator_2);
			tv.append(operatorList.get(1)[1]);
			tv = (TextView) this.findViewById(R.id.user_info_account_2);
			tv.append(operatorList.get(1)[0]);
		}

	}

	public void gotoModify(View view) {
		Intent intent3 = new Intent(this, ModifyActivity.class);
		this.startActivityForResult(intent3, 100);
	}

	public void cancel(View view) {
		this.finish();
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
		this.setContentView(R.layout.user_info);
		loadData();
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
