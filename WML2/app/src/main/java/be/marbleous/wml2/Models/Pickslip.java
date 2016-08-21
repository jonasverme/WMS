package be.marbleous.wml2.Models;

import java.util.List;

/**
 * Created by jonasvermeulen on 31/03/15.
 */
public class Pickslip {

    public Pickslip(){

    }
    /*public Courier Courier;
    public CourierServiceLevel CourierServiceLevel;
    public String CourierTrackTraceNumber;*/
    public String DateAdded;
    public int Id;
    public String Memo;
    public String PickSlipDate;
    public List<PickSlipLine> PickSlipLine;
    public String PickSlipMemoCustomizable;
    public String PickSlipNumber ;
    public String TransSmartDocumentId;
    public String TransSmartStatus;
    public String UserName;



    @Override
    public String toString()
    {
        return "Pickslip [PickslipNumber=" + PickSlipNumber +"]";
    }
}
