package com.figueroaluis.dormdash;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

class SyncInfo extends Thread{
   JSONObject result;
    SyncInfo(JSONObject result) {
        this.result = result;
    }

    public JSONObject getFeed(final jsonInterface callback, RequestParams params){
        JSONObject json = null;
        SyncHttpClient client = new SyncHttpClient();


        client.get("http://10.0.2.2:80/feed", params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
                System.out.println("Begin Feed Retrieval");
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                //Test out the response with this
                System.out.println("Feed Pulled");
                String response = new String(responseBody);
                try {
                    JSONObject json = new JSONObject(response);
//                    callback.onJSONResponse(true, jObj);
                    callback.onDownloadSuccess(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.out.println("Feed Failed");
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
                System.out.println("Retry!");
            }
        });

        return json;

    }

    public void run() {

        synchronized(result) {
            RequestParams params = new RequestParams();
            getFeed(new jsonInterface() {
                @Override
                public void onDownloadSuccess(JSONObject json) {
                    System.out.println("This is the inner result" + json);

                    result=json;
                }
            }, params);
            System.out.println("This is the outer result" + result);


        }
    }


}