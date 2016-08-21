package be.marbleous.wml2.Activities.ViewLocation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;

import be.marbleous.wml2.FetchLocationCompleteDelegate;
import be.marbleous.wml2.LocationRepository;
import be.marbleous.wml2.R;
import be.marbleous.wml2.WmsApplication;


public class ViewLocationActivity extends ActionBarActivity implements FetchLocationCompleteDelegate {

    private String locationNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_location);
    }

    public void btnManualInputOnClick(View v)
    {
        showManualInputDialog();
    }

    /***
     * Show a popup dialog to input the pickslip number manual (if the barcode is not scannable)
     */
    private void showManualInputDialog()
    {
        AlertDialog alert = createManualInputDialog();
        alert.show();

    }

    /***
     *
     * @return
     */
    public AlertDialog createManualInputDialog()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Enter location number");

        // Set an EditText view to get user input
        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                locationNumber = input.getText().toString();
                fetchLocation(locationNumber);
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

        return alert.create();
    }


    private void fetchLocation(String locationNumber){
        LocationRepository repository = new LocationRepository(this);
        try {
            repository.GetLocationById(locationNumber);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


   // @Override
    public void FetchCompleteResult(Location location) {
        //set the data in the application singleton
        WmsApplication app = (WmsApplication) getApplicationContext();
        app.setData(location);

        ViewLocationDetail();
    }

    public void ViewLocationDetail()
    {
        Intent intent = new Intent(this, ViewLocationDetailActivity.class);

        startActivity(intent);
    }


}
