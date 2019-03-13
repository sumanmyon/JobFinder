package www.sumanmyon.com.jobfinder.ErrorAndExceptionHandler;

import android.app.Activity;
import android.widget.Toast;

public class ToastShow {
    //String msg;
        public ToastShow(Activity activity, String msg){
            Toast.makeText(activity,msg,Toast.LENGTH_LONG).show();
        }

}
