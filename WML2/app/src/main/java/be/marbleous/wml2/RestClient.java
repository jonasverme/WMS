package be.marbleous.wml2;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;


/**
 * Created by jonasvermeulen on 21/03/15.
 */
public class RestClient {


    public static  String REST_URL; // Change this, base API URL
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static  String apikey ;




    /***
     * Get json object from the api
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        params.put("apikey", ConfigHelper.getConfigValue("api_key"));
        String xurl =  getAbsoluteUrl(url);
        client.get(xurl, params, responseHandler);

    }

    /***
     * Post json to the api
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        params.put ("apikey", apikey);
        client.post(REST_URL + url, params, responseHandler);

    }

    /***
     *
     * @param context
     * @param url
     * @param entity
     * @param responseHandler
     */
    public static void post(Context context, String url, StringEntity entity, AsyncHttpResponseHandler responseHandler){

        Header[] headers = {
                new BasicHeader("Accept", "application/json"),
                new BasicHeader("Content-type", "application/json")
               };


        client.post(context, REST_URL + url, headers, entity, "application/json", new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                try {
                    throw error;
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });

    }



    /***
     * Combine rest url with method call and parameters
     * @param relativeUrl
     * @return
     */
    private static String getAbsoluteUrl(String relativeUrl){
        return ConfigHelper.getConfigValue("api_url") + relativeUrl;
    }

}
