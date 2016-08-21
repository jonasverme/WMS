package be.marbleous.wml2;

import android.app.Application;
import android.content.Context;

import be.marbleous.wml2.Models.Pickslip;

/**
 * Created by jonasvermeulen on 05/04/15.
 */
public class WmsApplication extends Application {


    private Object data;
    public Object getData() {return data;}
    public void setData(Object data) {this.data = data;}

    private static Application sApplication;

    public static Application getApplication() {
        return sApplication;
    }


    public static Context getContext() {
        return getApplication().getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }
}
