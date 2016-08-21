package be.marbleous.wml2.Activities.ViewLocation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import be.marbleous.wml2.Models.Product;
import be.marbleous.wml2.R;
import be.marbleous.wml2.WmsApplication;


public class ViewLocationDetailActivity extends ActionBarActivity {

    private Product l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_location_detail);

        //get the data from the application singleton
        WmsApplication app = (WmsApplication) getApplicationContext();
        l = (Product)app.getData();

        showLocationDetail(l);
    }

    /***
     *
     * @param l
     */
   private void showLocationDetail(Product l) {

        ((TextView)findViewById(R.id.txtProductCode)).setText(l.Code);
        ((TextView)findViewById(R.id.txtDescription)).setText(l.Name);

        Bitmap decodedByte = getBitmap(l);
        ((ImageView)findViewById(R.id.imgProductImage)).setImageBitmap(decodedByte);

    }


    /***
     * Convert base 64 string to bitmap
     * @param l
     * @return bitmap
     */
    private Bitmap getBitmap(Product l) {

       byte[] decodedString = Base64.decode(l.ImageBase64, Base64.DEFAULT);
       return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

    }
}
