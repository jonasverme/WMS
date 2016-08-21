package be.marbleous.wml2;

import be.marbleous.wml2.Models.PickSlipLine;
import be.marbleous.wml2.Models.Pickslip;
import be.marbleous.wml2.Models.ReservationLocation;

/**
 * Created by jonasvermeulen on 16/10/15.
 */
public class ReservationLocationWithPickSlipLine {

    private ReservationLocation reservationLocation;
    private PickSlipLine pickSlipLine;

    public ReservationLocationWithPickSlipLine(ReservationLocation reservationLocation, PickSlipLine pickSlipLine)
    {
        this.reservationLocation = reservationLocation;
        this.pickSlipLine = pickSlipLine;
    }


    public ReservationLocation getReservationLocation(){
        return this.reservationLocation;
    }

    public PickSlipLine getPickSlipLine(){
        return this.pickSlipLine;
    }
}
