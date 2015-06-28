package com.phone.bill;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.typ.gk.net.GKHttpWrapper;
import com.typ.gk.net.RestCallback;
import com.typ.gk.net.loopj.RequestParams;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import Util.KeyValue;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class RegisterActivity extends MainActivity {

	@InjectView(R.id.register_et_1)
	EditText accountEt;
	@InjectView(R.id.register_et_2)
	EditText pwdEt;
	@InjectView(R.id.register_et_3)
	EditText pwdAgainEt;
	@InjectView(R.id.register_et_4)
	EditText phoneEt;
	@InjectView(R.id.register_et_5)
	EditText nameEt;
	@InjectView(R.id.register_et_6)
	EditText codeEt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.register);
		ButterKnife.inject(this);

		accountEt = (EditText) this.findViewById(R.id.register_et_1);
		pwdEt = (EditText) this.findViewById(R.id.register_et_2);
		pwdAgainEt = (EditText) this.findViewById(R.id.register_et_3);
		phoneEt = (EditText) this.findViewById(R.id.register_et_4);
		nameEt = (EditText) this.findViewById(R.id.register_et_5);
		codeEt = (EditText) this.findViewById(R.id.register_et_6);
		
		
		pwdAgainEt.addTextChangedListener(new TextWatcher(){

			int oldLength;
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
					String pwd = pwdEt.getEditableText().toString();
					String pwdAgain = pwdAgainEt.getEditableText().toString();
					if (!TextUtils.isEmpty(pwdAgain) && !TextUtils.isEmpty(pwd) && oldLength < pwdAgain.length()) {
						if (!pwd.startsWith(pwdAgain)) {
							Toast.makeText(RegisterActivity.this, R.string.register_error7, Toast.LENGTH_SHORT).show();
						}
					}
				
					oldLength = pwdAgain.length();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub

			}
			
		});
		hideSetting();
	}

	private void loadData(String account, String pwd, String phone, String name, String code) {

		RequestParams params = new RequestParams();

		params.put("termId", "1");
		params.put("merchantPhone", phone);
		params.put("merchantName", name);
		params.put("operatorId", account);
		params.put("operatorPwd", pwd);
		params.put("smsAuthCode", code);

		GKHttpWrapper.get("registration_interface.action", params, new RestCallback() {

			@Override
			public void onSuccess(JSONObject object) {
				// TODO Auto-generated method stub
//				System.out.println(object.toString());
//
//				String message = object.optString("message");
//				Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();

				KeyValue.mAccount = object.optString("mercAccount");

				JSONArray list = object.optJSONArray("list");

				ArrayList<String[]> operatorList = new ArrayList<String[]>();

				for (int i = 0; i < list.length(); i++) {
					JSONObject one = list.optJSONObject(i);

					String[] date = new String[2];

					date[0] = one.optString("vendorCode");
					date[1] = one.optString("vendorName");
					operatorList.add(date);
				}
				Intent intent = new Intent(RegisterActivity.this, OpenBusinessActivity.class);
				intent.putExtra("data", operatorList);
				startActivityForResult(intent, 101);
				finish();
			}

			@Override
			public void onFailure(Throwable throwable, JSONObject object) {
				// TODO Auto-generated method stub
				String message = object.optString("message");
				Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
			}

		});

	}

	public void submit(View view) {
		// TODO submit data to server...
		String account = accountEt.getEditableText().toString();
		String pwd = pwdEt.getEditableText().toString();
		String pwdAgain = pwdAgainEt.getEditableText().toString();
		String phone = phoneEt.getEditableText().toString();
		String name = nameEt.getEditableText().toString();
		String code = codeEt.getEditableText().toString();

		if (TextUtils.isEmpty(account)) {
			Toast.makeText(this, R.string.register_error1, Toast.LENGTH_SHORT).show();
			return;
		} else if (account.length() < 6) {
			Toast.makeText(this, R.string.register_error2, Toast.LENGTH_SHORT).show();
			return;
		}
		if (TextUtils.isEmpty(pwd)) {
			Toast.makeText(this, R.string.register_error3, Toast.LENGTH_SHORT).show();
			return;
		} else {
			if (pwd.length() != 6) {
				Toast.makeText(this, R.string.register_error4, Toast.LENGTH_SHORT).show();
				return;
			}
		}
		if (TextUtils.isEmpty(pwdAgain)) {
			Toast.makeText(this, R.string.register_error5, Toast.LENGTH_SHORT).show();
			return;
		} else {
			if (pwdAgain.length() != 6) {
				Toast.makeText(this, R.string.register_error6, Toast.LENGTH_SHORT).show();
				return;
			} else {
				if (!pwdAgain.equals(pwd)) {
					Toast.makeText(this, R.string.register_error7, Toast.LENGTH_SHORT).show();
					return;
				}
			}
		}
		if (TextUtils.isEmpty(phone)) {
			Toast.makeText(this, R.string.register_error8, Toast.LENGTH_SHORT).show();
			return;
		} else {
			if (phone.length() != 11) {
				Toast.makeText(this, R.string.register_error9, Toast.LENGTH_SHORT).show();
				return;
			}
		}
		if (TextUtils.isEmpty(name)) {
			Toast.makeText(this, R.string.register_error10, Toast.LENGTH_SHORT).show();
			return;
		} else {

		}
		if (TextUtils.isEmpty(code)) {
			Toast.makeText(this, R.string.register_error11, Toast.LENGTH_SHORT).show();
			return;
		}

		loadData(account, pwd, phone, name, code);

	}

	public void getCode(View view) {
		// TODO submit data to server...
		System.out.println("///////////////////");
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
