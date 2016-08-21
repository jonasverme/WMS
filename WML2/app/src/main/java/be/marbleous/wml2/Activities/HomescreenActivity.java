package be.marbleous.wml2.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import be.marbleous.wml2.Activities.Orderpick.OrderPickActivity;
import be.marbleous.wml2.Activities.Settings.SettingsActivity;
import be.marbleous.wml2.R;


public class HomescreenActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
       // testPostJson();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_homescreen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /***
     *
     * @param v
     */
    public void btnGoodsReceiptOnClick(View v){
/*        Intent intent = new Intent(this, ProcessLocationsActivity.class);

        startActivity(intent);*/
    }

    /***
     *
     * @param v
     */
    public void btnOrderpickOnClick(View v){
        Intent intent = new Intent(this, OrderPickActivity.class);
        startActivity(intent);
    }

    /***
     *
     * @param v
     */
    public void btnColliOnClick(View v){
                   /*Intent intent = new Intent(this, ProcessLocationsActivity.class);

        startActivity(intent);*/
    }

    /***
     *
     * @param v
     */
    public void btnScanInfoOnClick(View v)
    {
                /*Intent intent = new Intent(this, ProcessLocationsActivity.class);

        startActivity(intent);*/
    }

    /***
     *
     * @param v
     */
    public void btnGoodsTransferOnClick(View v)
    {
                /*Intent intent = new Intent(this, ProcessLocationsActivity.class);

        startActivity(intent);*/
    }

    /***
     *
     * @param v
     */
    public void btnStockCountOnClick(View v)
    {
                /*Intent intent = new Intent(this, ProcessLocationsActivity.class);

        startActivity(intent);*/
    }


    public void testPostJson()
    {

        try {

            /* TestH h = new TestH();
            h.Id = "t";
            h.Name = "j";


            Gson g = new Gson();

            String json = g.toJson(h);


            StringEntity s = new StringEntity(json);

            s.setContentType("application/json");

            RestClient.post(this, "Test", s, new AsyncHttpResponseHandler() {


                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    Log.d("HTTP", "onSuccess: " + statusCode);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                    // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                    Log.d("http", "onfailure" + statusCode);
                }

            });*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
