package com.mobilab.imgurgallery.util;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * This class implements the connection witht the imgur api web service
 *
 * @author Thúlio Araújo (thuliolins@gmail.com)
 * @since 2/6/2017
 */
public class ClientConnection {

    private static final String BASE_URL = "https://api.imgur.com/3/gallery/";
    static String requestUrl;
    private static AsyncHttpClient client = new AsyncHttpClient();

    static {
        client.addHeader("Authorization", Constants.getClientAuth()); // Set Your User Client ID to pass autentification
    }

    public static void requestAccess(String URL, RequestParams params, AsyncHttpResponseHandler handler){
        requestUrl = BASE_URL + URL;
        client.get(requestUrl, params, handler);
        Logger.info(requestUrl);
    }
}
