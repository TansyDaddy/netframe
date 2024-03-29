package framework.net.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uni.netframe.NetframeApplication;

public class NetworkRequest {
	
	private static Response.ErrorListener defaultError = new Response.ErrorListener() {

		@Override
		public void onErrorResponse(VolleyError e) {
			e.printStackTrace();
			Log.d(TAG, "error:"+e.getMessage());
		}

	};

	public static void requestJson(String url, Map<String, String> params, String charset,
			Response.Listener<JSONObject> listener, Response.ErrorListener error) {

		JSONObjectRequest jsonObjReq = new JSONObjectRequest(url, params, charset, 
				listener, error == null ? defaultError : error);

		NetframeApplication.getInstance().addToRequestQueue(jsonObjReq);
	}

	public static void requestJson(String url, String charset,
			Response.Listener<JSONObject> listener, Response.ErrorListener error) {

		Type mySuperClass = listener.getClass().getGenericSuperclass();
		Type type = ((ParameterizedType)mySuperClass).getActualTypeArguments()[0];
		Log.d(TAG, type.toString());

		JSONObjectRequest jsonObjReq = new JSONObjectRequest(url, null, charset, listener, error == null ? defaultError : error);

		NetframeApplication.getInstance().addToRequestQueue(jsonObjReq);
	}

	private final static String TAG = "HttpRequest";
	/**
	 * 
	 * @param url
	 * @param bean
	 *            params
	 * @param listener
	 *            callback, either Response.Listener<JSONObject> or
	 *            Response.Listener<JSONArray>
	 * @param error
	 *            callback, use a default callback to print stack trace if null
	 *            is set
	 * 
	 */
	public static void requestJson(String url, final Object bean, String charset,
			Response.Listener<JSONObject> listener, Response.ErrorListener error) {

		ObjectMapper objectMapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, String> params = objectMapper.convertValue(bean, Map.class);

		JSONObjectRequest jsonObjReq = new JSONObjectRequest(url, params, charset, 
				listener, error == null ? defaultError : error);

		NetframeApplication.getInstance().addToRequestQueue(jsonObjReq);
	}   

	/**
	 * 
	 * @param params
	 *            params
	 * @param listener
	 *            callback, either Response.Listener<JSONObject> or
	 *            Response.Listener<JSONArray>
	 * @param error
	 *            callback
	 * 
	 */
	public static void requestArray(String url, Map<String, String> params, String charset,
			Response.Listener<JSONArray> listener, Response.ErrorListener error) {

		JSONArrayRequest jsonObjReq = new JSONArrayRequest(url, params, charset, 
				listener, error == null ? defaultError : error);

		NetframeApplication.getInstance().addToRequestQueue(jsonObjReq);
	}

	/**
	 * 
	 * @param params
	 *            params
	 * @param listener
	 *            callback, either Response.Listener<JSONObject> or
	 *            Response.Listener<JSONArray>
	 * @param error
	 *            callback
	 * 
	 */
	public static void requestArray(String url, String charset,
			Response.Listener<JSONArray> listener, Response.ErrorListener error) {

		JSONArrayRequest jsonObjReq = new JSONArrayRequest(url, null, charset,
				listener, error == null ? defaultError : error);

		NetframeApplication.getInstance().addToRequestQueue(jsonObjReq);
	}

	/**
	 * 
	 * @param url
	 * @param bean
	 *            params
	 * @param listener
	 *            callback, either Response.Listener<JSONObject> or
	 *            Response.Listener<JSONArray>
	 * @param error
	 *            callback, use a default callback to print stack trace if null
	 *            is set
	 * 
	 */
	public static void requestArray(String url, final Object bean, String charset,
			Response.Listener<JSONArray> listener, Response.ErrorListener error) {

		ObjectMapper objectMapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, String> params = objectMapper.convertValue(bean, Map.class);

		JSONArrayRequest jsonObjReq = new JSONArrayRequest(url, params, charset,
				listener, error == null ? defaultError : error);

		NetframeApplication.getInstance().addToRequestQueue(jsonObjReq);
	}   

}
