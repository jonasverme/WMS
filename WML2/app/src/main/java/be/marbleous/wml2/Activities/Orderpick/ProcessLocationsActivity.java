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
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import be.marbleous.wml2.Activities.ScanType;
import be.marbleous.wml2.Activities.Scanner;
import be.marbleous.wml2.Activities.ScannerInstance;
import be.marbleous.wml2.FetchCompleteDelegate;
import be.marbleous.wml2.Models.PickSlipLine;
import be.marbleous.wml2.Models.Pickslip;
import be.marbleous.wml2.Models.ReservationLocation;
import be.marbleous.wml2.Models.SavePickSlipDto;
import be.marbleous.wml2.Models.SavePickSlipLineDto;
import be.marbleous.wml2.Models.SaveReservationLocationDto;
import be.marbleous.wml2.PickslipRepository;
import be.marbleous.wml2.R;
import be.marbleous.wml2.ReservationLocationWithPickSlipLine;
import be.marbleous.wml2.ScanBarcodeDelegate;
import be.marbleous.wml2.WmsApplication;


public class ProcessLocationsActivity extends ActionBarActivity implements FetchCompleteDelegate, ScanBarcodeDelegate {


    private Pickslip p;
    private Integer currentPosition;
    private List<ReservationLocationWithPickSlipLine> groupedPickslips;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_locations);



        //get the data from the application singleton
        WmsApplication app = (WmsApplication) getApplicationContext();
        p = (Pickslip)app.getData();

        SavePickSlipDto savePickSlipDto = new SavePickSlipDto();

        //map pickslip id
        savePickSlipDto.Id  = p.Id;

        try {
            //map picksliplines
            savePickSlipDto.SavePickSlipLineDto = new ArrayList<SavePickSlipLineDto>();

            for (PickSlipLine pickslipline : p.PickSlipLine) {

                SavePickSlipLineDto savePickSlipLineDto = new SavePickSlipLineDto();
                savePickSlipLineDto.Id = pickslipline.Id;
                savePickSlipLineDto.SaveReservationLocationDto = new ArrayList<SaveReservationLocationDto>();

                for (ReservationLocation reservationLocation : pickslipline.ReservationLocation) {
                    SaveReservationLocationDto saveReservationLocationDto = new SaveReservationLocationDto();
                    saveReservationLocationDto.Id = reservationLocation.Id;
                    saveReservationLocationDto.Quantity = 99;

                    savePickSlipLineDto.SaveReservationLocationDto.add(saveReservationLocationDto);
                }


                savePickSlipDto.SavePickSlipLineDto.add(savePickSlipLineDto);
            }

        }catch(Exception ex){
            throw ex;
        }

     //   closePickslip(savePickSlipDto);
        groupedPickslips = groupLocations(p);
        app.setData(groupedPickslips);
    
        currentPosition = 0;
        ProgressBar mProgress = (ProgressBar) findViewById(R.id.progressBar);
        mProgress.setMax(p.PickSlipLine.size());
        mProgress.setProgress(0);

        showLocation(groupedPickslips.get(0));


        ScannerInstance.getInstance().SetScanType(this, ScanType.LocationScan);
    }

    private List<ReservationLocationWithPickSlipLine> groupLocations(Pickslip p) {

        List<ReservationLocationWithPickSlipLine> r = new ArrayList<ReservationLocationWithPickSlipLine>();
        for (PickSlipLine pickslipline : p.PickSlipLine ) {
            for (ReservationLocation reservationLocation : pickslipline.ReservationLocation) {
                r.add(new ReservationLocationWithPickSlipLine(reservationLocation, pickslipline));
            }
        }
        return r;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        moveNext();
    }

    /***
     * btnManualInput clicked
     * @param v
     */
    public void btnManualInputOnClick(View v) {
        showManualInputDialog();
    }


    /***
     * Show a popup dialog to input the location number manual (if the barcode is not scannable)
     */
    private void showManualInputDialog()
    {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Enter location number");

        // Set an EditText view to get user input
        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String d = input.getText().toString();
                processLocation(d);
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

        alert.show();

    }

    private void processLocation(String location) {
        String scanLocationTag =  groupedPickslips.get(currentPosition).getReservationLocation().StockLocation.WareHouseLocation.ScanLocationTag;
        if (location.equals(scanLocationTag)){
            showProducts();

        }else{

        }

    }

    private void showProducts(){
        Intent intent = new Intent(this, ProcessProductActivity.class);
        intent.putExtra("currentReservationLocationProduct", currentPosition);

        startActivityForResult(intent, 1);
    }

    private void moveNext()
    {
        if (currentPosition +1< p.PickSlipLine.size()){
            currentPosition ++;

            showLocation(groupedPickslips.get(currentPosition));
            ProgressBar mProgress = (ProgressBar) findViewById(R.id.progressBar);
            mProgress.setProgress(currentPosition);
        }else{
           // closePickslip(savePickSlipDto);
        }
    }

    private void showLocation(ReservationLocationWithPickSlipLine s)
    {
        ((TextView)findViewById(R.id.txtLocationDescription)).setText(s.getReservationLocation().StockLocation.WareHouseLocationName);

    }

    /***
     * close the locations progress activity
     * @param savePickSlipDto
     */
    private void closePickslip(SavePickSlipDto savePickSlipDto)
    {

        PickslipRepository repository = new PickslipRepository(this);

        try {

            repository.UpdatePickslip(this, savePickSlipDto);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        finish();
    }

    @Override
    public void fetchCompleteResult(Pickslip pickslip) {
        
    }

    @Override
    public void scanBarcodeResult(String[] barcode, ScanType scantype) {
        if (scantype == ScanType.LocationScan) {
            processLocation(barcode[0]);
        }
    }
}
