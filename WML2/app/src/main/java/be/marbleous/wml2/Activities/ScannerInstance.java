package be.marbleous.wml2.Activities;

/**
 * Created by jonasvermeulen on 20/08/16.
 */
public class ScannerInstance {
    private static Scanner ourInstance = new Scanner();

    public static Scanner getInstance() {
        return ourInstance;
    }

    private ScannerInstance() {
    }
}
