package be.marbleous.wml2;

import android.location.Location;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by jonasvermeulen on 18/04/15.
 */
public class LocationRepository {

    private FetchLocationCompleteDelegate delegate;

    public LocationRepository(FetchLocationCompleteDelegate delegate)
    {
        this.delegate = delegate;

    }

    public void GetLocationById(String locationId) throws JSONException
    {

        RequestParams params = new RequestParams();
        params.put("locationname", locationId);

        RestClient.get("wmslocationview/searchbylocation/", params, new JsonHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Location location;
                try {
                    if (!response.isNull("GetWmsLocationViewForLocationResult")) {
                        Gson gson = new Gson();
                        location = gson.fromJson(response.getJSONObject("GetWmsLocationViewForLocationResult").toString(), Location.class);
                        delegate.FetchCompleteResult(location);
                    }else{
                        delegate.FetchCompleteResult(null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


            @Override
            public void onFailure(int statusCode,
                                  Header[] headers,
                                  java.lang.String responseString,
                                  java.lang.Throwable throwable)
            {
                System.out.println(responseString);
            }
        });
    }

}


