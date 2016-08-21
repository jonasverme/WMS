package be.marbleous.wml2;

import be.marbleous.wml2.Activities.ScanType;

/**
 * Created by jonasvermeulen on 15/08/16.
 */

public interface ScanBarcodeDelegate {
    public void scanBarcodeResult(String[] barcode, ScanType scanType);
}
