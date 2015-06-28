package com.phone.bill;

import java.util.ArrayList;

import org.json.JSONObject;

import com.typ.gk.net.GKHttpWrapper;
import com.typ.gk.net.RestCallback;
import com.typ.gk.net.loopj.RequestParams;

import Util.KeyValue;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class OpenBusinessActivity extends MainActivity {

	int operator = 0;

	ArrayList<String[]> operators = new ArrayList<String[]>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.open_business);
		init();
	}

	private void loadData() {

		RequestParams params = new RequestParams();

		params.put("termId", "1");
		params.put("mercAccount", KeyValue.mAccount);
		params.put("vendorCode", operators.get(operator)[0]);
		params.put("verFlag", KeyValue.verFlag);

		GKHttpWrapper.get("service_opening.action", params, new RestCallback() {

			@Override
			public void onSuccess(JSONObject object) {
				// TODO Auto-generated method stub
				System.out.println(object.toString());
				String message = object.optString("message");
				Toast.makeText(OpenBusinessActivity.this, message, Toast.LENGTH_SHORT).show();

				Intent intent1 = new Intent(OpenBusinessActivity.this, OpenSuccessActivity.class);
				intent1.putExtra("vendorName", object.optString("vendorName"));
				intent1.putExtra("crtAccount", object.optString("crtAccount"));
				startActivityForResult(intent1, 100);
			}

			@Override
			public void onFailure(Throwable throwable, JSONObject object) {
				// TODO Auto-generated method stub
				String message = object.optString("message");
				Toast.makeText(OpenBusinessActivity.this, message, Toast.LENGTH_SHORT).show();

				Intent intent1 = new Intent(OpenBusinessActivity.this, LoginActivity.class);
				startActivityForResult(intent1, 100);
			}

		});

	}

	@SuppressWarnings("unchecked")
	private void init() {

		operators = (ArrayList<String[]>) this.getIntent().getSerializableExtra("data");

		if (operators != null) {
			RadioButton button = (RadioButton) this.findViewById(R.id.open_business_chbtn_1);
			button.setText(operators.get(0)[1]);
			button = (RadioButton) this.findViewById(R.id.open_business_chbtn_2);
			button.setText(operators.get(1)[1]);
		}

		RadioGroup group = (RadioGroup) this.findViewById(R.id.radioGroup1);

		group.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// TODO Auto-generated method stub
				operator = arg0.getCheckedRadioButtonId() - R.id.open_business_chbtn_1;
			}
		});
	}

	public void open(View view) {
		loadData();
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
