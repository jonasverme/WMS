package be.marbleous.wml2.Activities.Orderpick;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//import com.datalogic.decode.BarcodeManager;

import be.marbleous.wml2.Activities.ScanType;
import be.marbleous.wml2.Activities.Scanner;
import be.marbleous.wml2.Activities.ScannerInstance;
import be.marbleous.wml2.ErrorAlert;
import be.marbleous.wml2.FetchCompleteDelegate;
import be.marbleous.wml2.Models.Pickslip;
import be.marbleous.wml2.PickslipRepository;
import be.marbleous.wml2.R;
import be.marbleous.wml2.ScanBarcodeDelegate;
import be.marbleous.wml2.WmsApplication;


public class OrderPickActivity extends ActionBarActivity implements FetchCompleteDelegate , ScanBarcodeDelegate {


    private String pickslipNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderpick);
        ScannerInstance.getInstance().SetScanType(this, ScanType.PickslipScan);

    }



    /***
     * btnManualInput clicked
     * @param v
     */
    public void btnManualInputOnClick(View v) {
        showManualInputDialog();
    }

    /***
     * Fetch the pickslip (ex: "PICK-010936" 10924)
     */
    protected void fetchPickslip()
    {
       // findViewById(R.id.progressBar2).setVisibility(View.VISIBLE);
        PickslipRepository repository = new PickslipRepository(this);
        try {
            repository.GetPickslipById(pickslipNumber);
        } catch (Exception e) {

            e.printStackTrace();
            ErrorAlert x = new ErrorAlert(this);
            x.showErrorDialog("Something went wrong", e.getMessage());
        }
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
        alert.setTitle("Enter Pickslip number");

        // Set an EditText view to get user input
        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                pickslipNumber = input.getText().toString();
                fetchPickslip();
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

        return alert.create();
    }

    /***
     * move to the next screen to view the pickslip scan process
     */
    public void viewLocationProcess() {
        Intent intent = new Intent(this, ProcessLocationsActivity.class);

        startActivity(intent);

    }

    /***
     * Delegate method called when fetching the pickslip is complete
     * @param pickslip
     */
    @Override
    public void fetchCompleteResult(Pickslip pickslip) {

      //  findViewById(R.id.progressBar2).setVisibility(View.INVISIBLE);

        if (pickslip != null) {
            //set the data in the application singleton
            WmsApplication app = (WmsApplication) getApplicationContext();
            app.setData(pickslip);

            //go to next screen
            viewLocationProcess();
        }else{
            //set error on status bar
            //ucStatusBarView v = (ucStatusBarView) findViewById(R.id.view2);
          //  v.setBg(Color.RED);
          //  v.invalidate();

        }
    }

    @Override
    public void scanBarcodeResult(String[] barcode, ScanType scanType) {
        if (scanType == ScanType.PickslipScan) {
            pickslipNumber = barcode[0].toLowerCase();
            fetchPickslip();
        }
    }
}
