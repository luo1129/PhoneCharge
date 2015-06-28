package com.typ.gk.net;

import org.json.JSONObject;

public interface RestCallback {
	public void onSuccess(JSONObject object);
	public void onFailure(Throwable throwable, JSONObject object);
}
