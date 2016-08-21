package be.marbleous.wml2;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import be.marbleous.wml2.Models.PickSlipLine;
import be.marbleous.wml2.Models.Pickslip;
import be.marbleous.wml2.Models.SavePickSlipDto;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by jonasvermeulen on 21/03/15.
 */
public class PickslipRepository {

    private FetchCompleteDelegate delegate;

    /**
     * Pickslip repository constructor
     *
     * @param delegate
     */
    public PickslipRepository(FetchCompleteDelegate delegate) {
        this.delegate = delegate;
    }


    /**
     * Fetch a pickslip by pickslipnumber (async) when complete call the delegate from the view
     *
     * @param id
     * @throws JSONException
     */
    public void GetPickslipById(String id) throws Exception {

        RequestParams params = new RequestParams();
        params.put("number", id);

        try {
            RestClient.get("pickslip/searchbynumber", params, new JsonHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    Pickslip pickslip;
                    try {
                        Gson gson = new Gson();
                        JSONObject d = response.getJSONObject("GetPickslipByNumberResult");
                        pickslip = gson.fromJson(d.toString(), Pickslip.class);
                        delegate.fetchCompleteResult(pickslip);
                    } catch (Exception e) {
                        try {
                            throw e;
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                    }

                }


                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable)  {
                    super.onFailure(statusCode, headers, responseString, throwable);

                        throw new RuntimeException(throwable);

                }
            });

        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * @param p
     */
    public void UpdatePickslip(Context c, SavePickSlipDto p) throws JSONException {


        try {

            Gson g = new Gson();
            String json = g.toJson(p);
            StringEntity s = new StringEntity(json);

            s.setContentType("application/json");


            RestClient.post(c, "Pickslip/savepickslip", s, new AsyncHttpResponseHandler() {


                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    Log.d("HTTP", "onSuccess: " + statusCode);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                    // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                    Log.d("http", "onfailure" + statusCode);
                    try {
                        throw e;
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

