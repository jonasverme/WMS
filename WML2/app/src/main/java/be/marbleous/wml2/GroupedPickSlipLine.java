package be.marbleous.wml2;

import java.util.List;

import be.marbleous.wml2.Models.PickSlipLine;
import be.marbleous.wml2.Models.StockLocation;

/**
 * Created by jonasvermeulen on 10/10/15.
 */
public class GroupedPickSlipLine {


    public GroupedPickSlipLine(StockLocation stockLocation, List<PickSlipLine> pickSlipLines){
        this.stockLocation = stockLocation;
        this.pickSlipLines = pickSlipLines;
    }

    public StockLocation stockLocation;
    public List<PickSlipLine> pickSlipLines;



}
