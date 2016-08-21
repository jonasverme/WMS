package be.marbleous.wml2.Models;

import java.util.List;

/**
 * Created by jonasvermeulen on 04/04/15.
 */
public class PickSlipLine {

    public PickSlipLine(){

    }

    public int Id;
    public String Memo;
    public OrderLine OrderLine;
    public float Quantity;
    public String QuantityFree;
    public String QuantityShipped;
    public List<ReservationLocation> ReservationLocation;
}
