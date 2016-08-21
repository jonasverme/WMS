package be.marbleous.wml2.Models;

/**
 * Created by jonasvermeulen on 04/04/15.
 */
public class StockLocation {

    public StockLocation(){

    }
    public String Id;
    public String MaximumStock;
    public String MinimumStock;
    public String AvailableStock;
    public String CurrentReservations;
    public String CurrentStock;
    public String WareHouseName;
    public String WareHouseLocationName;

    public WareHouseLocation WareHouseLocation;



}
