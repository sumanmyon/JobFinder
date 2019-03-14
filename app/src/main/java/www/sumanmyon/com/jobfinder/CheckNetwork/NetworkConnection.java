package www.sumanmyon.com.jobfinder.CheckNetwork;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import www.sumanmyon.com.jobfinder.ErrorAndExceptionHandler.ToastShow;

/**
 * Created by suman on 8/16/2017.
 */

public class NetworkConnection {
    Context c;
    public NetworkConnection(Context context){
         c=context;
     }
    public boolean isNetworkConnection(){
        ConnectivityManager manager = (ConnectivityManager) c.getSystemService(c.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
     }
}
