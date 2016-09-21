package com.psyberia.fragmentsexample;

import android.os.Looper;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

/**
 * Created by combo on 25.08.2016.
 */
public class LoopjHttpClient {

    // A SyncHttpClient is an AsyncHttpClient
    public static AsyncHttpClient syncHttpClient= new SyncHttpClient();
    public static AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

    public static void setCookieStore(PersistentCookieStore cookieStore) {
        getClient().setCookieStore(cookieStore);
    }
    
    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        getClient().get(url, params, responseHandler);
    }

    public static void post(String url, RequestParams requestParams, AsyncHttpResponseHandler responseHandler) {
        getClient().post(url, requestParams, responseHandler);
    }

    public static void debugLoopJ(String TAG, String methodName, String url, RequestParams requestParams, byte[] response, cz.msebera.android.httpclient.Header[] headers, int statusCode, Throwable t) {

        Log.d(TAG, getClient().getUrlWithQueryString(false, url, requestParams));

        if (headers != null) {
            Log.e(TAG, methodName);
            Log.d(TAG, "Return Headers:");
            /*
            for (Header h : headers) {
                String _h = String.format(Locale.US, "%s : %s", h.getName(), h.getValue());
                Log.d(TAG, _h);
            }
            */

            if (t != null) {
                Log.d(TAG, "Throwable:" + t);
            }

            Log.e(TAG, "StatusCode: " + statusCode);

            if (response != null) {
                Log.d(TAG, "Response: " + new String(response));
            }

        }
    }
    /**
     * @return an async client when calling from the main thread, otherwise a sync client.
     */
    private static AsyncHttpClient getClient()
    {
        // Return the synchronous HTTP client when the thread is not prepared
        if (Looper.myLooper() == null)
            return syncHttpClient;
        return asyncHttpClient;
    }
}