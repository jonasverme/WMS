package be.marbleous.wml2.Activities.Orderpick;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import be.marbleous.wml2.Activities.ScanType;
import be.marbleous.wml2.Activities.ScannerInstance;
import be.marbleous.wml2.Models.PickSlipLine;
import be.marbleous.wml2.Models.Pickslip;
import be.marbleous.wml2.R;
import be.marbleous.wml2.ReservationLocationWithPickSlipLine;
import be.marbleous.wml2.ScanBarcodeDelegate;
import be.marbleous.wml2.WmsApplication;


public class ProcessProductActivity extends ActionBarActivity implements ScanBarcodeDelegate {


    private Integer currentAmountScanned;
    private ReservationLocationWithPickSlipLine currentReservationLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_product);

        //get the data from the application singleton
        WmsApplication app = (WmsApplication) getApplicationContext();
        int currentPosition = getIntent().getIntExtra("currentLocationIndex", 0);
        currentReservationLocation = ((List<ReservationLocationWithPickSlipLine>)app.getData()).get(currentPosition);

        ProgressBar mProgress = (ProgressBar) findViewById(R.id.progressBar);
        mProgress.setMax(Integer.parseInt(currentReservationLocation.getReservationLocation().Quantity));

        showProduct(currentReservationLocation);
        currentAmountScanned = 1;
      //  populateListView(p.PickSlipLine);
        ScannerInstance.getInstance().SetScanType(this, ScanType.ProductScan);
    }


    /***
     * btnManualInput clicked
     * @param v
     */
    public void btnManualInputOnClick(View v) {
        showManualInputDialog();
    }


    /***
     * Show a popup dialog to input the pickslip number manual (if the barcode is not scannable)
     */
    private void showManualInputDialog()
    {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Enter product code");

        // Set an EditText view to get user input
        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                processPickslipLine(input.getText().toString());
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

        alert.show();

    }

    private void processPickslipLine(String productcode){

        if (currentReservationLocation.getPickSlipLine().OrderLine.Product.Code.equals(productcode) ){
            moveNext();
        }else{
            //product not available
        }
    }

    private void moveNext(){

        String quantity = currentReservationLocation.getReservationLocation().Quantity;
        if (currentAmountScanned == Integer.parseInt(quantity)){
            closeProduct();
        }else{
            //update the progress bar
            currentAmountScanned ++;

            ProgressBar mProgress = (ProgressBar) findViewById(R.id.progressBar);
            mProgress.setProgress(currentAmountScanned-1);
        }
    }

    private void showProduct(ReservationLocationWithPickSlipLine pl){

        ((TextView)findViewById(R.id.txtProductname)).setText(pl.getPickSlipLine().OrderLine.ProductName);
        ((TextView)findViewById(R.id.txtNumber)).setText(pl.getReservationLocation().Quantity);

        if (pl.getPickSlipLine().OrderLine.Product.ImageBase64 != null) {
            Bitmap decodedByte = getBitmap(pl.getPickSlipLine().OrderLine.Product.ImageBase64);
            ((ImageView) findViewById(R.id.imgProduct)).setImageBitmap(decodedByte);
        }
    }

    /***
     * Convert base 64 string to bitmap
     * @param bitmapString
     * @return bitmap
     */
    private Bitmap getBitmap(String bitmapString) {
        byte[] decodedString = Base64.decode(bitmapString, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

    }

    /***
     * go back to the locations activity
     */
    private void closeProduct(){
        finish();
    }

    @Override
    public void scanBarcodeResult(String[] barcode, ScanType scanType) {
        if (scanType == ScanType.ProductScan) {
            for (String b: barcode) {
                processPickslipLine(b);
            }
        }
    }
}
