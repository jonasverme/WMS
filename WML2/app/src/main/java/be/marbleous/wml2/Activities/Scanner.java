package be.marbleous.wml2.Activities;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;
/*
import com.datalogic.decode.BarcodeManager;
import com.datalogic.decode.DecodeException;
import com.datalogic.decode.DecodeResult;
import com.datalogic.decode.ReadListener;
import com.datalogic.decode.StartListener;
import com.datalogic.decode.StopListener;
import com.datalogic.decode.TimeoutListener;
import com.datalogic.decode.configuration.DisplayNotification;
*/
import be.marbleous.wml2.ScanBarcodeDelegate;

/**
 * Created by jonasvermeulen on 15/08/16.
 */

import be.marbleous.wml2.ScanBarcodeDelegate;

public class Scanner /*implements ReadListener,
        StartListener, TimeoutListener, StopListener*/ {

    static final String TAG = "Brickx-Scanner";
   // private BarcodeManager mBarcodeManager;
    private Toast mToast;
    private Activity activity;
    private boolean ignoreStop;
    private ScanBarcodeDelegate delegate;
    private ScanType scanType;

    public Scanner()
    {
        initScan();
    }



    public void SetScanType(ScanBarcodeDelegate delegate, ScanType scanType){
        this.delegate = delegate;
        this.scanType = scanType;
    }

    public void TestScan(){
        delegate.scanBarcodeResult(new String[]{"123"}, scanType);
    }

    // Connects to the barcode manager
    private void initScan() {
     //   delegate.scanBarcodeResult(new String[]{"123"}, scanType);
        try {
         //   mBarcodeManager = new BarcodeManager();
        } catch (Exception e) {
            Log.e(TAG, "Error while creating BarcodeManager");
       //     showMessage("ERROR! Check logcat");
            return;
        }

        // Disable Display Notification
     /*   DisplayNotification dn = new DisplayNotification(mBarcodeManager);

        dn.enable.set(false);
        try {
            dn.store(mBarcodeManager, false);
        } catch (Exception e) {
            Log.e(TAG, "Cannot disable Display Notification", e);
        }
*/
        registerListeners();
    }


    // Register this activity as a listener for several scanner events
    private void registerListeners() {
     /*   try {
            mBarcodeManager.addReadListener(this);
            mBarcodeManager.addStartListener(this);
            mBarcodeManager.addStopListener(this);
            mBarcodeManager.addTimeoutListener(this);
        } catch (Exception e) {
            Log.e(TAG, "Cannot add listener, the app won't work");

        }*/
    }

   // Unregister this activity as a listener
    private void releaseListeners() {
       /* try {
            mBarcodeManager.removeReadListener(this);
            mBarcodeManager.removeStartListener(this);
            mBarcodeManager.removeStopListener(this);
            mBarcodeManager.removeTimeoutListener(this);
        } catch (DecodeException e) {
            Log.e(TAG, "Cannot remove listeners, the app won't work", e);
          //  showMessage("ERROR! Check logcat");

        }*/
    }

/*
    @Override
    public void onRead(DecodeResult decodeResult) {
        ignoreStop = true;

        byte[] bData = decodeResult.getRawData();
        String bDataHex = encodeHex(bData);
        String text = decodeResult.getText();
        String symb = decodeResult.getBarcodeID().toString();

        // All data as log
        Log.d(TAG, "Scan read");
        Log.d(TAG, "Symb: " + symb);
        Log.d(TAG, "Data: " + text);
        Log.d(TAG, "Data[]: " + bData.toString());
        Log.d(TAG, "As hex: " + bDataHex);

        delegate.scanBarcodeResult(text.split(";"), scanType);
       // showMessage("Scanner Read");
    }

    @Override
    public void onScanStarted() {
      //  showMessage("Scanner Started");
        Log.d(TAG, "Scan start");
    }

    @Override
    public void onScanStopped() {

        if(!ignoreStop) {
        //    showMessage("Scanner Stopped");
        } else {
            ignoreStop = false;
        }
    }

    @Override
    public void onScanTimeout() {
        ignoreStop = true;

     //   showMessage("Scanning timed out");
        Log.d(TAG, "Scan timeout");
    }

*/
    String encodeHex(byte[] data) {
        if(data == null)
            return "";

        StringBuffer hexString = new StringBuffer();
        hexString.append('[');
        for (int i = 0; i < data.length; i++) {
            hexString.append(' ');
            String hex = Integer.toHexString(0xFF & data[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        hexString.append(']');
        return hexString.toString();
    }



}
